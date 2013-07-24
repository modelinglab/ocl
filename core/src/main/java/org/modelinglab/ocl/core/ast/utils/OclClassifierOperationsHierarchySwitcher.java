/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.DataType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.MessageType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclClassifierOperationsHierarchySwitcher extends OclVisitorAdapter<Set<Classifier>, Void> {

    private final Set<Classifier> any, bag, collection, invalid, message, orderedSet,
            booleanType, stringType, integerType, unlimitedNaturalType, realType,
            sequence, template, tuple, set, umlClass, umlEnum, voidType, classifierType;

    private OclClassifierOperationsHierarchySwitcher() {
        any = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                AnyType.getInstance()
        )));
        invalid = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                InvalidType.getInstance(),
                VoidType.getInstance(),
                AnyType.getInstance()
        )));
        voidType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                VoidType.getInstance(),
                AnyType.getInstance()
        )));
        message = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                MessageType.getGenericInstance(),
                AnyType.getInstance()
        )));
        booleanType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN),
                AnyType.getInstance()
        )));
        stringType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING),
                AnyType.getInstance()
        )));
        integerType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER),
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL),
                AnyType.getInstance()
        )));
        unlimitedNaturalType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.UNLIMITED_NATURAL),
                AnyType.getInstance()
        )));
        realType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL),
                AnyType.getInstance()
        )));
        template = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                TemplateParameterType.getGenericCollectionElement(),
                AnyType.getInstance()
        )));
        tuple = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                TupleType.getGenericInstance(),
                AnyType.getInstance()
        )));
        umlClass = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                UmlClass.getGenericInstance(),
                AnyType.getInstance()
        )));
        umlEnum = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                UmlEnum.getGenericInstance(),
                UmlClass.getGenericInstance(),
                AnyType.getInstance()
        )));
        classifierType = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
//                AnyType.getInstance().getClassifierType(),
                AnyType.getInstance()
        )));
        
        collection = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                CollectionType.getGenericInstance(),
                AnyType.getInstance()
        )));
        set = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                SetType.getGenericInstance(),
                CollectionType.getGenericInstance(),
                AnyType.getInstance()
        )));
        orderedSet = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                OrderedSetType.getGenericInstance(),
                CollectionType.getGenericInstance(),
                AnyType.getInstance()
        )));
        sequence = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                SequenceType.getGenericInstance(),
                CollectionType.getGenericInstance(),
                AnyType.getInstance()
        )));
        bag = Collections.unmodifiableSet(new HashSet<Classifier>(Arrays.asList(
                BagType.getGenericInstance(),
                CollectionType.getGenericInstance(),
                AnyType.getInstance()
        )));
        
    }

    public static OclClassifierOperationsHierarchySwitcher getInstance() {
        return ClassifierConformsSwitcherHolder.INSTANCE;
    }

    @Override
    public Set<Classifier> visit(AnyType anyType, Void argument) {
        return any;
    }

    @Override
    public Set<Classifier> visit(BagType bagType, Void argument) {
        return bag;
    }

    @Override
    public Set<Classifier> visit(Classifier classifier, Void argument) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Set<Classifier> visit(CollectionType collectionType, Void argument) {
        return collection;
    }

    @Override
    public Set<Classifier> visit(DataType dataType, Void argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Classifier> visit(InvalidType invalidType, Void argument) {
        return invalid;
    }

    @Override
    public Set<Classifier> visit(MessageType messageType, Void argument) {
        return message;
    }

    @Override
    public Set<Classifier> visit(OrderedSetType orderedSetType, Void argument) {
        return orderedSet;
    }

    @Override
    public Set<Classifier> visit(PrimitiveType primitiveType, Void argument) {
        switch (primitiveType.getPrimitiveKind()) {
            case BOOLEAN:
                return booleanType;
            case INTEGER:
                return integerType;
            case REAL:
                return realType;
            case STRING:
                return stringType;
            case UNLIMITED_NATURAL:
                return unlimitedNaturalType;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Set<Classifier> visit(SequenceType sequenceType, Void argument) {
        return sequence;
    }

    @Override
    public Set<Classifier> visit(TemplateParameterType templateParameterType, Void argument) {
        return template;
    }

    @Override
    public Set<Classifier> visit(TupleType tupleType, Void argument) {
        return tuple;
    }

    @Override
    public Set<Classifier> visit(SetType setType, Void argument) {
        return set;
    }

    @Override
    public Set<Classifier> visit(UmlClass umlClass, Void argument) {
        return this.umlClass;
    }

    @Override
    public Set<Classifier> visit(UmlEnum umlEnum, Void argument) {
        return this.umlEnum;
    }

    @Override
    public Set<Classifier> visit(VoidType voidType, Void argument) {
        return this.voidType;
    }

    @Override
    public Set<Classifier> visit(ClassifierType classifierType, Void arguments) {
        Set<Classifier> result = new HashSet<>(this.classifierType.size() + 1);
        result.addAll(this.classifierType);
        result.add(classifierType);
        return result;
    }

    private static class ClassifierConformsSwitcherHolder {

        private static final OclClassifierOperationsHierarchySwitcher INSTANCE = new OclClassifierOperationsHierarchySwitcher();

        private ClassifierConformsSwitcherHolder() {
        }
    }
}
