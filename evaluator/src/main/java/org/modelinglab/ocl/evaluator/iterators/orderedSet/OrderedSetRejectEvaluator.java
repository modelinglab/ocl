/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.orderedSet.Reject;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractRejectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetRejectEvaluator extends AbstractRejectEvaluator {
    
    private OrderedSetRejectEvaluator() {
    }
    
    public static OrderedSetRejectEvaluator getInstance() {
        return OrderedSetRejectEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new OrderedSetValue<>(accumulator.getValue(), sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Reject.class;
    }
    
    private static class OrderedSetRejectEvaluatorHolder {

        private static final OrderedSetRejectEvaluator INSTANCE = new OrderedSetRejectEvaluator();
    }
}
