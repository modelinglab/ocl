/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import org.modelinglab.ocl.core.values.*;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 * This class delegates ValueVisitor visit methods to 
 * {@link AnyOperatorEvaluatorTemplate#evaluate(org.modelinglab.ocl.core.values.OclValue<?>, org.modelinglab.ocl.evaluator.operations.OperationEvaluator.SwitchArgument) },
 * an abstract method that subclasses should implement.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class AnyOperatorEvaluatorTemplate extends OperationEvaluator {

    protected abstract OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg);
    
    @Override
    public OclValue<?> visit(BooleanValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(BagValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(ObjectValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(EnumValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(IntegerValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(NaturalValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(OrderedSetValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(RealValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(SequenceValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(SetValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(StringValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(ClassifierValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }

    @Override
    public OclValue<?> visit(TupleValue val, SwitchArgument arg) {
        return evaluate(val, arg);
    }
    
}
