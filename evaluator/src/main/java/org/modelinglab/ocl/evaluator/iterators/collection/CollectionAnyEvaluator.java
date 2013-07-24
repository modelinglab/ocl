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
import org.modelinglab.ocl.core.standard.iterators.collection.Any;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionAnyEvaluator implements IteratorEvaluator<OclValue<?>> {

    private static final long serialVersionUID = 1L;

    private CollectionAnyEvaluator() {
    }

    public static CollectionAnyEvaluator getInstance() {
        return CollectionAnyEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<OclValue<?>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceValue) {
        if (taskResult != null) {
            accumulator.setValue(taskResult);
            
            return taskResult;
        }
        return null;
    }

    @Override
    public OclValueIndirection<OclValue<?>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<OclValue<?>> accum = new OclValueIndirection<>();
        accum.setValue(VoidValue.instantiate());
        return accum;
    }

    @Override
    public OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
        assert element2 == null : "any may have at most one iterator variable.";
        if (bodyValue.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        if (bodyValue.getType().oclIsUndefined()) {
            return null;
        }
        assert bodyValue instanceof BooleanValue;
        
        BooleanValue castedBodyValue = (BooleanValue) bodyValue;
        if (!castedBodyValue.getValue()) {
            return null;
        }
        return element1;
    }

    @Override
    public Classifier expectedBodyClassifier() {
        return PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<OclValue<?>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        assert accumulator.getValue() != null;
        return accumulator.getValue();
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Any.class;
    }

    private static class CollectionAnyEvaluatorHolder {

        private static final CollectionAnyEvaluator INSTANCE = new CollectionAnyEvaluator();
    }
}
