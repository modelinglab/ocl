/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.ext.time;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.vartables.IVariableTable;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.evaluator.InstanceProvider;
import org.modelinglab.ocl.evaluator.OclEvaluator;
import org.modelinglab.ocl.evaluator.UnexpectedObjectException;
import org.modelinglab.ocl.evaluator.UserEvaluationEnvironment;
import org.modelinglab.ocl.evaluator.iterators.SequentialIteratorEvaluationAlgorithm.SequentialIteratorEvaluationAlgorithmProvider;
import org.modelinglab.ocl.parser.OclLexerException;
import org.modelinglab.ocl.parser.OclParser;
import org.modelinglab.ocl.parser.OclParserException;

/**
 *
 */
public class DateTestUtils {

    private final static OclParser parser;
    private final static OclEvaluator evaluator;
    private final static IVariableTable emptyVarTable = new VariableTable();
    private final static UserEvaluationEnvironment uee;
    
    static {
        StaticEnvironment env = new StaticEnvironment(DateLibrary.createStore());
        for (final Classifier classifier : DateLibrary.createTypes()) {
            env.addElement(classifier);
        }
        
        parser = new OclParser(env);
        
        evaluator = new OclEvaluator(new SequentialIteratorEvaluationAlgorithmProvider());
        
        InstanceProvider ip = new InstanceProvider<Long>() {

            @Override
            public void preExpression(OclExpression exp, UserEvaluationEnvironment env) {
            }

            @Override
            public void postExpression(OclExpression exp, UserEvaluationEnvironment env) {
            }

            @Override
            public SetValue<ObjectValue<Long>> getAllInstances(UmlClass clazz) {
                return new SetValue<>(Collections.<ObjectValue<Long>>emptySet(), clazz);
            }

            @Override
            public void checkObject(ObjectValue<Long> val) throws UnexpectedObjectException {
            }
        };
        
        uee = new UserEvaluationEnvironment(env, ip, DateLibrary.getEvaluators());
    }
    
    private DateTestUtils() {}
    
    public static boolean equals(String oclExpression, Object obj) {
        return equals(oclExpression, obj, emptyVarTable);
    }
    
    public static boolean equals(String oclExpression, Object obj, IVariableTable localVarTable) {
        try {
            parser.getEnv().addScope();
            for (final String varId : localVarTable) {
                parser.getEnv().addElement(localVarTable.getVariable(varId));
            }
            OclExpression exp = parser.parse(oclExpression);
            OclValue<?> val = evaluator.evaluate(exp, uee, localVarTable);
            
            Object evaluatedObject = DateUtils.translateToJavaObject(val);
            return Objects.equals(evaluatedObject, obj);
        } catch (OclParserException | OclLexerException | IOException | OclException ex) {
            throw new RuntimeException(ex);
        } finally {
            parser.getEnv().removeScope();
        }
    }
}
