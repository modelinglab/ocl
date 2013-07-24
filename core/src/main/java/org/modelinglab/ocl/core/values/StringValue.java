/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import org.modelinglab.ocl.core.values.utils.ValueVisitor;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import javax.annotation.concurrent.Immutable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public final class StringValue extends OclValue<String> {
    private static final long serialVersionUID = 1L;

    public StringValue(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "\"" + getValue() + "\"";
    }

    @Override
    public <Result, Arg> Result accept(ValueVisitor<Result, Arg> visitor, Arg arg) {
        return visitor.visit(this, arg);
    }

    @Override
    public Classifier getType() {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING);
    }
    
}
