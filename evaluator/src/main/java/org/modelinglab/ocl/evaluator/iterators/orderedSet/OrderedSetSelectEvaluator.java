/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.orderedSet;

import java.util.LinkedList;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.standard.iterators.orderedSet.Select;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.iterators.generic.AbstractSelectEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OrderedSetSelectEvaluator extends AbstractSelectEvaluator {

    private OrderedSetSelectEvaluator() {
    }

    public static OrderedSetSelectEvaluator getInstance() {
        return OrderedSetSelectEvaluatorHolder.INSTANCE;
    }

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        List<OclValue<?>> withNull = accumulator.getValue();
        LinkedList<OclValue<?>> withoutNull = new LinkedList<>();

        for (final OclValue<?> oclValue : withNull) {
            if (oclValue != null) {
                withoutNull.add(oclValue);
            }
        }
        return new OrderedSetValue<>(withoutNull, sourceType.getElementType());
    }

    @Override
    public Class<? extends IteratorExp> getEvaluableIterator() {
        return Select.class;
    }

    private static class OrderedSetSelectEvaluatorHolder {

        private static final OrderedSetSelectEvaluator INSTANCE = new OrderedSetSelectEvaluator();
    }
}
