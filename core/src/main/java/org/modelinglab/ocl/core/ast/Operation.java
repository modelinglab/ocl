/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.UmlVisitor;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.*;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class Operation extends TypedElement {

    private static final long serialVersionUID = 1L;
    private LinkedList<Parameter> ownedParameters = new LinkedList<Parameter>();
    private Set<Classifier> raisedExceptions = new HashSet<Classifier>();
    private final Operation templateOperation;
    private Classifier source;
    private Classifier type;

    public Operation(@Nullable Operation templateOperation) {
        this.templateOperation = templateOperation;
    }

    public abstract Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions);
    
    public Operation getTemplateOperation() {
        if (templateOperation == null) {
            return this;
        }
        return templateOperation;
    }

    public Classifier getSource() {
        return source;
    }

    public void setSource(Classifier source) {
        modifyAttempt();
        this.source = source;
    }

    @Override
    public Classifier getType() {
        return type;
    }

    @Override
    public void setType(Classifier type) {
        modifyAttempt();
        this.type = type;
    }

    /**
     * @return The parameters to the operation.
     */
    public final List<Parameter> getOwnedParameters() {
        return Collections.unmodifiableList(ownedParameters);
    }

    public final void setOwnedParameters(Collection<? extends Parameter> ownedParameters) {
        modifyAttempt();
        for (Parameter p : getOwnedParameters()) {
            p.setOperationUnsecure(null);
        }
        this.ownedParameters = new LinkedList<Parameter>(ownedParameters);
        for (Parameter p : ownedParameters) {
            p.setOperationUnsecure(this);
        }
    }

    public final void addOwnedParameter(Parameter parameterToAdd) {
        modifyAttempt();
        parameterToAdd.setOperation(this);
    }

    public final boolean removeOwnedParameter(Parameter parameterToRemove) {
        modifyAttempt();
        boolean b = ownedParameters.contains(parameterToRemove);
        parameterToRemove.setOperation(null);
        assert (!ownedParameters.contains(parameterToRemove));
        return b;
    }

    public final void addOwnedParameterUnsecure(Parameter parameterToAdd) {
        modifyAttempt();
        ownedParameters.add(parameterToAdd);
    }

    public final boolean removeOwnedParameterUnsecure(Parameter parameterToRemove) {
        modifyAttempt();
        return ownedParameters.remove(parameterToRemove);
    }

    /**
     * @return The exceptions that are declared as possible during an invocation of the operation.
     */
    public Set<Classifier> getRaisedExceptions() {
        return raisedExceptions;
    }

    public void setRaisedExceptions(Collection<? extends Classifier> raisedExceptions) {
        modifyAttempt();
        this.raisedExceptions = new HashSet<Classifier>(raisedExceptions);
    }

    public void addRaisedException(Classifier raisedExceptionToAdd) {
        modifyAttempt();
        getRaisedExceptions().add(raisedExceptionToAdd);
    }

    public boolean removeRaisedException(Classifier raisedExceptionToRemove) {
        modifyAttempt();
        return getRaisedExceptions().remove(raisedExceptionToRemove);
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(getOwnedParameters());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Operation)) {
            return false;
        }
        final Operation other = (Operation) obj;
        if (this.ownedParameters != other.ownedParameters && (this.ownedParameters == null || !this.ownedParameters.equals(other.ownedParameters))) {
            return false;
        }
        if (this.raisedExceptions != other.raisedExceptions && (this.raisedExceptions == null || !this.raisedExceptions.equals(other.raisedExceptions))) {
            return false;
        }
        if (this.source != other.source && (this.source == null || !this.source.equals(other.source))) {
            return false;
        }
        if (this.type != other.type && (this.type == null || !this.type.equals(other.type))) {
            return false;
        }
        return elementEquals(other);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        String name = this.getName();
        hash = 19 * hash + (name != null ? name.hashCode() : 0);
        hash = 19 * hash + (this.source != null ? this.source.hashCode() : 0);
        hash = 19 * hash + (this.type != null ? this.type.hashCode() : 0);
        return hash;
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
    
    @Override
    public String toString() {
        return getSignature();
    }
    
    public String toString(OclExpression source, List<OclExpression> arguments) {
        return getSignature() + " with source " + source + " and args " + arguments + '}';
    }

    public String getSignature() {
        StringBuilder sb = new StringBuilder(40);
        sb.append(getSource().getName()).append("::").append(getName()).append('(');

        if (!getOwnedParameters().isEmpty()) {
            for (Parameter p : getOwnedParameters()) {
                sb.append(p.getName()).append(" : ").append(p.getType().getName());
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(") : ");
        sb.append(getType().getName());
        return sb.toString();
    }
    
    public boolean overrides(@Nonnull Operation op, TemplateRestrictions restrictions) {
        if (op == this || op.equals(this)) {
            return true;
        }
        if (!op.getName().equals(this.getName())) { //different ops names
            return false;
        }
        if (!this.getSource().conformsTo(op.getSource(), restrictions)) {
            //this.getClassifier does not extend op.getClassifier() => this can not override op
            return false;
        }
        final int size = this.getOwnedParameters().size();
        if (size != op.getOwnedParameters().size()) { //they have different number of arguments => this can not override op
            return false;
        }
        for (int i = 0; i < size; i++) {
            Classifier myArg = this.getOwnedParameters().get(i).getType();
            Classifier opArg = op.getOwnedParameters().get(i).getType();
            
            if (!myArg.conformsTo(opArg, restrictions)) { //i-th argument does not conform
                return false;
            }
        }
        if (!this.getType().conformsTo(op.getType(), restrictions)) { //types does not conform
            return false;
        }
        return true;
    }
    
    /**
     * Operation that can be evaluated without dynamic values (for example, some literal cases)
     * returns its value. The others return null.
     * 
     * @param sourceVals 
     * @param argsVals 
     * @return If expression can be evaluated with static values, this
     * value is returned. If expression need dyniamic values, null is returned.
     * @throws OclEvaluationException  
     */
    public OclValue getStaticEvaluation(Classifier sourceType, OclValue sourceVals, List<OclValue> argsVals) throws OclEvaluationException {
        return null;
    }
}
