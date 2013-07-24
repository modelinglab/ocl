/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class EnumValue extends OclValue<UmlEnumLiteral> {
    private static final long serialVersionUID = 1L;

    public EnumValue(UmlEnumLiteral value) {
        super(value);
    }

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    @Override
    public Classifier getType() {
        return getValue().getType();
    }
    
}
