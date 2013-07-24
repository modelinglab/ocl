/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators.generic;

import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SequenceValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.OclValueIndirection;
import org.modelinglab.ocl.evaluator.operations.utils.CollectionFlatten;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractCollectEvaluator extends AbstractCollectNestedEvaluator {
    protected abstract Classifier getElementResultType(IteratorExp expression);
    
    protected abstract OclValue<?> getResult(SequenceValue<? extends OclValue<?>> sequenceResult);

    @Override
    public OclValue<?> extractResult(OclValueIndirection<List<OclValue<?>>> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv) {
        Classifier bodyType = itExp.getBody().getType();

        SequenceValue<? extends OclValue<?>> sequenceResult;
        if (bodyType.isCollection() || bodyType instanceof AnyType) { //then we need to flatten our accumulator
            Classifier elementResultType = getElementResultType(itExp);
            sequenceResult = CollectionFlatten.flatten(accumulator.getValue(), elementResultType);
        } else { //our result is already flattened
            sequenceResult = new SequenceValue<>(accumulator.getValue(), bodyType, true);
        }
        return getResult(sequenceResult);
    }
}
