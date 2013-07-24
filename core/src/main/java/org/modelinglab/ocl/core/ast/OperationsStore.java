/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import java.io.Serializable;
import java.util.*;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.utils.OclClassifierOperationsHierarchySwitcher;
import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.core.standard.OclStandardOperations;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public final class OperationsStore implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private Table<Classifier, String, Set<Operation>> operationsMap;

    private OperationsStore(Table<Classifier, String, Set<Operation>> operationsMap) {
        this.operationsMap = operationsMap;
    }

    public Iterator<Operation> getOperations(Classifier classifier, String name) {
        return new OperationsIterator(classifier, name);
    }

    @Override
    public OperationsStore clone() {
        //we need to make a clone of operationsMap
        Table<Classifier, String, Set<Operation>> newOperationsMap = HashBasedTable.create(operationsMap);

        return new OperationsStore(newOperationsMap);
    }

    private Set<Operation> getOperationByClassifierAndName(Classifier classifier, String name) {
        Set<Operation> ops = operationsMap.get(classifier, name);
        if (ops == null) {
            ops = Collections.emptySet();
        }
        return ops;
    }

    public static class OperationsStoreFactory implements Serializable {

        private static final long serialVersionUID = 1L;
        private OperationsStore tempStore;

        public OperationsStoreFactory() {
            Table<Classifier, String, Set<Operation>> operationsMap = HashBasedTable.create();
            tempStore = new OperationsStore(operationsMap);
        }

        public void addOperation(Operation op) {
            Set<Operation> similarOperations = tempStore.getOperationByClassifierAndName(op.getSource(), op.getName());
            if (similarOperations.isEmpty()) {
                similarOperations = new HashSet<>(1);
                tempStore.operationsMap.put(op.getSource(), op.getName(), similarOperations);
            }
            boolean addOperation = true;
            for (final Operation alreadyDeclaredOp : similarOperations) {
                if (alreadyDeclaredOp.overrides(op, new TemplateRestrictions())
                        && op.overrides(alreadyDeclaredOp, new TemplateRestrictions())) {

                    if (op.equals(alreadyDeclaredOp)) {
                        addOperation = false;
                    } else {
                        throw new IllegalArgumentException("There are two different operations with simmilar signature (" + op + ")");
                    }
                }
            }
            if (addOperation) {
                similarOperations.add(op);
            }
        }

        public boolean removeOperation(Operation op) {
            Set<Operation> similarOperations = tempStore.getOperationByClassifierAndName(op.getSource(), op.getName());
            if (similarOperations.isEmpty()) {
                return false;
            }
            return similarOperations.remove(op);
        }

        public OperationsStoreFactory addAllOperations(OperationsStore opStore) {
            for (final Set<Operation> ops : opStore.operationsMap.values()) {
                addAllOperations(ops);
            }
            return this;
        }

        public OperationsStoreFactory addAllOperations(Iterable<Operation> operations) {
            for (final Operation operation : operations) {
                addOperation(operation);
            }
            return this;
        }

        public OperationsStore createStore() {
            return tempStore.clone();
        }
    }

    private class OperationsIterator implements Iterator<Operation> {

        /**
         * this attribute iterates over all ocl classifiers in the source classifier hierarchy. We
         * call "ocl classifier" to all classifiers except UmlClasses (and UmlEnums).
         */
        final Iterator<Classifier> oclClassifiersIt;
        /**
         * this attribute iterates over all umlClasses in the source classifier hierarchy.
         */
        final Iterator<UmlClass> classHierarchyIt;
        Iterator<Operation> standardIt, userIt, classIt;
        final String operatorName;

        private OperationsIterator(Classifier classifier, String name) {
            Set<Classifier> classifiers = classifier.accept(OclClassifierOperationsHierarchySwitcher.getInstance(), null);
            oclClassifiersIt = classifiers.iterator();

            LinkedList<UmlClass> classHierarchy = new LinkedList<UmlClass>();
            if (classifier instanceof UmlClass) {
                classHierarchy.addAll(OclUtils.getAllSuperclasses((UmlClass) classifier));
                classHierarchy.add((UmlClass) classifier);
            }
            classHierarchyIt = classHierarchy.iterator();

            operatorName = name;
        }

        private Iterator<Operation> getActualIterator() {
            if (standardIt != null && standardIt.hasNext()) {
                return standardIt;
            }
            if (userIt != null && userIt.hasNext()) {
                return userIt;
            }
            if (classIt != null && classIt.hasNext()) {
                return classIt;
            }
            //we need a new classifier
            if (oclClassifiersIt.hasNext()) { //there are some ocl classifier
                Classifier classifier = oclClassifiersIt.next();
                standardIt = classifier.accept(OclStandardOperations.getInstance(), operatorName).iterator();
                userIt = getOperationByClassifierAndName(classifier, operatorName).iterator();
            } else { //there is no ocl classifier => maybe is an opertor of some UmlClass in the hierarchy
                if (!classHierarchyIt.hasNext()) { //there are no uml classes in the hierarchy that are note visited => the iterator ends
                    return null;
                }
                UmlClass clazz = classHierarchyIt.next();
                classIt = clazz.getOperations(operatorName).iterator();
                userIt = getOperationByClassifierAndName(clazz, operatorName).iterator();
            }
            assert standardIt != null || userIt != null || classIt != null;
            return getActualIterator();
        }

        @Override
        public boolean hasNext() {
            Iterator<Operation> it = getActualIterator();
            return it != null && it.hasNext();
        }

        @Override
        public Operation next() {
            Iterator<Operation> it = getActualIterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            return it.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported.");
        }
    }
}
