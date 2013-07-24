/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.iterators.orderedSet.Collect;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetCollectEvaluator extends AbstractCollectEvaluator {
    private static final long serialVersionUID = 1L;
    
    private OrderedSetCollectEvaluator() {
    }
    
    public static OrderedSetCollectEvaluator getInstance() {
        return OrderedSetCollectEvaluatorHolder.INSTANCE;
    }

    @Override
    protected Classifier getElementResultType(IteratorExp expression) {
        return ((Collect) expression).getType().getElementType();
    }

    @Override
    protected OclValue<?> getResult(SequenceValue<? extends OclValue<?>> sequenceResult) {
        return sequenceResult;
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Collect.class;
    }
    
    private static class OrderedSetCollectEvaluatorHolder {

        private static final OrderedSetCollectEvaluator INSTANCE = new OrderedSetCollectEvaluator();
    }
}
