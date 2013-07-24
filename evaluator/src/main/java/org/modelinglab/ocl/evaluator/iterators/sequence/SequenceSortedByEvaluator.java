/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.sequence.SortedBy;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSortedByEvaluator;

/**
 *
 */
public class SequenceSortedByEvaluator extends AbstractSortedByEvaluator {
    
    private SequenceSortedByEvaluator() {
    }

    @Override
    public OclValue<?> createReturnValue(CollectionType sourceType, List<OclValue<?>> sortedList) {
        return new SequenceValue<>(sortedList, sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return SortedBy.class;
    }
    
    public static SequenceSortedByEvaluator getInstance() {
        return SequenceSortedByEvaluatorHolder.INSTANCE;
    }
    
    private static class SequenceSortedByEvaluatorHolder {

        private static final SequenceSortedByEvaluator INSTANCE = new SequenceSortedByEvaluator();
    }
}
