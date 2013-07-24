/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.set.SortedBy;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSortedByEvaluator;

/**
 *
 */
public class SetSortedByEvaluator extends AbstractSortedByEvaluator {
    
    private SetSortedByEvaluator() {
    }

    @Override
    public OclValue<?> createReturnValue(CollectionType sourceType, List<OclValue<?>> sortedList) {
        return new OrderedSetValue<>(sortedList, sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return SortedBy.class;
    }
    
    public static SetSortedByEvaluator getInstance() {
        return SetSortedByEvaluatorHolder.INSTANCE;
    }
    
    private static class SetSortedByEvaluatorHolder {

        private static final SetSortedByEvaluator INSTANCE = new SetSortedByEvaluator();
    }
}
