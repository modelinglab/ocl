
import java.util.Collections;
import org.junit.Ignore;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
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
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate.AGDateObject;
import org.modelinglab.ocl.ext.complextypes.classes.AGDayOfWeek;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;
import org.modelinglab.ocl.ext.complextypes.classes.ComplexTypesLibrary;
import org.modelinglab.ocl.parser.OclParser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class ManualTest {

    @Ignore
    @Test
    public void chu() throws Exception {
        
        StaticEnvironment env = new StaticEnvironment(ComplexTypesLibrary.createStore());
        
        env.addElement(AGDate.INSTANCE, false);
        env.addElement(AGDayOfWeek.INSTANCE, false);
        env.addElement(AGTimeUnit.INSTANCE, false);
        
        OclParser parser = new OclParser(env);
        
        assert AGDate.INSTANCE.equals(AGDate.INSTANCE);
        
        OclExpression exp = parser.parse("Date.now().truncate(TimeUnit::DAY)");
        
        OclEvaluator evaluator = new OclEvaluator(new SequentialIteratorEvaluationAlgorithmProvider());
        
        assert exp != null;
        
        UserEvaluationEnvironment uee = new UserEvaluationEnvironment(env, new InstanceProvider() {

            @Override
            public void preExpression(OclExpression exp, UserEvaluationEnvironment env) {
            }

            @Override
            public void postExpression(OclExpression exp, UserEvaluationEnvironment env) {
            }

            @Override
            public SetValue getAllInstances(UmlClass clazz) {
                return new SetValue(Collections.emptyList(), clazz);
            }

            @Override
            public void checkObject(ObjectValue val) throws UnexpectedObjectException {
            }
        }, ComplexTypesLibrary.getEvaluators());
        
        IVariableTable varTable = new VariableTable();
        
        OclValue<?> result = evaluator.evaluate(exp, uee, varTable);
        OclValue<?> result2 = evaluator.evaluate(exp, uee, varTable);
        
        AGDateObject dateObject = (AGDateObject) result.getValue();
        AGDateObject dateObject2 = (AGDateObject) result2.getValue();
        
        System.out.println(dateObject.getDate());
        System.out.println(dateObject2.getDate());
        
        exp = parser.parse("Date.now().truncate(TimeUnit::DAY) = Date.now().truncate(TimeUnit::DAY)");
        System.out.println(evaluator.evaluate(exp, uee, varTable));
    }
}
