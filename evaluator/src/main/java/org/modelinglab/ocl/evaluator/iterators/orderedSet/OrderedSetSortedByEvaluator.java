/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.orderedSet.SortedBy;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSortedByEvaluator;

/**
 *
 * @author gortiz
 */
public class OrderedSetSortedByEvaluator extends AbstractSortedByEvaluator {
    
    private OrderedSetSortedByEvaluator() {
    }

    @Override
    public OclValue<?> createReturnValue(CollectionType sourceType, List<OclValue<?>> sortedList) {
        return new OrderedSetValue<>(sortedList, sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return SortedBy.class;
    }
    
    public static OrderedSetSortedByEvaluator getInstance() {
        return OrderedSetSortedByEvaluatorHolder.INSTANCE;
    }
    
    private static class OrderedSetSortedByEvaluatorHolder {

        private static final OrderedSetSortedByEvaluator INSTANCE = new OrderedSetSortedByEvaluator();
    }
}
