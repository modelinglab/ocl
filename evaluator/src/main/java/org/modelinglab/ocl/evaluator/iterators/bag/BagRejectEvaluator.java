/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.bag;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.bag.Reject;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractRejectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagRejectEvaluator extends AbstractRejectEvaluator {
    private static final long serialVersionUID = 1L;
    
    private BagRejectEvaluator() {
    }
    
    public static BagRejectEvaluator getInstance() {
        return BagRejectEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new BagValue<>(accumulator.getValue(), sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Reject.class;
    }
    
    private static class BagRejectEvaluatorHolder {

        private static final BagRejectEvaluator INSTANCE = new BagRejectEvaluator();
    }
}
