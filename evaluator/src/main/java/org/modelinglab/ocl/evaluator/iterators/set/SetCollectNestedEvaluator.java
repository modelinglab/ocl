/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.set.CollectNested;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractCollectNestedEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetCollectNestedEvaluator extends AbstractCollectNestedEvaluator {
    private static final long serialVersionUID = 1L;
    
    private SetCollectNestedEvaluator() {
    }
    
    public static SetCollectNestedEvaluator getInstance() {
        return SetCollectNestedEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        CollectNested collectNestedExp = (CollectNested) itExp;
        return new BagValue<>(accumulator.getValue(), collectNestedExp.getType().getElementType());
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return CollectNested.class;
    }
    
    private static class SetCollectNestedEvaluatorHolder {

        private static final SetCollectNestedEvaluator INSTANCE = new SetCollectNestedEvaluator();
    }
}
