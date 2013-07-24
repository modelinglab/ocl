/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.generic;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractCollectNestedEvaluator implements IteratorEvaluator<List<OclValue<?>>> {

    @Override
    public Classifier expectedBodyClassifier() {
        return AnyType.getInstance();
    }

    @Override
    public OclValueIndirection<List<OclValue<?>>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<List<OclValue<?>>> accum = new OclValueIndirection<>();
        
        List<OclValue<?>> val = new ArrayList<>(sourceVal.size());
        
        accum.setValue(val);
        for (int i = 0; i < sourceVal.size(); i++) {
            val.add(null);
        }

        return accum;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<List<OclValue<?>>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceVal) {
        if (taskResult.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }

        accumulator.getValue().set(index, taskResult);
        return null;
    }

    @Override
    public OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
        return bodyValue;
    }
}
