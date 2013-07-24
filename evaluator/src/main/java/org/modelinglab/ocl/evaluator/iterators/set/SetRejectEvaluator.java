/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.set.Reject;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractRejectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetRejectEvaluator extends AbstractRejectEvaluator {
    private static final long serialVersionUID = 1L;
    
    private SetRejectEvaluator() {
    }
    
    public static SetRejectEvaluator getInstance() {
        return SetRejectEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new SetValue<>(accumulator.getValue(), sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Reject.class;
    }
    
    private static class SetRejectEvaluatorHolder {

        private static final SetRejectEvaluator INSTANCE = new SetRejectEvaluator();
    }
}
