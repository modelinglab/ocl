/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluationAlgorithm;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluator;
import org.modelinglab.ocl.evaluator.iterators.StandardIteratorEvaluatorProvider;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IteratorExpEval {

    private IteratorExpEval() {
    }

    public OclValue<?> evaluate(IteratorExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> source = exp.getSource().accept(ExpressionWalker.getInstance(), runtimeEnv);
        Class<? extends IteratorExp> itClass = exp.getClass();
        IteratorEvaluator<?> itEval;

        itEval = StandardIteratorEvaluatorProvider.getInstance().getEvaluator(itClass);
        if (itEval == null) {
            throw new OclEvaluationException(exp, "There is no declared evaluator that "
                    + "evaluates " + exp.toString());
        }

        IteratorEvaluationAlgorithm algorithm = runtimeEnv.getItAlgorithmProvider().getAlgorithm(exp, itEval, runtimeEnv);

        algorithm.checkCanBeEvaluated(exp, itEval, source);
        IteratorEvaluationAlgorithm.SwitchArgument<?> switchArg = new IteratorEvaluationAlgorithm.SwitchArgument<>(exp, itEval, runtimeEnv);

        OclValue<?> result = source.accept(algorithm, switchArg);
        
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, source, result);
        
        return result;
    }

    public static IteratorExpEval getInstance() {
        return IteratorExpEvalHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return IteratorExpEval.getInstance();
    }

    private static class IteratorExpEvalHolder {

        private static final IteratorExpEval INSTANCE = new IteratorExpEval();

        private IteratorExpEvalHolder() {
        }
    }
}
