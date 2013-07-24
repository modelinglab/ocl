/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.bag;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.standard.iterators.bag.SortedBy;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSortedByEvaluator;
import org.modelinglab.ocl.evaluator.iterators.set.SetSortedByEvaluator;

/**
 *
 */
public class BagSortedByEvaluator extends AbstractSortedByEvaluator {

    private BagSortedByEvaluator() {
    }

    @Override
    public OclValue<?> createReturnValue(CollectionType sourceType, List<OclValue<?>> sortedList) {
        return new SequenceValue<>(sortedList, sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return SortedBy.class;
    }

    public static BagSortedByEvaluator getInstance() {
        return BagSortedByEvaluatorHolder.INSTANCE;
    }

    private static class BagSortedByEvaluatorHolder {

        private static final BagSortedByEvaluator INSTANCE = new BagSortedByEvaluator();
    }
}
