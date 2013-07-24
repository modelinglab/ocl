/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.evaluator.operations.StandardOperationEvaluatorsProvider;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OperationCallExpEval {

    private OperationCallExpEval() {
    }

    public static OperationCallExpEval getInstance() {
        return OperationCallExpEvalHolder.INSTANCE;
    }

    public OclValue<?> evaluate(OperationCallExp exp, EvaluatorVisitorArg runtimeEnv) throws OclEvaluationException {
        OclValue<?> source = exp.getSource().accept(ExpressionWalker.getInstance(), runtimeEnv);
        Operation opTemplate = exp.getReferredOperation().getTemplateOperation();
        OperationEvaluator opEval;

        opEval = StandardOperationEvaluatorsProvider.getInstance().getEvaluator(opTemplate);
        if (opEval == null) {
            opEval = runtimeEnv.getUserEvalEnv().getUserOperationEvaluator(opTemplate);
            if (opEval == null) {
                throw new OclEvaluationException(exp, "There is no declared evaluator that "
                        + "evaluates " + opTemplate.getSignature());
            }
        }

        List<OclValue<?>> args = new ArrayList<>(exp.getArguments().size());
        for (OclExpression arg : exp.getArguments()) {
            args.add(arg.accept(ExpressionWalker.getInstance(), runtimeEnv));
        }
        opEval.checkCanBeEvaluated(exp, source, args);
        OperationEvaluator.SwitchArgument arg = new OperationEvaluator.SwitchArgument(exp, args, runtimeEnv);
        
        OclValue<?> result = source.accept(opEval, arg);
        runtimeEnv.getUserEvalEnv().getAnalyzer().analyze(exp, source, args, result);
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return OperationCallExpEval.getInstance();
    }

    private static class OperationCallExpEvalHolder {

        private static final OperationCallExpEval INSTANCE = new OperationCallExpEval();
    }
}
