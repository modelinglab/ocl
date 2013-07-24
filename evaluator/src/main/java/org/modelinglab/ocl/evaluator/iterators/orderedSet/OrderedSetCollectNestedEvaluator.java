/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.orderedSet.CollectNested;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectNestedEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetCollectNestedEvaluator extends AbstractCollectNestedEvaluator {
    
    private OrderedSetCollectNestedEvaluator() {
    }
    
    public static OrderedSetCollectNestedEvaluator getInstance() {
        return OrderedSetCollectNestedEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        CollectNested collectNestedExp = (CollectNested) itExp;
        return new SequenceValue<>(accumulator.getValue(), collectNestedExp.getType().getElementType());
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return CollectNested.class;
    }
    
    private static class OrderedSetCollectNestedEvaluatorHolder {

        private static final OrderedSetCollectNestedEvaluator INSTANCE = new OrderedSetCollectNestedEvaluator();
    }
}
