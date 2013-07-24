/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public interface IteratorEvaluatorProvider extends Iterable<IteratorEvaluator<?>> {
    
    IteratorEvaluator<?> getEvaluator(Class<? extends IteratorExp> iteratorClass);
    
}
