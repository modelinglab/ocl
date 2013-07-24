/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.evaluator.iterators.set;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.vartables.VariableTable;
import org.modelinglab.ocl.evaluator.AbstractTest;
import org.modelinglab.ocl.evaluator.MyNumber;
import org.modelinglab.ocl.evaluator.TesterTool;

/**
 *
 */
public class SetSortedByEvaluatorTest extends AbstractTest {

    @Test
    public void testNumber() throws Exception {
        assertEquals("Set{1,2,3}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        assertEquals("Set{1,3,2}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        assertEquals("Set{2,1,3}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        assertEquals("Set{2,3,1}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        assertEquals("Set{3,2,1}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        assertEquals("Set{3,1,2}        ->sortedBy(i | i)", "OrderedSet{1, 2, 3}");
        
        assertEquals("Set{2,2,3}        ->sortedBy(i | i)", "OrderedSet{2, 3}");
        assertEquals("Set{2,3,2}        ->sortedBy(i | i)", "OrderedSet{2, 3}");
        assertEquals("Set{3,2,2}        ->sortedBy(i | i)", "OrderedSet{2, 3}");
        
        assertEquals("Set{1,3,3}        ->sortedBy(i | i)", "OrderedSet{1, 3}");
        assertEquals("Set{3,1,3}        ->sortedBy(i | i)", "OrderedSet{1, 3}");
        assertEquals("Set{3,3,1}        ->sortedBy(i | i)", "OrderedSet{1, 3}");

        assertEquals("Set{1,2,3,null}   ->sortedBy(i | i)", "invalid");
        assertEquals("Set{1,2,3,invalid}->sortedBy(i | i)", "invalid");
    }
    
    @Test
    public void testString() throws Exception {
        assertEquals("Set{'1','2','3'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        assertEquals("Set{'1','3','2'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        assertEquals("Set{'2','1','3'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        assertEquals("Set{'2','3','1'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        assertEquals("Set{'3','2','1'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        assertEquals("Set{'3','1','2'}        ->sortedBy(i | i)", "OrderedSet{'1', '2', '3'}");
        
        assertEquals("Set{'2','2','3'}        ->sortedBy(i | i)", "OrderedSet{'2', '3'}");
        assertEquals("Set{'2','3','2'}        ->sortedBy(i | i)", "OrderedSet{'2', '3'}");
        assertEquals("Set{'3','2','2'}        ->sortedBy(i | i)", "OrderedSet{'2', '3'}");
        
        assertEquals("Set{'1','3','3'}        ->sortedBy(i | i)", "OrderedSet{'1', '3'}");
        assertEquals("Set{'3','1','3'}        ->sortedBy(i | i)", "OrderedSet{'1', '3'}");
        assertEquals("Set{'3','3','1'}        ->sortedBy(i | i)", "OrderedSet{'1', '3'}");

        assertEquals("Set{'1','2','3',null}   ->sortedBy(i | i)", "invalid");
        assertEquals("Set{'1','2','3',invalid}->sortedBy(i | i)", "invalid");
    }
    
    @Test
    public void testUserDefined() throws Exception {
        VariableTable varTable = new VariableTable();
        varTable.createVariable(new Variable("n1", null, MyNumber.INSTANCE), TesterTool.evaluate("MyNumber.allInstances()->any(value = 1)", null));
        varTable.createVariable(new Variable("n2", null, MyNumber.INSTANCE), TesterTool.evaluate("MyNumber.allInstances()->any(value = 2)", null));
        varTable.createVariable(new Variable("n3", null, MyNumber.INSTANCE), TesterTool.evaluate("MyNumber.allInstances()->any(value = 3)", null));
        
        assertEquals("Set{n1,n2,n3}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        assertEquals("Set{n1,n3,n2}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        assertEquals("Set{n2,n1,n3}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        assertEquals("Set{n2,n3,n1}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        assertEquals("Set{n3,n2,n1}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        assertEquals("Set{n3,n1,n2}        ->sortedBy(i | i)", "OrderedSet{n1, n2, n3}", varTable);
        
        assertEquals("Set{n2,n2,n3}        ->sortedBy(i | i)", "OrderedSet{n2, n3}", varTable);
        assertEquals("Set{n2,n3,n2}        ->sortedBy(i | i)", "OrderedSet{n2, n3}", varTable);
        assertEquals("Set{n3,n2,n2}        ->sortedBy(i | i)", "OrderedSet{n2, n3}", varTable);
        
        assertEquals("Set{n1,n3,n3}        ->sortedBy(i | i)", "OrderedSet{n1, n3}", varTable);
        assertEquals("Set{n3,n1,n3}        ->sortedBy(i | i)", "OrderedSet{n1, n3}", varTable);
        assertEquals("Set{n3,n3,n1}        ->sortedBy(i | i)", "OrderedSet{n1, n3}", varTable);

        assertEquals("Set{n1,n2,n3,null}   ->sortedBy(i | i)", "invalid", varTable);
        assertEquals("Set{n1,n2,n3,invalid}->sortedBy(i | i)", "invalid", varTable);
    }
}

