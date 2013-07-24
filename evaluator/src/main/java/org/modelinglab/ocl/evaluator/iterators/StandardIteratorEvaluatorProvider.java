/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.iterators;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.evaluator.iterators.bag.AllBagIterators;
import org.modelinglab.ocl.evaluator.iterators.collection.AllCollectionIterators;
import org.modelinglab.ocl.evaluator.iterators.orderedSet.AllOrderedSetIterators;
import org.modelinglab.ocl.evaluator.iterators.sequence.AllSequenceIterators;
import org.modelinglab.ocl.evaluator.iterators.set.AllSetIterators;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StandardIteratorEvaluatorProvider implements IteratorEvaluatorProvider {

    private final HashMap<Class<? extends IteratorExp>, IteratorEvaluator<?>> evaluators;
    
    private StandardIteratorEvaluatorProvider() {
        
        List<IteratorEvaluator<?>> bagEvals = AllBagIterators.getEvaluators();
        List<IteratorEvaluator<?>> collEvals = AllCollectionIterators.getEvaluators();
        List<IteratorEvaluator<?>> ordEvals = AllOrderedSetIterators.getEvaluators();
        List<IteratorEvaluator<?>> seqEvals = AllSequenceIterators.getEvaluators();
        List<IteratorEvaluator<?>> setEvals = AllSetIterators.getEvaluators();
        
        evaluators = new HashMap<>(
                bagEvals.size() 
                + collEvals.size() 
                + ordEvals.size()
                + seqEvals.size()
                + setEvals.size());
        
        addIteratorEvaluatos(bagEvals);
        addIteratorEvaluatos(collEvals);
        addIteratorEvaluatos(ordEvals);
        addIteratorEvaluatos(seqEvals);
        addIteratorEvaluatos(setEvals);
    }
    
    private void addIteratorEvaluatos(Collection<IteratorEvaluator<?>> itEvals) {
        for (final IteratorEvaluator<?> itEval : itEvals) {
            IteratorEvaluator<?> old = evaluators.put(itEval.getEvaluableIterator(), itEval);
            if (old != null) {
                throw new IllegalArgumentException(old.getEvaluableIterator() + " operation has two "
                        + "assigned evaluatos (" + old.getClass() + " and "+ itEval.getClass() +")!");
            }
        }
    }

    @Override
    public IteratorEvaluator<?> getEvaluator(Class<? extends IteratorExp> exp) {
        return evaluators.get(exp);
    }

    @Override
    public Iterator<IteratorEvaluator<?>> iterator() {
        return evaluators.values().iterator();
    }

    public static StandardIteratorEvaluatorProvider getInstance() {
        return StandardIteratorEvaluatorHandlerHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return StandardIteratorEvaluatorProvider.getInstance();
    }

    

    private static class StandardIteratorEvaluatorHandlerHolder {
        private static final StandardIteratorEvaluatorProvider INSTANCE = new StandardIteratorEvaluatorProvider();

        private StandardIteratorEvaluatorHandlerHolder() {
        }
    }
 }
