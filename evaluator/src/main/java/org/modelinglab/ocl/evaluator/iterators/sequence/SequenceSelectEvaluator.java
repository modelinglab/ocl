/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.sequence;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.sequence.Select;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSelectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SequenceSelectEvaluator extends AbstractSelectEvaluator{
    
    private SequenceSelectEvaluator() {
    }
    
    public static SequenceSelectEvaluator getInstance() {
        return SequenceSelectEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new SequenceValue<>(accumulator.getValue(), sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Select.class;
    }
    
    private static class SequenceSelectEvaluatorHolder {

        private static final SequenceSelectEvaluator INSTANCE = new SequenceSelectEvaluator();
    }
}
