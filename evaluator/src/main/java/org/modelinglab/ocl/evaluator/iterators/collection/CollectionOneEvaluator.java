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
import org.modelinglab.ocl.core.standard.iterators.collection.One;
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
public class CollectionOneEvaluator implements IteratorEvaluator<BooleanValue> {

    private static final long serialVersionUID = 1L;

    private CollectionOneEvaluator() {
    }

    public static CollectionOneEvaluator getInstance() {
        return CollectionOneEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> acumResult(OclValueIndirection<BooleanValue> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceVal) {
        BooleanValue accum = accumulator.getValue();
        assert accum != null;
        if (taskResult.getType().oclIsInvalid()) {
            return InvalidValue.instantiate();
        }
        
        if (!taskResult.getValue().equals(Boolean.TRUE)) {
            //this element evaluates body to not true (false or null) => so evaluation may return true or false.
            return null;
        }
        if (accum.getValue()) { //is true => previously we found an object which evaluates body to true
            /*
             * there is another element that evaluates body to true so we return false and end the evaluation
             */
            return BooleanValue.FALSE;
        } else {
            /*
             * is false => previously we did not found an objet which evaluates body to true So this is the
             * first element (and, until now, the only one) which is evaluated to true
             */
            accumulator.setValue(BooleanValue.TRUE);
            return null; //but it could be another element not evaluated yet that also returs true => we cannot stop the evaluation
        }
    }

    @Override
    public OclValueIndirection<BooleanValue> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceVal, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        OclValueIndirection<BooleanValue> accum = new OclValueIndirection<>();
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
    public OclValue<?> extractResult(OclValueIndirection<BooleanValue> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        assert accumulator.getValue() != null;
        return accumulator.getValue();
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return One.class;
    }

    private static class CollectionOneEvaluatorHolder {

        private static final CollectionOneEvaluator INSTANCE = new CollectionOneEvaluator();
    }
}
