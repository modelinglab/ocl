/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.set;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.set.Select;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSelectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class SetSelectEvaluator  extends AbstractSelectEvaluator {

    private static final long serialVersionUID = 1L;
    
    private SetSelectEvaluator() {
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        return new SetValue<>(accumulator.getValue(), sourceType.getElementType(), true);
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Select.class;
    }
    
    public static SetSelectEvaluator getInstance() {
        return SetSelectEvaluatorHolder.INSTANCE;
    }
    
    private static class SetSelectEvaluatorHolder {

        private static final SetSelectEvaluator INSTANCE = new SetSelectEvaluator();
    }
}
