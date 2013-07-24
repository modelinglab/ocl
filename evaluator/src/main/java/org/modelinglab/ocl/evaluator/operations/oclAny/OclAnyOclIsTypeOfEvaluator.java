/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsTypeOf;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyOclIsTypeOfEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        assert arg.arguments.size() == 1;
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal instanceof ClassifierValue) {
            Classifier toType = (Classifier) argVal.getValue();
            if (!val.getType().equals(toType)) {
                return BooleanValue.FALSE;
            }
            return BooleanValue.TRUE;
        }
        assert argVal.getType().conformsTo(VoidType.getInstance());
        return InvalidValue.instantiate();
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        OclValue<?> argVal = arg.arguments.get(0);
        if (argVal instanceof ClassifierValue) {
            Classifier toType = (Classifier) argVal.getValue();
            if (toType.equals(VoidType.getInstance())) {
                return BooleanValue.TRUE;
            }
            return BooleanValue.FALSE;
        }
        return InvalidValue.instantiate();
    }

    @Override
    public Operation getEvaluableOperation() {
        return OclIsTypeOf.createTemplateOperation();
    }
    
}

