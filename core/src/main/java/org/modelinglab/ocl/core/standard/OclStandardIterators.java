/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.core.standard.iterators.collection.Any;
import org.modelinglab.ocl.core.standard.iterators.collection.Exists;
import org.modelinglab.ocl.core.standard.iterators.collection.ForAll;
import org.modelinglab.ocl.core.standard.iterators.collection.IsUnique;
import org.modelinglab.ocl.core.standard.iterators.collection.One;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclStandardIterators {
    
    private final Map<String, Class<? extends IteratorExp>> bagIts, collectionIts, orderedSetIts, sequenceIts, setIts;
    
    private OclStandardIterators() {
        collectionIts = new HashMap<>(5);
        collectionIts.put("collect", org.modelinglab.ocl.core.standard.iterators.collection.Collect.class);
        collectionIts.put("exists", Exists.class);
        collectionIts.put("forAll", ForAll.class);
        collectionIts.put("isUnique", IsUnique.class);
        collectionIts.put("any", Any.class);
        collectionIts.put("one", One.class);
        
        setIts = new HashMap<>(5);
        setIts.put("collect", org.modelinglab.ocl.core.standard.iterators.set.Collect.class);
        setIts.put("select", org.modelinglab.ocl.core.standard.iterators.set.Select.class);
        setIts.put("reject", org.modelinglab.ocl.core.standard.iterators.set.Reject.class);
        setIts.put("collectNested", org.modelinglab.ocl.core.standard.iterators.set.CollectNested.class);
        setIts.put("sortedBy", org.modelinglab.ocl.core.standard.iterators.set.SortedBy.class);
        
        bagIts = new HashMap<>(5);
        bagIts.put("collect", org.modelinglab.ocl.core.standard.iterators.bag.Collect.class);
        bagIts.put("select", org.modelinglab.ocl.core.standard.iterators.bag.Select.class);
        bagIts.put("reject", org.modelinglab.ocl.core.standard.iterators.bag.Reject.class);
        bagIts.put("collectNested", org.modelinglab.ocl.core.standard.iterators.bag.CollectNested.class);
        bagIts.put("sortedBy", org.modelinglab.ocl.core.standard.iterators.bag.SortedBy.class);
        
        sequenceIts = new HashMap<>(5);
        sequenceIts.put("collect", org.modelinglab.ocl.core.standard.iterators.sequence.Collect.class);
        sequenceIts.put("select", org.modelinglab.ocl.core.standard.iterators.sequence.Select.class);
        sequenceIts.put("reject", org.modelinglab.ocl.core.standard.iterators.sequence.Reject.class);
        sequenceIts.put("collectNested", org.modelinglab.ocl.core.standard.iterators.sequence.CollectNested.class);
        sequenceIts.put("sortedBy", org.modelinglab.ocl.core.standard.iterators.sequence.SortedBy.class);
        
        orderedSetIts = new HashMap<>(5);
        orderedSetIts.put("collect", org.modelinglab.ocl.core.standard.iterators.orderedSet.Collect.class);
        orderedSetIts.put("select", org.modelinglab.ocl.core.standard.iterators.orderedSet.Select.class);
        orderedSetIts.put("reject", org.modelinglab.ocl.core.standard.iterators.orderedSet.Reject.class);
        orderedSetIts.put("collectNested", org.modelinglab.ocl.core.standard.iterators.orderedSet.CollectNested.class);
        orderedSetIts.put("sortedBy", org.modelinglab.ocl.core.standard.iterators.orderedSet.SortedBy.class);
    }
    
    public static OclStandardIterators getInstance() {
        return OclStandardIteratorStoreHolder.INSTANCE;
    }
    
    public boolean isIterator(CollectionType sourceType, String name) {
        return getIteratorClass(name, sourceType) != null;
    }
    
    public IteratorExp getIteratorExp(CollectionType sourceType, String name, Classifier bodyType) throws IllegalIteratorException {
        Class<? extends IteratorExp> clazz = getIteratorClass(name, sourceType);
        if (clazz == null) {
            throw new OclRuntimeException(sourceType.getName() + " does not define an iterator "
                    + "with that name '"+name+"'.");
        }
        
        return createIterator(clazz, sourceType, bodyType);
    }
    
    /**
     * 
     * @param sourceType
     * @return the names of the iterators that can be applied for the given source type.
     */
    public Set<String> getIteratorNames(CollectionType sourceType) {
        Set<String> iteratorNames = new HashSet<>();
        iteratorNames.addAll(collectionIts.keySet());
        switch (sourceType.getCollectionKind()) {
            case BAG: {
                iteratorNames.addAll(bagIts.keySet());
                break;
            }
            case ORDERED_SET: {
                iteratorNames.addAll(orderedSetIts.keySet());
                break;
            }
            case SEQUENCE: {
                iteratorNames.addAll(sequenceIts.keySet());
                break;
            }
            case SET: {
                iteratorNames.addAll(setIts.keySet());
                break;
            }
            default:
                throw new AssertionError(sourceType.getCollectionKind() + " is an unexpected collection kind");
        }
        return iteratorNames;
    }
    
    private Class<? extends IteratorExp> getIteratorClass(String name, CollectionType sourceType) {
        Class<? extends IteratorExp> result;
        switch (sourceType.getCollectionKind()) {
            case COLLECTION:
                result = null;
                break;
            case BAG:
                result = bagIts.get(name);
                break;
            case ORDERED_SET:
                result = orderedSetIts.get(name);
                break;
            case SEQUENCE:
                result = sequenceIts.get(name);
                break;
            case SET:
                result = setIts.get(name);
                break;
            default:
                throw new AssertionError(sourceType.getCollectionKind() + " is an unexpected collection kind");
        }
        if (result == null) {
            result = collectionIts.get(name);
        }
        return result;
    }
    
    private IteratorExp createIterator(Class<? extends IteratorExp> clazz, CollectionType sourceType, Classifier bodyType) throws IllegalIteratorException {
        try {
            Constructor<? extends IteratorExp> constructor = clazz.getConstructor(CollectionType.class, Classifier.class);
            IteratorExp it = constructor.newInstance(sourceType, bodyType);
            return it;
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException ex) {
            throw new AssertionError(ex);
        } catch (InvocationTargetException ex) {
            if (ex.getTargetException() instanceof IllegalIteratorException) {
                throw (IllegalIteratorException) ex.getTargetException();
            }
            throw new AssertionError(ex);
        }
    }
    
    private static class OclStandardIteratorStoreHolder {

        private static final OclStandardIterators INSTANCE = new OclStandardIterators();

        private OclStandardIteratorStoreHolder() {
        }
    }
}
