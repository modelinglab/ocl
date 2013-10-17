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
import org.modelinglab.ocl.core.standard.iterators.collection.Exists;
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
public class CollectionExistsEvaluator implements IteratorEvaluator<OclValue<?>> {

    private static final long serialVersionUID = 1L;

    private CollectionExistsEvaluator() {
    }

    public static CollectionExistsEvaluator getInstance() {
        return CollectionExistsEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<OclValue<?>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceValue) {
        if (taskResult.equals(BooleanValue.FALSE)) {
            return null;
        }
        if (taskResult.getType().oclIsUndefined()) {
            accumulator.setValue(InvalidValue.instantiate());
            return null;
        }
        assert taskResult.equals(BooleanValue.TRUE);
        return taskResult;
    }

    @Override
    public OclValueIndirection<OclValue<?>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<OclValue<?>> accum = new OclValueIndirection<>();
        accum.setValue(BooleanValue.FALSE);
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
        assert accumulator.getValue().equals(BooleanValue.FALSE) || accumulator.getValue().getType().oclIsUndefined() :
                "This method is called only if no task returns BooleanValue.TRUE, accumulator should value FALSE or undefined";
        return accumulator.getValue();
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Exists.class;
    }

    private static class CollectionExistsEvaluatorHolder {

        private static final CollectionExistsEvaluator INSTANCE = new CollectionExistsEvaluator();
    }
}
