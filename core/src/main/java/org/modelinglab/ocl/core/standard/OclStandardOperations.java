/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard;

import java.util.*;
import org.modelinglab.ocl.core.ast.Operation;
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
import org.modelinglab.ocl.core.ast.utils.OclVisitorAdapter;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclStandardOperations
        extends OclVisitorAdapter<Set<Operation>, String>
        implements Iterable<Operation> {

    private final Map<String, Set<Operation>> oclAnyOps;
    private final Map<String, Set<Operation>> oclInvalidOps;
    private final Map<String, Set<Operation>> oclVoidOps;
    private final Map<String, Set<Operation>> bagOps;
    private final Map<String, Set<Operation>> booleanOps;
    private final Map<String, Set<Operation>> collectionOps;
    private final Map<String, Set<Operation>> integerOps;
    private final Map<String, Set<Operation>> orderedSetOps;
    private final Map<String, Set<Operation>> realOps;
    private final Map<String, Set<Operation>> sequenceOps;
    private final Map<String, Set<Operation>> setOps;
    private final Map<String, Set<Operation>> stringOps;
    private final Map<String, Set<Operation>> classifierOps;
    private final Map<String, Set<Operation>> emptyOps;

    private OclStandardOperations() {

        Set<Operation> ops;

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.oclAny.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.oclAny.IsDifferent.getInstance(),
                org.modelinglab.ocl.core.standard.operations.oclAny.IsEqual.getInstance(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclAsType.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsInvalid.getInstance(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsKindOf.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsTypeOf.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclIsUndefined.getInstance(),
                org.modelinglab.ocl.core.standard.operations.oclAny.OclType.createTemplateOperation()));

        oclAnyOps = createMap(ops);

        ops = new HashSet<Operation>(1);
        ops.add(org.modelinglab.ocl.core.standard.operations.oclVoid.IsEqual.getInstance());
        oclVoidOps = createMap(ops);
        
        ops = new HashSet<Operation>();
        oclInvalidOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.bag.AsBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.AsOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.AsSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.Count.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.Excluding.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.Flatten.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.Including.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.IntersectionBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.IntersectionSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.IsEqual.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.UnionBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.bag.UnionSet.createTemplateOperation()));
        bagOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.bool.And.getInstance(),
                org.modelinglab.ocl.core.standard.operations.bool.Implies.getInstance(),
                org.modelinglab.ocl.core.standard.operations.bool.Not.getInstance(),
                org.modelinglab.ocl.core.standard.operations.bool.Or.getInstance(),
                org.modelinglab.ocl.core.standard.operations.bool.ToString.getInstance(),
                org.modelinglab.ocl.core.standard.operations.bool.Xor.getInstance()));
        booleanOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.collection.AsBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.AsOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.AsSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Count.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Excludes.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.ExcludesAll.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Flatten.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Includes.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.IncludesAll.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.IsDifferent.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.IsEmpty.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.IsEqual.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Max.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Min.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.NotEmpty.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Product.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Size.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.collection.Sum.createTemplateOperation()));
        collectionOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.integer.Abs.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Addition.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Division.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.IntegerDivision.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Max.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Min.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Mod.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Multiplication.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Negative.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.Substraction.getInstance(),
                org.modelinglab.ocl.core.standard.operations.integer.ToString.getInstance()));
        integerOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.orderedSet.Append.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.At.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.First.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.IndexOf.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.InsertAt.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.Last.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.Reverse.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.SubOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.orderedSet.Sum.createTemplateOperation()));
        orderedSetOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.real.Abs.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Addition.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Division.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Floor.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Greater.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.GreaterOrEqual.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Less.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.LessOrEqual.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Max.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Min.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Multiplication.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Negative.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Round.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.Substraction.getInstance(),
                org.modelinglab.ocl.core.standard.operations.real.ToString.getInstance()));
        realOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.sequence.Append.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.AsBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.AsOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.AsSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.At.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Count.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Excluding.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.First.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Flatten.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Including.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.IndexOf.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.InsertAt.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.IsEqual.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Last.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Prepend.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Reverse.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.SubSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Sum.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.sequence.Union.createTemplateOperation()));
        sequenceOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.set.AsBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.AsOrderedSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.AsSequence.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.AsSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.Count.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.Excluding.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.Flatten.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.Including.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.IntersectionBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.IntersectionSet.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.IsEqual.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.Substraction.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.SymmetricDifference.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.UnionBag.createTemplateOperation(),
                org.modelinglab.ocl.core.standard.operations.set.UnionSet.createTemplateOperation()));
        setOps = createMap(ops);

        ops = new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.string.Addition.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.At.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Characters.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Concat.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.EqualsIgnoreCase.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Greater.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.GreaterOrEqual.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.IndexOf.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Less.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.LessOrEqual.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Size.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.Substring.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.ToBoolean.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.ToInteger.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.ToLowerCase.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.ToReal.getInstance(),
                org.modelinglab.ocl.core.standard.operations.string.ToUpperCase.getInstance()));
        stringOps = createMap(ops);
        
        classifierOps = createMap(new HashSet<Operation>(Arrays.asList(
                org.modelinglab.ocl.core.standard.operations.umlClass.AllInstances.createTemplateOperation())));
        emptyOps = createMap(new HashSet<Operation>());
    }

    private Map<String, Set<Operation>> createMap(Set<Operation> ops) {
        Map<String, Set<Operation>> opMap = new HashMap<String, Set<Operation>>(ops.size());
        for (Operation operation : ops) {
            Set<Operation> sameName = opMap.get(operation.getName());
            if (sameName == null) {
                sameName = new HashSet<Operation>();
                opMap.put(operation.getName(), sameName);
            }
            sameName.add(operation);
        }
        return opMap;
    }

    public static OclStandardOperations getInstance() {
        return OclStandardOperationStoreHolder.INSTANCE;
    }
    
    private Set<Operation> getSet(Map<String, Set<Operation>> map, String argument) {
        if (argument == null) {
            Set<Operation> ops = new HashSet<Operation>();
            for (Set<Operation> set : map.values()) {
                ops.addAll(set);
            }
            return ops;
        }
        else {
            Set<Operation> op = map.get(argument);
            if (op == null) {
                return Collections.emptySet();
            }
            else {
                return Collections.unmodifiableSet(op);
            }
        }
    }

    @Override
    public Set<Operation> visit(AnyType anyType, String argument) {
        return getSet(oclAnyOps, argument);
    }

    @Override
    public Set<Operation> visit(BagType bagType, String argument) {
        return getSet(bagOps, argument);
    }

    @Override
    public Set<Operation> visit(Classifier classifier, String argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Operation> visit(CollectionType collectionType, String argument) {
        return getSet(collectionOps, argument);
    }

    @Override
    public Set<Operation> visit(DataType dataType, String argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Operation> visit(InvalidType invalidType, String argument) {
        return getSet(oclInvalidOps, argument);
    }

    @Override
    public Set<Operation> visit(MessageType messageType, String argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Operation> visit(OrderedSetType orderedSetType, String argument) {
        return getSet(orderedSetOps, argument);
    }

    @Override
    public Set<Operation> visit(PrimitiveType primitiveType, String argument) {
        switch (primitiveType.getPrimitiveKind()) {
            case BOOLEAN:
                return getSet(booleanOps, argument);
            case INTEGER:
                return getSet(integerOps, argument);
            case REAL:
                return getSet(realOps, argument);
            case STRING:
                return getSet(stringOps, argument);
            default:
                return Collections.emptySet();
        }
    }

    @Override
    public Set<Operation> visit(SequenceType sequenceType, String argument) {
        return getSet(sequenceOps, argument);
    }

    @Override
    public Set<Operation> visit(TemplateParameterType templateParameterType, String argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Operation> visit(TupleType tupleType, String argument) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Operation> visit(SetType setType, String argument) {
        return getSet(setOps, argument);
    }

    @Override
    public Set<Operation> visit(UmlClass umlClass, String argument) {
        return getSet(emptyOps, argument);
    }

    @Override
    public Set<Operation> visit(UmlEnum umlEnum, String argument) {
        return getSet(emptyOps, argument);
    }

    @Override
    public Set<Operation> visit(VoidType voidType, String argument) {
        return getSet(oclVoidOps, argument);
    }

    @Override
    public Set<Operation> visit(ClassifierType classifierType, String arguments) {
        return getSet(classifierOps, arguments);
    }

    @Override
    public Iterator<Operation> iterator() {
        return new OpIterator();
    }
    
    private class OpIterator implements Iterator<Operation> {
        
        Iterator<Set<Operation>> metaIterator;
        Iterator<Operation> actualIt;

        public OpIterator() {
            HashSet<Set<Operation>> sets = new HashSet<>();
            
            sets.addAll(oclAnyOps.values());
            sets.addAll(oclInvalidOps.values());
            sets.addAll(oclVoidOps.values());
            sets.addAll(bagOps.values());
            sets.addAll(booleanOps.values());
            sets.addAll(collectionOps.values());
            sets.addAll(integerOps.values());
            sets.addAll(orderedSetOps.values());
            sets.addAll(realOps.values());
            sets.addAll(sequenceOps.values());
            sets.addAll(setOps.values());
            sets.addAll(stringOps.values());
            sets.addAll(classifierOps.values());
            sets.addAll(emptyOps.values());
            
            this.metaIterator = sets.iterator();
        }

        @Override
        public boolean hasNext() {
            if (actualIt == null || !actualIt.hasNext()) {
                if (metaIterator.hasNext()) {
                    actualIt = metaIterator.next().iterator();
                    return hasNext();
                }
                return false;
            }
            return true;
        }

        @Override
        public Operation next() {
            return actualIt.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        
    }

    private static class OclStandardOperationStoreHolder {

        private static final OclStandardOperations INSTANCE = new OclStandardOperations();

        private OclStandardOperationStoreHolder() {
        }
    }
}
