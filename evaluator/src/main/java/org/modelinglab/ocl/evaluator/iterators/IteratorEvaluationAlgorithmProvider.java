/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public interface IteratorEvaluationAlgorithmProvider {
    
    public <E> IteratorEvaluationAlgorithm getAlgorithm(IteratorExp exp, IteratorEvaluator<E> evaluator, EvaluatorVisitorArg rutimeEnv);
    
}
