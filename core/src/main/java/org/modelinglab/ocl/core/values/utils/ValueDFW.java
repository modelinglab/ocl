/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.values.utils;

import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.NaturalValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.TupleValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ValueDFW<Argument> implements ValueVisitor<Void, Argument> {

    public void preBooleanValue(BooleanValue val, Argument arg) {}
    public void postBooleanValue(BooleanValue val, Argument arg) {}
    
    @Override
    public Void visit(BooleanValue val, Argument arg) {
        preBooleanValue(val, arg);
        postBooleanValue(val, arg);
        return null;
    }
    
    public void preBagValue(BagValue<? extends OclValue<?>> val, Argument arg) {}
    public void postBagValue(BagValue<? extends OclValue<?>> val, Argument arg) {}
    
    @Override
    public Void visit(BagValue<? extends OclValue<?>> val, Argument arg) {
        preBagValue(val, arg);
        
        for (final OclValue<?> oclValue : val.getValue()) {
            oclValue.accept(this, arg);
        }
        
        postBagValue(val, arg);
        return null;
    }

    public void preObjectValue(ObjectValue<?> val, Argument arg) {}
    public void postObjectValue(ObjectValue<?> val, Argument arg) {}
    
    @Override
    public Void visit(ObjectValue<?> val, Argument arg) {
        preObjectValue(val, arg);
        postObjectValue(val, arg);
        return null;
    }

    public void preEnumValue(EnumValue val, Argument arg) {}
    public void postEnumValue(EnumValue val, Argument arg) {}
    
    @Override
    public Void visit(EnumValue val, Argument arg) {
        preEnumValue(val, arg);
        postEnumValue(val, arg);
        return null;
    }

    public void preIntegerValue(IntegerValue val, Argument arg) {}
    public void postIntegerValue(IntegerValue val, Argument arg) {}
    
    @Override
    public Void visit(IntegerValue val, Argument arg) {
        preIntegerValue(val, arg);
        postIntegerValue(val, arg);
        return null;
    }

    public void preInvalidValue(InvalidValue val, Argument arg) {}
    public void postInvalidValue(InvalidValue val, Argument arg) {}
    
    @Override
    public Void visit(InvalidValue val, Argument arg) {
        preInvalidValue(val, arg);
        postInvalidValue(val, arg);
        return null;
    }

    public void preNaturalValue(NaturalValue val, Argument arg) {}
    public void postNaturalValue(NaturalValue val, Argument arg) {}
    
    @Override
    public Void visit(NaturalValue val, Argument arg) {
        preNaturalValue(val, arg);
        postNaturalValue(val, arg);
        return null;
    }

    public void preOrderedSetValue(OrderedSetValue<? extends OclValue<?>> val, Argument arg) {}
    public void postOrderedSetValue(OrderedSetValue<? extends OclValue<?>> val, Argument arg) {}
    
    @Override
    public Void visit(OrderedSetValue<? extends OclValue<?>> val, Argument arg) {
        preOrderedSetValue(val, arg);
        
        for (final OclValue<?> oclValue : val.getValue()) {
            oclValue.accept(this, arg);
        }
        
        postOrderedSetValue(val, arg);
        return null;
    }

    public void preRealValue(RealValue val, Argument arg) {}
    public void postRealValue(RealValue val, Argument arg) {}
    
    @Override
    public Void visit(RealValue val, Argument arg) {
        preRealValue(val, arg);
        postRealValue(val, arg);
        return null;
    }

    public void preSequenceValue(SequenceValue<? extends OclValue<?>> val, Argument arg) {}
    public void postSequenceValue(SequenceValue<? extends OclValue<?>> val, Argument arg) {}
    
    @Override
    public Void visit(SequenceValue<? extends OclValue<?>> val, Argument arg) {
        preSequenceValue(val, arg);
        
        for (final OclValue<?> oclValue : val.getValue()) {
            oclValue.accept(this, arg);
        }
        
        postSequenceValue(val, arg);
        return null;
    }

    public void preSetValue(SetValue<? extends OclValue<?>> val, Argument arg) {}
    public void postSetValue(SetValue<? extends OclValue<?>> val, Argument arg) {}
    
    @Override
    public Void visit(SetValue<? extends OclValue<?>> val, Argument arg) {
        preSetValue(val, arg);
        
        for (final OclValue<?> oclValue : val.getValue()) {
            oclValue.accept(this, arg);
        }
        
        postSetValue(val, arg);
        return null;
    }

    public void preStringValue(StringValue val, Argument arg) {}
    public void postStringValue(StringValue val, Argument arg) {}
    
    @Override
    public Void visit(StringValue val, Argument arg) {
        preStringValue(val, arg);
        postStringValue(val, arg);
        return null;
    }

    public void preClassifierValue(ClassifierValue val, Argument arg) {}
    public void postClassifierValue(ClassifierValue val, Argument arg) {}
    
    @Override
    public Void visit(ClassifierValue val, Argument arg) {
        preClassifierValue(val, arg);
        postClassifierValue(val, arg);
        return null;
    }

    public void preTupleValue(TupleValue val, Argument arg) {}
    public void postTupleValue(TupleValue val, Argument arg) {}
    
    @Override
    public Void visit(TupleValue val, Argument arg) {
        preTupleValue(val, arg);
        
        for (final OclValue<?> oclValue : val.getValue().values()) {
            oclValue.accept(this, arg);
        }
        
        postTupleValue(val, arg);
        return null;
    }

    public void preVoidValue(VoidValue val, Argument arg) {}
    public void postVoidValue(VoidValue val, Argument arg) {}
    
    @Override
    public Void visit(VoidValue val, Argument arg) {
        preVoidValue(val, arg);
        postVoidValue(val, arg);
        return null;
    }
    
}
