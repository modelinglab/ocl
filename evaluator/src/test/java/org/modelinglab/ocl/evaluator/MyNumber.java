/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import java.util.List;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlObject;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 */
public class MyNumber extends UmlClass {

    public static MyNumber INSTANCE = new MyNumber();

    private MyNumber() {
        setName("MyNumber");

        Attribute value = new Attribute();
        value.setReferredType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        value.setName("value");

        addOwnedAttribute(value);


    }

    public static class MyNumberLessOperation extends Operation {

        public static MyNumberLessOperation INSTANCE = new MyNumberLessOperation(null);
        
        private MyNumberLessOperation(Operation templateOperation) {
            super(templateOperation);

            setName("<");
            setSource(MyNumber.INSTANCE);

            Parameter param = new Parameter();
            param.setName("other");
            param.setType(MyNumber.INSTANCE);
            addOwnedParameter(param);

            setType(PrimitiveType.getInstance(PrimitiveKind.BOOLEAN));
        }

        @Override
        public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
            return this;
        }
    }
    
    public static class MyNumberLessEvaluator extends OperationEvaluator {

        @Override
        public Operation getEvaluableOperation() {
            return MyNumberLessOperation.INSTANCE;
        }

        @Override
        public OclValue<?> visit(ObjectValue<?> val, SwitchArgument arg) {
            if (val.getValue().getUmlClass() instanceof MyNumber) {
                assert !arg.arguments.isEmpty();
                OclValue<?> argument = arg.arguments.get(0);
                if (argument.getType().oclIsUndefined()) {
                    return InvalidValue.instantiate();
                }
                
                ObjectValue<?> argVal = (ObjectValue<?>) argument;
                
                long l1 = (Long) val.getValue().getProperty("value").getValue();
                long l2 = (Long) argVal.getValue().getProperty("value").getValue();
                
                if (l1 < l2) {
                    return BooleanValue.TRUE;
                }
                return BooleanValue.FALSE;
                
            }
            else {
                throw new RuntimeException(val + " is not a " + MyNumber.INSTANCE.getName());
            }
        }
        
        
        
    }
    
    public static class MyNumberObject implements UmlObject<Long> {

        private final Long id;

        public MyNumberObject(Long id) {
            this.id = id;
        }
        
        @Override
        public UmlClass getUmlClass() {
            return MyNumber.INSTANCE;
        }

        @Override
        public Long getId() {
            return id;
        }

        @Override
        public OclValue<?> getProperty(String propertyName) throws IllegalArgumentException, NotInitializedProperty {
            switch (propertyName) {
                case "value" :
                    return new IntegerValue(id);
                default:
                    throw new IllegalArgumentException(propertyName + " is not defined at "+ getUmlClass());
            }
        }
        
    }
}
