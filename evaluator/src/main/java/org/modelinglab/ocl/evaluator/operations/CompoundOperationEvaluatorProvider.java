/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations;

import com.google.common.collect.Iterables;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;

/**
 *
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class CompoundOperationEvaluatorProvider implements OperationEvaluatorProvider {
    List<OperationEvaluatorProvider> opEvProviders;
    
    public CompoundOperationEvaluatorProvider(OperationEvaluatorProvider... opEvProviders) {
        this.opEvProviders = Arrays.asList(opEvProviders);
    }

    @Override
    public OperationEvaluator getEvaluator(Operation op) {
        OperationEvaluator eval = null;
        for (OperationEvaluatorProvider provider : opEvProviders) {
            eval = provider.getEvaluator(op);
            if (eval != null) {
                return eval;
            }
        }
        return null;
    }

    @Override
    public Iterator<OperationEvaluator> iterator() {
        return Iterables.concat(opEvProviders).iterator();
    }
}
