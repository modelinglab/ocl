/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;

/**
 *
 * @param <Id> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class ObjectValue<Id> extends OclValue<UmlObject<Id>> {
    private static final long serialVersionUID = 1L;

    public ObjectValue(UmlObject<Id> value) {
        super(value);
        assert value != null;
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public UmlClass getType() {
        return getValue().getUmlClass();
    }
    
    
}
