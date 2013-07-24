/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.values.utils.ValueVisitor;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public final class TupleValue extends OclValue<Map<String, OclValue<?>>> {
    private static final long serialVersionUID = 1L;

    private TupleType type;
    public TupleValue(Map<String, OclValue<?>> value) {
        super(new HashMap<>(value));
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
    public TupleType getType() {
        if (type == null) {
            type = new TupleType();
            Map<String, OclValue<?>> values = getValue();
            for (String name : values.keySet()) {
                type.addAttribute(name, values.get(name).getType());
            }            
        }
        return type;
    }
}
