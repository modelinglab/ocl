/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases;

import org.modelinglab.ocl.parser.walker.cases.NameCase;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.parser.sablecc.node.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NameCaseTest extends CaseTestAbstract {

    /**
     * Test of out method, of class NameCase.
     */
    @Test
    public void testOutASimpleSimpleNameCS() {
        ASimpleSimpleNameCS node = new ASimpleSimpleNameCS();
        
        TIdentifier id = new TIdentifier("test");
        node.setIdentifier(id);
        
        expectedMap.put(node, "test");
        
        NameCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }

    @Test
    public void testOutAPathNameCS_OnlyFirst() {
        //tests "asd"
        APathNameCS node = new APathNameCS();
        PSimpleNameCS first = new ASimpleSimpleNameCS(new TIdentifier("asd"));
        node.setFirstName(first);
        
        resultMap.put(first, "asd");
        
        ArrayList<String> expectedResult = new ArrayList<String>();
        expectedResult.add("asd");
        expectedMap.put(node, expectedResult);
        
        NameCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }

    @Test
    public void testOutAPathNameCS_WithRest() {
        //tests "asd0.asd1.asd2.[...].asd9"
        APathNameCS node = new APathNameCS();
        
        LinkedList<PSimpleNameCS> names = new LinkedList<PSimpleNameCS>();
        LinkedList<String> expectedResult = new LinkedList<String>();
        
        for (int i = 0; i < 10; i++) {
            PSimpleNameCS name = new ASimpleSimpleNameCS(new TIdentifier("asd" + i));
            resultMap.put(name, "asd" + i);
            
            names.addLast(name);
            
            expectedResult.add("asd" + i);
        }
        
        node.setFirstName(names.removeFirst());
        node.setRestNames(names);        
        
        expectedMap.put(node, expectedResult);
        
        NameCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }

    /**
     * Test of out method, of class NameCase.
     */
    @Test
    public void testOutAPathNameNameCS() {
        APathNameNameCS node = new APathNameNameCS();
        APathNameCS pathName = new APathNameCS();
        
        node.setPathNameCS(pathName);
        
        resultMap.put(pathName, new InvalidLiteralExp());
        expectedMap.put(node, new InvalidLiteralExp());
        
        NameCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }

    /**
     * Test of out method, of class NameCase
     */
    @Test
    public void testOutASimpleNameNameCS() {
        ASimpleNameNameCS node = new ASimpleNameNameCS();
        PSimpleNameCS simpleName = new ASimpleSimpleNameCS();
        
        node.setSimpleNameCS(simpleName);
        
        resultMap.put(simpleName, "aStringFromSimpleName");
        expectedMap.put(node, Arrays.asList(new String[] {"aStringFromSimpleName"}));
        
        NameCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }
}
