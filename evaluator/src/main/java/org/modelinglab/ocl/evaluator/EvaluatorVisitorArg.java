/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import java.io.Serializable;
import org.modelinglab.ocl.core.vartables.HierarchyVarTable;
import org.modelinglab.ocl.core.vartables.IVariableTable;
import org.modelinglab.ocl.evaluator.iterators.IteratorEvaluationAlgorithmProvider;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class EvaluatorVisitorArg implements Serializable {

    private static final long serialVersionUID = 1L;
    private final UserEvaluationEnvironment evaluationEnv;
    private final IVariableTable varTable;
    private final IteratorEvaluationAlgorithmProvider itAlgorithmProvider;

    public EvaluatorVisitorArg(
            UserEvaluationEnvironment evaluationEnv, 
            IVariableTable varTable, 
            IteratorEvaluationAlgorithmProvider itAlgorithmProvider) {
        this.evaluationEnv = evaluationEnv;
        this.varTable = varTable;
        this.itAlgorithmProvider = itAlgorithmProvider;
    }

    public EvaluatorVisitorArg createSubEnvironment() {
        return new EvaluatorVisitorArg(evaluationEnv, new HierarchyVarTable(varTable), itAlgorithmProvider);
    }

    public UserEvaluationEnvironment getUserEvalEnv() {
        return evaluationEnv;
    }

    public IVariableTable getVarTable() {
        return varTable;
    }

    public IteratorEvaluationAlgorithmProvider getItAlgorithmProvider() {
        return itAlgorithmProvider;
    }
}
