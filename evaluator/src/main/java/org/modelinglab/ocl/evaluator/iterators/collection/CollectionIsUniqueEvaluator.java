/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import java.util.HashMap;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.collection.IsUnique;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionIsUniqueEvaluator implements IteratorEvaluator<HashMap<OclValue<?>, OclValue<?>>> {

    private static final long serialVersionUID = 1L;

    private CollectionIsUniqueEvaluator() {
    }

    public static CollectionIsUniqueEvaluator getInstance() {
        return CollectionIsUniqueEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<HashMap<OclValue<?>, OclValue<?>>> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceVal) {
        OclValue<?> sourceElement = sourceVal.get(index);

        if (taskResult.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        
        OclValue<?> previousSourceWithThisResult = accumulator.getValue().put(taskResult, sourceElement);
        if (previousSourceWithThisResult != null && !previousSourceWithThisResult.equals(sourceElement)) {
            /*
             * there are at least 2 different source elements that are evaluated to the same value!
             */
            return BooleanValue.FALSE;
        }
        return null;
    }

    @Override
    public OclValueIndirection<HashMap<OclValue<?>, OclValue<?>>> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        final int size = sourceVal.size();

        OclValueIndirection<HashMap<OclValue<?>, OclValue<?>>> accum = new OclValueIndirection<>();
        accum.setValue(new HashMap<OclValue<?>, OclValue<?>>(size));
        return accum;
    }

    @Override
    public OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
        return bodyValue;
    }

    @Override
    public Classifier expectedBodyClassifier() {
        return AnyType.getInstance();
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<HashMap<OclValue<?>, OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        assert accumulator.getValue().size() == sourceAsList.size() :
                "By construction, if this method is called, each different source value should have "
                + "a different evaluation body!";
        return BooleanValue.TRUE;
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return IsUnique.class;
    }

    private static class CollectionIsUniqueEvaluatorHolder {

        private static final CollectionIsUniqueEvaluator INSTANCE = new CollectionIsUniqueEvaluator();
    }
}
