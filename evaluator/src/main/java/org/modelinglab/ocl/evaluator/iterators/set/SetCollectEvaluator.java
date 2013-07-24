/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.iterators.set.Collect;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetCollectEvaluator extends AbstractCollectEvaluator {
    private static final long serialVersionUID = 1L;
    
    private SetCollectEvaluator() {
    }
    
    public static SetCollectEvaluator getInstance() {
        return SetCollectEvaluatorHolder.INSTANCE;
    }

    @Override
    protected Classifier getElementResultType(IteratorExp expression) {
        return ((Collect) expression).getType().getElementType();
    }

    @Override
    protected OclValue<?> getResult(SequenceValue<? extends OclValue<?>> sequenceResult) {
        return new BagValue<>(sequenceResult);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Collect.class;
    }
    
    private static class SetCollectEvaluatorHolder {

        private static final SetCollectEvaluator INSTANCE = new SetCollectEvaluator();
    }
}
