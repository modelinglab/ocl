/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class CollectionOperationEvaluatorProvider implements OperationEvaluatorProvider {
    private final Map<Operation, OperationEvaluator> opEvalMap;

    public CollectionOperationEvaluatorProvider(Collection<? extends OperationEvaluator> operationEvaluators) {
        this.opEvalMap = new HashMap<>(operationEvaluators.size());
        
        for (OperationEvaluator operationEvaluator : operationEvaluators) {
            OperationEvaluator op = opEvalMap.put(operationEvaluator.getEvaluableOperation(), operationEvaluator);
            if (op != null) {
                throw new IllegalArgumentException("There are two operation evaluator that evaluates "+op);
            }
        }
    }

    @Override
    public OperationEvaluator getEvaluator(Operation op) {
        return opEvalMap.get(op);
    }

    @Override
    public Iterator<OperationEvaluator> iterator() {
        return opEvalMap.values().iterator();
    }
}
