/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.evaluator.MyNumber.MyNumberLessEvaluator;
import org.modelinglab.ocl.evaluator.iterators.SequentialIteratorEvaluationAlgorithm.SequentialIteratorEvaluationAlgorithmProvider;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.parser.OclParser;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class TesterTool {

    private static TesterTool instance;
    OclParser parser;
    OclEvaluator singleThreadEvaluator;
    InstanceProvider instancesProvider;
    UserEvaluationEnvironment uee;

    private TesterTool() {
        parser = new OclParser(TestEnvironment.createEnvironment());

        singleThreadEvaluator = new OclEvaluator(new SequentialIteratorEvaluationAlgorithmProvider());

        instancesProvider = new DefaultInstancesProvider(parser.getEnv());
        
        Set<OperationEvaluator> evaluators = new HashSet<>(1);
        evaluators.add(new MyNumberLessEvaluator());
        
        uee = new UserEvaluationEnvironment(parser.getEnv(), instancesProvider, evaluators);
    }

    private static TesterTool getInstance() {
        if (instance == null) {
            instance = new TesterTool();
        }
        return instance;
    }

    private OclValue<?> evaluatePrivate(OclExpression exp, @Nullable VariableTable varTable) throws Exception {

        VariableTable vt = varTable;
        if (vt == null) {
            vt = new VariableTable();
        }
        
        return singleThreadEvaluator.evaluate(exp, uee, vt);
    }

    private OclExpression parsePrivate(String exp, @Nullable VariableTable varTable) throws Exception, Exception {
        if (varTable != null) {
            parser.getEnv().addScope();
        }

        try {
            if (varTable != null) {
                for (final String varName : varTable) {
                    parser.getEnv().addElement(varTable.getVariable(varName), false);
                }
            }

            return parser.parse(exp);
        } finally {
            if (varTable != null) {
                parser.getEnv().removeScope();
            }
        }
    }

    public synchronized static OclExpression parse(String exp, @Nullable VariableTable varTable) throws Exception {
        return getInstance().parsePrivate(exp, varTable);
    }
    
    public synchronized static OclValue<?> evaluate(OclExpression exp, @Nullable VariableTable varTable) throws Exception {
        return getInstance().evaluatePrivate(exp, varTable);
    }
    
    public synchronized static OclValue<?> evaluate(String expString, @Nullable VariableTable varTable) throws Exception {
        OclExpression exp = getInstance().parsePrivate(expString, varTable);
        return getInstance().evaluatePrivate(exp, varTable);
    }

    @Deprecated
    public synchronized static OclValue<?> evaluate(OclExpression exp, @Nullable VariableTable varTable, boolean concurrent) throws Exception {
        return getInstance().evaluatePrivate(exp, varTable);
    }

    @Deprecated
    public synchronized static OclValue<?> evaluate(String expString, @Nullable VariableTable varTable, boolean concurrent) throws Exception {
        OclExpression exp = getInstance().parsePrivate(expString, varTable);
        return getInstance().evaluatePrivate(exp, varTable);
    }
}
