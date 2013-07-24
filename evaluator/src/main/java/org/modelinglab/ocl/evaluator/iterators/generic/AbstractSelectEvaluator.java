/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.generic;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class AbstractSelectEvaluator implements IteratorEvaluator<List<OclValue<?>>> {
    
    @Override
    public OclValue<?> acumResult(OclValueIndirection<List<OclValue<?>>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceVal) {
        if (taskResult instanceof BooleanValue) {
            boolean val = (Boolean) taskResult.getValue();
            if (val) {
                accumulator.getValue().add(sourceVal.get(index));
            }
            return null;
        }
        else {
            assert taskResult.getType().oclIsUndefined();
            return InvalidValue.instantiate();
        }
    }

    @Override
    public OclValueIndirection<List<OclValue<?>>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<List<OclValue<?>>> accum = new OclValueIndirection<>();
        accum.setValue(new ArrayList<OclValue<?>>(sourceVal.size()));
        
        return accum;
    }

    @Override
    public OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
        return bodyValue;
    }

    @Override
    public Classifier expectedBodyClassifier() {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
    }
}
