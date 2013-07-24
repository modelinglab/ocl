/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.values.utils.ValueVisitorAdapter;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class IteratorEvaluationAlgorithm extends ValueVisitorAdapter<OclValue<?>, IteratorEvaluationAlgorithm.SwitchArgument<?>> {

    public abstract <E> OclValue<?> evaluateSingleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException;

    public abstract <E> OclValue<?> evaluateDoubleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException;

    protected <E> OclValue<?> evaluate(CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        switch (itExp.getIterators().size()) {
            case 1:
                return evaluateSingleItVar(sourceType, sourceAsList, itExp, concreteEvaluator, runtimeEnv);
            case 2:
                return evaluateDoubleItVar(sourceType, sourceAsList, itExp, concreteEvaluator, runtimeEnv);
            default:
                throw new OclEvaluationException(itExp, "Iterator expressions should have 1 or 2 iterator variables.");
        }
    }

    public void checkCanBeEvaluated(IteratorExp exp, IteratorEvaluator<?> evaluator, OclValue<?> source) throws OclEvaluationException {
        if (!evaluator.getEvaluableIterator().equals(exp.getClass())) {
            throw new OclEvaluationException(exp, exp + " is not evaluable by the selected evaluator!");
        }
    }

    /**
     * This method is called when the source value does not conform with any visit method overrided by
     * IteratorEvaluator subclasses. This can happen when subclass is not correctly implemented or when source
     * value does not conform with expected value, but this case should be detected by evaluator
     * implementation.
     *
     * @param val
     * @param arg
     *
     * @return
     */
    @Override
    protected OclValue<?> defaultCase(OclValue<?> val, IteratorEvaluationAlgorithm.SwitchArgument<?> arg) {
        throw new AssertionError("Source was evaluated to " + val + " with type '"
                + val.getType() + "' does not conform to the expected type.");
    }

    /**
     * By default, operations called over a InvalidValue source returns InvalidValue
     *
     * @param val
     * @param arg
     *
     * @return an instance of InvalidValue
     */
    @Override
    public OclValue<?> visit(InvalidValue val, IteratorEvaluationAlgorithm.SwitchArgument<?> arg) {
        return val;
    }

    /**
     * By default, operations called over a VoidValue source returns InvalidValue
     *
     * @param val
     * @param arg
     *
     * @return an instance of InvalidValue
     */
    @Override
    public OclValue<?> visit(VoidValue val, IteratorEvaluationAlgorithm.SwitchArgument<?> arg) {
        return InvalidValue.instantiate();
    }

    @Override
    public OclValue<?> visit(BagValue<? extends OclValue<?>> val, SwitchArgument<?> arg) {
        return evaluate(val.getType(), val.getValue(), arg.itExp, arg.concreteEvaluator, arg.runtimeEnv);
    }

    @Override
    public OclValue<?> visit(OrderedSetValue<? extends OclValue<?>> val, SwitchArgument<?> arg) {
        return evaluate(val.getType(), val.getValue(), arg.itExp, arg.concreteEvaluator, arg.runtimeEnv);
    }

    @Override
    public OclValue<?> visit(SequenceValue<? extends OclValue<?>> val, SwitchArgument<?> arg) {
        return evaluate(val.getType(), val.getValue(), arg.itExp, arg.concreteEvaluator, arg.runtimeEnv);
    }

    @Override
    public OclValue<?> visit(SetValue<? extends OclValue<?>> val, SwitchArgument<?> arg) {
        return evaluate(val.getType(), val.getValue(), arg.itExp, arg.concreteEvaluator, arg.runtimeEnv);
    }

    public static class SwitchArgument<E> {

        private final IteratorExp itExp;
        private final IteratorEvaluator<E> concreteEvaluator;
        private final EvaluatorVisitorArg runtimeEnv;

        public SwitchArgument(IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) {
            this.itExp = itExp;
            this.concreteEvaluator = concreteEvaluator;
            this.runtimeEnv = runtimeEnv;
        }
    }
}