/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * VoidType represents a type that conforms to all types. The only instance of VoidType is OclVoid, 
 * which is further defined in the standard library. Furthermore OclVoid has exactly one instance 
 * called null - corresponding to the UML NullLiteral literal specification - and representing the 
 * absence of value. Note that in contrast with OclInvalid null is a valid value and as such can be 
 * owned by collections.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VoidType extends Classifier {

    private static final long serialVersionUID = 1L;
    private static VoidType instance;
    private final ClassifierType classifierType = new ClassifierType(this);

    private VoidType() {
    }

    public static VoidType getInstance() {
        if (instance == null) {
            instance = new VoidType();
        }
        return instance;
    }

    @Override
    public ClassifierType getClassifierType() {
        return classifierType;
    }

    /**
     * @return Void conforms to all other types except OclInvalid.
     */
    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        return conformsTarget != InvalidType.getInstance();
    }

    @Override
    public String getName() {
        return "VoidType";
    }

    @Override
    public List<Classifier> getSuperClassifiers() {
        List<Classifier> result = new ArrayList<Classifier>(1);
        result.add(new TemplateParameterType("VoidTypeTemplate"));
        return result;
    }

    @Override
    public boolean oclIsUndefined() {
        return true;
    }
    
    @Override
    public boolean equals(Object o) {
        return o instanceof VoidType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public VoidType clone() {
        return this;
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
