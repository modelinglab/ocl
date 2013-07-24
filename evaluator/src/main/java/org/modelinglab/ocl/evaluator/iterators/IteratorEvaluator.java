/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import java.util.List;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluationAlgorithm.SwitchArgument;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public interface IteratorEvaluator<E> {

    public abstract Class<? extends IteratorExp> getEvaluableIterator();
    
    /**
     * The expected type of the body expression.
     *
     * @return
     */
    public abstract Classifier expectedBodyClassifier();

    /**
     * Creates the accumulator that will be use to merge result from tasks.
     *
     * This method is executed secuencially
     *
     * @param sourceType   the static source type
     * @param sourceAsList the dynamic source value
     * @param switchArg    an object that wraps the iterator expression and runtime environment.
     *
     * @return an instance of OclValueIndirection that will be used as accumulator
     */
    public abstract OclValueIndirection<E> createAccumulator(CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv);

    /**
     *
     * This method is executed secuencially
     *
     * @param accumulator
     * @param taskResult
     * @param index
     * @param sourceAsList
     *
     * @return result of the iterator evaluation. If null is returned, evaluation continues. If not null is
     *         returned, all pendient tasks will be canceled and this result is returned as iterator result.
     */
    public abstract OclValue<?> acumResult(OclValueIndirection<E> accumulator, OclValue<?> taskResult, int index, List<? extends OclValue<?>> sourceAsList);

    /**
     * Given an accumulator, extracts the result of the iteration. This method is called when all tasks are
     * finished and accumulator stores the value of all tasks.
     *
     * This method is executed secuencially
     *
     * @param accumulator
     * @param sourceType
     * @param sourceAsList
     * @param switchArg    an object that wraps the iterator expression and runtime environment.
     *
     * @return the result of the iteration
     */
    @Nonnull
    public abstract OclValue<?> extractResult(OclValueIndirection<E> accumulator, CollectionType sourceType, List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, EvaluatorVisitorArg runtimeEnv);

    /**
     * Creates the task result in function of value of the body. Usually, this method returns the same value
     * given as argument. But some iterators (for example, any) needs to modify this value.
     *
     * This method is executed concurrently
     *
     * @param bodyValue the evaluated value body value.
     *
     * @return
     */
    @Nonnull
    public abstract OclValue<?> createTaskResult(OclValue<?> bodyValue, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index);
}
