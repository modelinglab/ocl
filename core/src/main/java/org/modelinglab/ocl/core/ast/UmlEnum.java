/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.ast.utils.UmlVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UmlEnum extends UmlClass {

    private static final long serialVersionUID = 1L;
    OrderedSet<UmlEnumLiteral> literals = new OrderedSet<>();

    private static UmlClass genericInstance;
    
    public static UmlClass getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new UmlClass();
            genericInstance.setName("UmlEnum");
            genericInstance.setAbstract(true);
        }
        return genericInstance;
    }
    
    public List<UmlEnumLiteral> getLiterals() {
        return Collections.unmodifiableList(literals);
    }

    public void setLiterals(Collection<? extends UmlEnumLiteral> literals) {
        modifyAttempt();
        this.literals.clear();
        this.literals.addAll(literals);
    }

    public void addLiteral(@Nonnull UmlEnumLiteral literalToAdd) {
        modifyAttempt();
        literalToAdd.setType(this);
    }

    public void addLiteralUnsecure(UmlEnumLiteral literalToAdd) {
        modifyAttempt();
        literals.add(literalToAdd);
    }

    public boolean removeLiteral(UmlEnumLiteral literalToRemove) {
        modifyAttempt();
        boolean b = literals.contains(literalToRemove);
        literalToRemove.setTypeUnsecure(null);
        assert (!literals.contains(literalToRemove));
        return b;
    }

    public boolean removeLiteralUnsecure(UmlEnumLiteral literalToRemove) {
        modifyAttempt();
        return literals.remove(literalToRemove);
    }
    
    public int getLiteralOrdinal(UmlEnumLiteral literal) {
        int ordinal = literals.indexOf(literal);
        if (ordinal < 0) {
            throw new IllegalArgumentException(literal + " is not a literal of " + this);
        }
        return ordinal;
    }
    
    public UmlEnumLiteral getLiteral(@Nonnegative int ordinal) {
        if (ordinal < 0 || ordinal >= literals.size()) {
            throw new IllegalArgumentException("Ordinal "+ordinal+" is lower than 0 or greater or equal "
                    + "than literals size ("+literals.size()+")");
        }
        return literals.get(ordinal);
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(literals);
        return result;
    }
    
    @Override
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
    
    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
