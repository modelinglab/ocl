/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.collection;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.iterators.collection.Collect;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class CollectionCollectEvaluator extends AbstractCollectEvaluator {
    private static final long serialVersionUID = 1L;

    private CollectionCollectEvaluator() {
    }

    public static CollectionCollectEvaluator getInstance() {
        return CollectionCollectEvaluatorHolder.INSTANCE;
    }

    @Override
    protected Classifier getElementResultType(IteratorExp expression) {
        return ((Collect) expression).getType();
    }

    @Override
    protected OclValue<?> getResult(SequenceValue<? extends OclValue<?>> sequenceResult) {
        return sequenceResult;
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Collect.class;
    }

    private static class CollectionCollectEvaluatorHolder {

        private static final CollectionCollectEvaluator INSTANCE = new CollectionCollectEvaluator();

        private CollectionCollectEvaluatorHolder() {
        }
    }
}
