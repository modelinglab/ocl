/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.values.BooleanValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.core.vartables.VariableTable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public abstract class AbstractTest {
    
    protected static PrimitiveType realType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL);
    protected static PrimitiveType intType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
    
    protected OclValue<?> invalid = InvalidValue.instantiate();
    protected OclValue<?> nullVal = VoidValue.instantiate();
    protected OclValue<?> trueVal = BooleanValue.TRUE;
    protected OclValue<?> falseVal = BooleanValue.FALSE;
    
    protected void assertEquals(String exp, String expected, VariableTable varTable) throws Exception {
        OclValue<?> resultVal = TesterTool.evaluate(exp, varTable);
        OclValue<?> expectedVal = TesterTool.evaluate(expected, varTable);
        assert resultVal.equals(expectedVal)
                : exp+" = "+resultVal+" (expected "+expected +")";
    }
    
    protected void assertEquals(String exp, String expected) throws Exception {
        assertEquals(exp, expected, null);
    }
    
}
