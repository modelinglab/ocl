/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.OclStandardOperations;
import org.modelinglab.ocl.core.standard.operations.bag.IntersectionBag;
import org.modelinglab.ocl.core.standard.operations.bag.IntersectionSet;
import org.modelinglab.ocl.core.standard.operations.bag.UnionBag;
import org.modelinglab.ocl.core.standard.operations.bag.UnionSet;
import org.modelinglab.ocl.core.standard.operations.collection.Max;
import org.modelinglab.ocl.core.standard.operations.collection.Min;
import org.modelinglab.ocl.core.standard.operations.collection.Product;
import org.modelinglab.ocl.core.standard.operations.collection.Sum;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Append;
import org.modelinglab.ocl.core.standard.operations.orderedSet.InsertAt;
import org.modelinglab.ocl.core.standard.operations.orderedSet.Prepend;
import org.modelinglab.ocl.core.standard.operations.sequence.AsOrderedSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StandardOperationEvaluatorsTableProvider {

    Set<Operation> expectedNotImplementedOps;

    public StandardOperationEvaluatorsTableProvider() {
        expectedNotImplementedOps = new HashSet<>();
        /*
         * Sequence
         */
        expectedNotImplementedOps.add(org.modelinglab.ocl.core.standard.operations.sequence.Sum.createTemplateOperation());
        
        /*
         * OrderedSet
         */
        expectedNotImplementedOps.add(Append.createTemplateOperation());
        expectedNotImplementedOps.add(Prepend.createTemplateOperation());
        expectedNotImplementedOps.add(InsertAt.createTemplateOperation());
        expectedNotImplementedOps.add(org.modelinglab.ocl.core.standard.operations.orderedSet.Sum.createTemplateOperation());
        
        /*
         * Collection
         */
        expectedNotImplementedOps.add(Sum.createTemplateOperation());
        expectedNotImplementedOps.add(Min.createTemplateOperation());
        expectedNotImplementedOps.add(Max.createTemplateOperation());
        expectedNotImplementedOps.add(Product.createTemplateOperation());
    }

    @Test
    public void testEvaluatesAllStandardOperation() {
        StandardOperationEvaluatorsProvider table = StandardOperationEvaluatorsProvider.getInstance();

        Set<Operation> notContained = new HashSet<>();
        for (final Operation op : OclStandardOperations.getInstance()) {
            if (table.getEvaluator(op) == null) {
                notContained.add(op);
            }
        }
        assert notContained.isEmpty() : notContained + " have not a evaluator!";
    }

    @Test
    public void testKnownNotImplementedOperations() {
        StandardOperationEvaluatorsProvider table = StandardOperationEvaluatorsProvider.getInstance();

        Set<Operation> notImplemented = new HashSet<>();
        for (final Operation op : OclStandardOperations.getInstance()) {
            if (table.getEvaluator(op) == null) {
                continue;
            }
            if (!table.getEvaluator(op).isImplemented()) {
                notImplemented.add(op);
            }
        }
        if (!notImplemented.equals(expectedNotImplementedOps)) {
            Set<Operation> tempSet = new HashSet<>(notImplemented);
            tempSet.removeAll(expectedNotImplementedOps);

            if (!tempSet.isEmpty()) {
                assert false : tempSet + " are unexpected not implemented operations!";
            }

            tempSet.clear();
            tempSet.addAll(expectedNotImplementedOps);
            tempSet.removeAll(notImplemented);
            if (!tempSet.isEmpty()) {
                assert false : "It was expected that " + tempSet + " were not implemented operations, but "
                        + "are implemented!";
            }
            assert false : "This code should be unreachable";
        }
    }
}
