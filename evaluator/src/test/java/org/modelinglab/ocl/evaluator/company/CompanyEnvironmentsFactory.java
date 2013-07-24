/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.company;

import java.math.BigInteger;
import java.util.*;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.OperationsStore;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.UmlObject.NotInitializedProperty;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitorAdaptor;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.OrderedSetValue;
import org.modelinglab.ocl.core.values.RealValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.vartables.IVariableTable;
import org.modelinglab.ocl.core.vartables.VariableAlreadyExistException;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.evaluator.UserEvaluationEnvironment;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.utils.environments.CompanyEnvironment;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CompanyEnvironmentsFactory {

    private final ClassifierVisitor<OclValue<?>, UserEvaluationEnvironment> randomGenerator;
    private final ClassifierVisitor<OclValue<?>, UserEvaluationEnvironment> variableGenerator;

    public CompanyEnvironmentsFactory() {
        randomGenerator = new PropertyRandomGenerator();
        variableGenerator = new VariableRandomGenerator();
    }

    public StaticEnvironment createStaticEnv() {
        return CompanyEnvironment.createEnvironment();
    }

    public UserEvaluationEnvironment createEvaluationEnv(StaticEnvironment staticEnv) throws NotInitializedProperty {
        MemoryInstancesProviderBuilder mipc = new MemoryInstancesProviderBuilder();

        Collection<UmlClass> classes = new LinkedList<>();
        for (final Element element : staticEnv.getOwnedMembers()) {
            if (element instanceof UmlClass) {
                final UmlClass clazz = (UmlClass) element;
                classes.add(clazz);
                
                if (!(clazz instanceof UmlEnum)) {
                    for (int i = 0; i < 10; i++) {
                        createInstance(clazz, staticEnv, mipc, i);
                    }
                }
            }
        }
        
        OperationsStore opStore = new OperationsStore.OperationsStoreFactory().createStore();
        UserEvaluationEnvironment env = new UserEvaluationEnvironment(
                classes, mipc.createInstancesProvider(), Collections.<OperationEvaluator>emptySet(), opStore);
        return env;
    }

    private void createInstance(UmlClass umlClass, StaticEnvironment staticEnv, MemoryInstancesProviderBuilder mipc, int i) throws NotInitializedProperty {

        Map<String, OclValue<?>> propertyMap = new HashMap<>();
        for (final Attribute attribute : umlClass.getAllAttributes()) {
            propertyMap.put(attribute.getName(), (OclValue) attribute.getType().accept(randomGenerator, null));
        }
        for (final AssociationEnd associationEnd : umlClass.getAllAssociationEnds()) {
            propertyMap.put(associationEnd.getName(), (OclValue) associationEnd.getType().accept(randomGenerator, null));
        }
        MemoryUmlObject object;
        object = new MemoryUmlObject(umlClass, propertyMap);

        mipc.addInstance(umlClass, object);
    }
    
    public IVariableTable createRandomVariableTable(StaticEnvironment staticEnv, UserEvaluationEnvironment evalEnv) throws VariableAlreadyExistException {
        VariableTable varTable = new VariableTable();

        for (final Element element : staticEnv.getOwnedMembers()) {
            if (element instanceof Variable) {
                Variable var = (Variable) element;
                varTable.createVariable(var, (OclValue) var.getType().accept(variableGenerator, evalEnv));
            }
        }
        return varTable;
    }

    
    

    private static class PropertyRandomGenerator extends ClassifierVisitorAdaptor<OclValue<?>, UserEvaluationEnvironment> {

        protected final Random random = new Random();

        @Override
        public OclValue<?> visit(AnyType anyType, UserEvaluationEnvironment argument) {
            return VoidValue.instantiate();
        }

        @Override
        public OclValue<?> visit(InvalidType invalidType, UserEvaluationEnvironment argument) {
            return InvalidValue.instantiate();
        }

        @Override
        public OclValue<?> visit(BagType bagType, UserEvaluationEnvironment argument) {
            ArrayList<OclValue<?>> values = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                values.add(bagType.getElementType().accept(this, argument));
            }
            return new BagValue<>(values, bagType.getElementType());
        }

        @Override
        public OclValue<?> visit(OrderedSetType orderedSetType, UserEvaluationEnvironment argument) {
            Set<OclValue<?>> values = new HashSet<>(10);
            for (int i = 0; i < 10; i++) {
                values.add(orderedSetType.getElementType().accept(this, argument));
            }
            return new OrderedSetValue<>(values, orderedSetType.getElementType());
        }

        @Override
        public OclValue<?> visit(SequenceType sequenceType, UserEvaluationEnvironment argument) {
            ArrayList<OclValue<?>> values = new ArrayList<>(10);
            for (int i = 0; i < 10; i++) {
                values.add(sequenceType.getElementType().accept(this, argument));
            }
            return new SequenceValue<>(values, sequenceType.getElementType());
        }

        @Override
        public OclValue<?> visit(SetType setType, UserEvaluationEnvironment argument) {
            Set<OclValue<?>> values = new HashSet<>(10);
            for (int i = 0; i < 10; i++) {
                values.add(setType.getElementType().accept(this, argument));
            }
            return new SetValue<>(values, setType.getElementType());
        }

        @Override
        public OclValue<?> visit(PrimitiveType primitiveType, UserEvaluationEnvironment argument) {
            switch (primitiveType.getPrimitiveKind()) {
                case BOOLEAN:
                    if (random.nextBoolean()) {
                        return BooleanValue.TRUE;
                    } else {
                        return BooleanValue.FALSE;
                    }
                case INTEGER:
                    return new IntegerValue(random.nextLong());
                case REAL:
                    return new RealValue(random.nextDouble());
                case STRING:
                    return new StringValue(new BigInteger(130, random).toString(32));
                default:
                    return VoidValue.instantiate();
            }
        }
    }

    private static class VariableRandomGenerator extends PropertyRandomGenerator {

        @Override
        public OclValue<?> visit(UmlClass clazz, UserEvaluationEnvironment argument) {
            SetValue<ObjectValue<?>> instances = argument.getInstancesProvider().getAllInstances(clazz);
            
            Collection<ObjectValue<?>> values = instances.getValue();
            int size = values.size();
            int selected = random.nextInt(size);
            ObjectValue<?> value = null;
            int i = 0;
            for (final ObjectValue<?> objectValue : values) {
                i++;
                if (i > selected) {
                    value = objectValue;
                    break;
                }
            }
            assert value != null;
            return value;            
        }

        @Override
        public OclValue<?> visit(UmlEnum umlEnum, UserEvaluationEnvironment argument) {
            List<UmlEnumLiteral> values = umlEnum.getLiterals();
            
            int size = values.size();
            int selected = random.nextInt(size);
            
            UmlEnumLiteral randomLiteral = values.get(selected);
            return new EnumValue(randomLiteral);
        }
    }
}
