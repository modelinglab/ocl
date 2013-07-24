/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values.utils;

import org.modelinglab.ocl.core.values.*;

/**
 *
 * @param <Result> 
 * @param <Arg> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ValueVisitorAdapter<Result, Arg> implements ValueVisitor<Result, Arg> {

    protected Result defaultCase(OclValue<?> val, Arg arg) {
        return null;
    }
    
    @Override
    public Result visit(BooleanValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(BagValue<? extends OclValue<?>> val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(ObjectValue<?> val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(EnumValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(IntegerValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(InvalidValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(NaturalValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(OrderedSetValue<? extends OclValue<?>> val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(RealValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(SequenceValue<? extends OclValue<?>> val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(SetValue<? extends OclValue<?>> val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(StringValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(ClassifierValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(TupleValue val, Arg arg) {
        return defaultCase(val, arg);
    }

    @Override
    public Result visit(VoidValue val, Arg arg) {
        return defaultCase(val, arg);
    }
    
}
