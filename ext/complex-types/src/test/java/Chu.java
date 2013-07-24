
import org.junit.Test;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
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
public class Chu {

    @Test
    public void chu() throws Exception {
        
        StaticEnvironment env = new StaticEnvironment(ComplexTypesLibrary.createStore());
        
        env.addElement(AGDate.INSTANCE, false);
        env.addElement(AGDayOfWeek.INSTANCE, false);
        env.addElement(AGTimeUnit.INSTANCE, false);
        
        OclParser parser = new OclParser(env);
        
        assert AGDate.INSTANCE.equals(AGDate.INSTANCE);
        
        OclExpression exp = parser.parse("Date.now().truncate(TimeUnit::DAY)");
        assert exp != null;
    }
}
