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
public interface ValueVisitor<Result, Arg> {
    
    public Result visit(BooleanValue val, Arg arg);
    public Result visit(BagValue<? extends OclValue<?>> val, Arg arg);
    public Result visit(ObjectValue<?> val, Arg arg);
    public Result visit(EnumValue val, Arg arg);
    public Result visit(IntegerValue val, Arg arg);
    public Result visit(InvalidValue val, Arg arg);
    public Result visit(NaturalValue val, Arg arg);
    public Result visit(OrderedSetValue<? extends OclValue<?>> val, Arg arg);
    public Result visit(RealValue val, Arg arg);
    public Result visit(SequenceValue<? extends OclValue<?>> val, Arg arg);
    public Result visit(SetValue<? extends OclValue<?>> val, Arg arg);
    public Result visit(StringValue val, Arg arg);
    public Result visit(ClassifierValue val, Arg arg);
    public Result visit(TupleValue val, Arg arg);
    public Result visit(VoidValue val, Arg arg);
}

