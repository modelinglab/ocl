/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.string;

import java.util.ArrayList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.string.Characters;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StringCharactersEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        String sourceVal = val.getValue();
        ArrayList<StringValue> chars = new ArrayList<>(sourceVal.length());
        for (char c : sourceVal.toCharArray()) {
            chars.add(new StringValue(Character.toString(c)));
        }
        return new SequenceValue<>(chars, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING), true);
    }

    @Override
    public Operation getEvaluableOperation() {
        return Characters.getInstance();
    }
    
}
