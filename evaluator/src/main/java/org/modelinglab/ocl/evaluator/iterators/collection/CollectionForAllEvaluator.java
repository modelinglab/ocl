/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.iterators.collection.ForAll;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluationAlgorithm;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionForAllEvaluator implements IteratorEvaluator<OclValue<?>> {

    private static final long serialVersionUID = 1L;

    private CollectionForAllEvaluator() {
    }

    public static CollectionForAllEvaluator getInstance() {
        return CollectionForAllEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<OclValue<?>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceValue) {
        if (taskResult.equals(BooleanValue.TRUE)) {
            return null;
        }
        if (taskResult.getType().oclIsUndefined()) {
            accumulator.setValue(InvalidValue.instantiate());
            return null;
        }
        assert taskResult.equals(BooleanValue.FALSE);
        return taskResult;
    }

    @Override
    public OclValueIndirection<OclValue<?>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<OclValue<?>> accum = new OclValueIndirection<>();
        accum.setValue(BooleanValue.TRUE);
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

    @Override
    public OclValue<?> extractResult(OclValueIndirection<OclValue<?>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        assert accumulator.getValue().equals(BooleanValue.TRUE) || accumulator.getValue().getType().oclIsUndefined() :
                "This method is called only if no task returns BooleanValue.FALSE, accumulator should value TRUE or undefined";
        return accumulator.getValue();
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return ForAll.class;
    }

    private static class CollectionForAllEvaluatorHolder {

        private static final CollectionForAllEvaluator INSTANCE = new CollectionForAllEvaluator();
    }
}
