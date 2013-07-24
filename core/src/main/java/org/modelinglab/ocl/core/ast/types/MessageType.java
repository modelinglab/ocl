/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.MessageType.Signal;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;

/**
 * MessageType describes ocl messages. Similar to the collection types, MessageType describes a set 
 * of types in the standard library. Part of every MessageType is a reference to the declaration of 
 * the type of its operation or signal, i.e., an ocl message type is parameterized with an operation
 * or signal. In the metamodel, this is shown as an association from MessageType to Operation and to
 * Signal. MessageType is part of the abstract syntax of OCL, residing on M2 level. Its instances, 
 * called OclMessage, and subtypes of OclMessage, reside on M1 level.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Deprecated
public final class MessageType extends Classifier {

    private static final long serialVersionUID = 1L;
    private static MessageType genericInstance;
    Operation referredOperation;
    Signal referredSignal;

    private MessageType() {
    }

    private MessageType(MessageType other) {
        
    }
    
    public static MessageType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new MessageType();
        }
        return genericInstance;
    }

    @Override
    public ClassifierType getClassifierType() {
        return new ClassifierType(this);
    }

    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        return false;
    }

    @Override
    public String getName() {
        if (getReferredOperation() != null) {
            return "Message " + getReferredOperation().getName();
        } else {
            return "Message " + getReferredSignal().getName();
        }
    }
    
    @Override
    public List<Classifier> getSuperClassifiers() {
        List<Classifier> result = new ArrayList<Classifier>(1);
        result.add(AnyType.getInstance());
        return result;
    }

    /**
     * @return The Operation that is called by the message.
     */
    public Operation getReferredOperation() {
        return referredOperation;
    }

    /**
     * @return The Signal that is sent by the message.
     */
    public Signal getReferredSignal() {
        return referredSignal;
    }

    public void setReferredOperation(Operation referredOperation) {
        Preconditions.checkState(referredSignal == null, "MessageType has either a link with a Signal or with an operation, but not both.");
        this.referredOperation = referredOperation;
    }

    public void setReferredSignal(Signal referredSignal) {
        Preconditions.checkState(referredOperation == null, "MessageType has either a link with a Signal or with an operation, but not both.");
        this.referredSignal = referredSignal;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MessageType other = (MessageType) obj;
        if (this.referredOperation != other.referredOperation && (this.referredOperation == null || !this.referredOperation.equals(other.referredOperation))) {
            return false;
        }
        if (this.referredSignal != other.referredSignal && (this.referredSignal == null || !this.referredSignal.equals(other.referredSignal))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + (this.referredOperation != null ? this.referredOperation.hashCode() : 0);
        hash = 13 * hash + (this.referredSignal != null ? this.referredSignal.hashCode() : 0);
        return hash;
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public MessageType clone() {
        return new MessageType(this);
    }
    
    public static class Signal extends Element {
        private static final long serialVersionUID = 1L;

        @Override
        public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean equals(Object obj) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Element clone() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
    }
}
