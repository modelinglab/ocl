/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableAlreadyExistException;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ConcurrentIteratorEvaluationAlgorithm extends IteratorEvaluationAlgorithm {
    
    private final ExecutorService executorService;

    public ConcurrentIteratorEvaluationAlgorithm(ExecutorService executorService) {
        this.executorService = executorService;
    }
    
    @Nonnull
    @Override
    public <E> OclValue<?> evaluateSingleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        assert itExp.getBody().getType().conformsTo(concreteEvaluator.expectedBodyClassifier());
        assert itExp.getIterators().size() == 1;
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(0).getName()) :
                "Iterator variable should not be declared in variable table";

        CompletionService<OclValue<?>> completionService = new ExecutorCompletionService<>(executorService);

        EvaluatorVisitorArg originalRuntimeEnv = runtimeEnv;

        final OclValueIndirection<E> accumulator = concreteEvaluator.createAccumulator(sourceType, sourceAsList, itExp, runtimeEnv);
        final ArrayList<Future<OclValue<?>>> futureTasks = new ArrayList<>(sourceAsList.size());

        int index = 0;
        for (final OclValue<?> element : sourceAsList) {
            Callable<OclValue<?>> task = new SubTask<>(concreteEvaluator, originalRuntimeEnv, itExp, element, null, index);
            Future<OclValue<?>> future = completionService.submit(task);
            futureTasks.add(future);

            index++;
        }

        try {
            for (int i = 0; i < sourceAsList.size(); i++) {
                Future<OclValue<?>> completedTask = completionService.take();
                OclValue<?> taskResult = completedTask.get();

                OclValue<?> result = concreteEvaluator.acumResult(accumulator, taskResult, index, sourceAsList);
                if (result != null) {
                    for (final Future<OclValue<?>> subtask : futureTasks) {
                        subtask.cancel(true);
                    }
                    return result;
                }
            }
        } catch (ExecutionException ex) {
            throw new OclEvaluationException(itExp, ex);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        OclValue<?> result = concreteEvaluator.extractResult(accumulator, sourceType, sourceAsList, itExp, runtimeEnv);
        assert result != null;
        return result;
    }

    @Nonnull
    @Override
    public <E> OclValue<?> evaluateDoubleItVar(final CollectionType sourceType, final List<? extends OclValue<?>> sourceAsList, IteratorExp itExp, IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {

        assert itExp.getBody().getType().conformsTo(concreteEvaluator.expectedBodyClassifier());
        assert itExp.getIterators().size() == 2;
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(0).getName()) :
                "Iterator variable should not be declared in variable table";
        assert !runtimeEnv.getVarTable().exists(itExp.getIterators().get(1).getName()) :
                "Iterator variable should not be declared in variable table";

        CompletionService<OclValue<?>> completionService = new ExecutorCompletionService<>(executorService);

        EvaluatorVisitorArg originalRuntimeEnv = runtimeEnv;

        final OclValueIndirection<E> accumulator = concreteEvaluator.createAccumulator(sourceType, sourceAsList, itExp, runtimeEnv);
        final ArrayList<Future<OclValue<?>>> futureTasks = new ArrayList<>(sourceAsList.size());

        int index = 0;
        for (final OclValue<?> element1 : sourceAsList) {
            for (final OclValue<?> element2 : sourceAsList) {
                Callable<OclValue<?>> task = new SubTask<>(concreteEvaluator, originalRuntimeEnv, itExp, element1, element2, index);
                Future<OclValue<?>> future = completionService.submit(task);
                futureTasks.add(future);
            }
            index++;
        }

        try {
            for (int i = 0; i < sourceAsList.size(); i++) {
                Future<OclValue<?>> completedTask = completionService.take();
                OclValue<?> taskResult = completedTask.get();

                OclValue<?> result = concreteEvaluator.acumResult(accumulator, taskResult, index, sourceAsList);
                if (result != null) {
                    for (final Future<OclValue<?>> subtask : futureTasks) {
                        subtask.cancel(true);
                    }
                    return result;
                }
            }
        } catch (ExecutionException ex) {
            throw new OclEvaluationException(itExp, ex);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        OclValue<?> result = concreteEvaluator.extractResult(accumulator, sourceType, sourceAsList, itExp, runtimeEnv);
        assert result != null;
        return result;
    }

    private class SubTask<E> implements Callable<OclValue<?>> {

        private final IteratorEvaluator<E> concreteEvaluator;
        private final EvaluatorVisitorArg originalRuntimeEnv;
        private final IteratorExp itExp;
        private final OclValue<?> element1;
        private final OclValue<?> element2;
        private final int index;

        public SubTask(IteratorEvaluator<E> concreteEvaluator, EvaluatorVisitorArg originalRuntimeEnv, IteratorExp itExp, OclValue<?> element1, OclValue<?> element2, int index) {
            this.concreteEvaluator = concreteEvaluator;
            this.originalRuntimeEnv = originalRuntimeEnv;
            this.itExp = itExp;
            this.element1 = element1;
            this.element2 = element2;
            this.index = index;
        }

        /**
         * Evaluates body in a new runtime environment that contains originalRuntimeEnv, element1 and element2
         * (if this element is not null).
         *
         * @param originalRuntimeEnv
         * @param itExp
         * @param element1
         * @param element2
         *
         * @return the evaluated value.
         */
        @Nonnull
        private OclValue<?> evaluateBody() throws VariableAlreadyExistException {
            EvaluatorVisitorArg newRuntimeEnv = originalRuntimeEnv.createSubEnvironment();

            Variable itVar = itExp.getIterators().get(0);
            newRuntimeEnv.getVarTable().createVariable(itVar, element1);

            if (element2 != null) {
                itVar = itExp.getIterators().get(1);
                newRuntimeEnv.getVarTable().createVariable(itVar, element2);
            }

            return itExp.getBody().accept(ExpressionWalker.getInstance(), newRuntimeEnv);
        }

        @Override
        public OclValue<?> call() throws Exception {
            OclValue<?> bodyVal = evaluateBody();

            assert bodyVal != null;

            return concreteEvaluator.createTaskResult(bodyVal, originalRuntimeEnv, itExp, element1, element2, index);
        }
    }
}
