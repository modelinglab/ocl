/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.standard.iterators.sequence.Collect;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceCollectEvaluator extends AbstractCollectEvaluator {
    private static final long serialVersionUID = 1L;
    
    private SequenceCollectEvaluator() {
    }
    
    public static SequenceCollectEvaluator getInstance() {
        return SequenceCollectEvaluatorHolder.INSTANCE;
    }

    @Override
    public Classifier getElementResultType(IteratorExp expression) {
        return ((Collect) expression).getType().getElementType();
    }

    @Override
    public OclValue<?> getResult(SequenceValue<? extends OclValue<?>> sequenceResult) {
        return sequenceResult;
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Collect.class;
    }
    
    private static class SequenceCollectEvaluatorHolder {

        private static final SequenceCollectEvaluator INSTANCE = new SequenceCollectEvaluator();
    }
}
