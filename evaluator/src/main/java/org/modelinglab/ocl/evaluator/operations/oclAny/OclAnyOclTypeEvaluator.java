/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclIsKindOf;
import org.modelinglab.ocl.core.standard.operations.oclAny.OclType;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyOclTypeEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        Classifier type = val.getType();
        return new ClassifierValue(type);
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        return new ClassifierValue(val.getType());
    }

    @Override
    public Operation getEvaluableOperation() {
        return OclType.createTemplateOperation();
    }
}

