/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public interface OperationEvaluatorProvider extends Iterable<OperationEvaluator> {
    
    OperationEvaluator getEvaluator(Operation op);
    
}
