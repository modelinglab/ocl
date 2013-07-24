/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.evaluator.operations.bag.AllBagOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.bool.AllBooleanOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.collection.AllCollectionOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.integer.AllIntegerOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.oclAny.AllOclAnyOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.oclVoid.AllOclVoidOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.orderedSet.AllOrderedSetOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.real.AllRealOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.sequence.AllSequenceOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.set.AllSetOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.string.AllStringOperationEvaluators;
import org.modelinglab.ocl.evaluator.operations.umlClass.AllUmlClassOperationEvaluators;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class StandardOperationEvaluatorsProvider implements OperationEvaluatorProvider {
    
    Map<Operation, OperationEvaluator> opTable;

    private StandardOperationEvaluatorsProvider() {
        List<OperationEvaluator> bagEvals = AllBagOperationEvaluators.getEvaluators();
        List<OperationEvaluator> boolEvals = AllBooleanOperationEvaluators.getEvaluators();
        List<OperationEvaluator> collEvals = AllCollectionOperationEvaluators.getEvaluators();
        List<OperationEvaluator> intEvals = AllIntegerOperationEvaluators.getEvaluators();
        List<OperationEvaluator> anyEvals = AllOclAnyOperationEvaluators.getEvaluators();
        List<OperationEvaluator> voidEvals = AllOclVoidOperationEvaluators.getEvaluators();
        List<OperationEvaluator> orderedSetEvals = AllOrderedSetOperationEvaluators.getEvaluators();
        List<OperationEvaluator> realEvals = AllRealOperationEvaluators.getEvaluators();
        List<OperationEvaluator> seqEvals = AllSequenceOperationEvaluators.getEvaluators();
        List<OperationEvaluator> setEvals = AllSetOperationEvaluators.getEvaluators();
        List<OperationEvaluator> stringEvals = AllStringOperationEvaluators.getEvaluators();
        List<OperationEvaluator> umlClassEvals = AllUmlClassOperationEvaluators.getEvaluators();
        
        opTable = new HashMap<>(
                bagEvals.size() 
                + boolEvals.size() 
                + collEvals.size() 
                + intEvals.size()
                + anyEvals.size()
                + voidEvals.size()
                + orderedSetEvals.size()
                + realEvals.size()
                + seqEvals.size()
                + setEvals.size()
                + stringEvals.size()
                + umlClassEvals.size());
        
        addOperationEvaluatos(bagEvals);
        addOperationEvaluatos(boolEvals);
        addOperationEvaluatos(collEvals);
        addOperationEvaluatos(intEvals);
        addOperationEvaluatos(anyEvals);
        addOperationEvaluatos(voidEvals);
        addOperationEvaluatos(orderedSetEvals);
        addOperationEvaluatos(realEvals);
        addOperationEvaluatos(seqEvals);
        addOperationEvaluatos(setEvals);
        addOperationEvaluatos(stringEvals);
        addOperationEvaluatos(umlClassEvals);
    }

    public static StandardOperationEvaluatorsProvider getInstance() {
        return StandardOperationEvaluatorsTableHolder.INSTANCE;
    }
    
    @Override
    public OperationEvaluator getEvaluator(Operation op) {
        return opTable.get(op);
    }

    @Override
    public Iterator<OperationEvaluator> iterator() {
        return opTable.values().iterator();
    }
    
    private void addOperationEvaluatos(Collection<OperationEvaluator> opEvals) {
        for (final OperationEvaluator opEval : opEvals) {
            OperationEvaluator old = opTable.put(opEval.getEvaluableOperation(), opEval);
            if (old != null) {
                throw new IllegalArgumentException(old.getEvaluableOperation() + " operation has two "
                        + "assigned evaluatos (" + old.getClass() + " and "+ opEval.getClass() +")!");
            }
        }
    }
    
    
    private static class StandardOperationEvaluatorsTableHolder {

        private static final StandardOperationEvaluatorsProvider INSTANCE = new StandardOperationEvaluatorsProvider();
    }
}
