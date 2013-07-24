/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.parser.CompanyEnvironment;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import java.util.Iterator;
import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CaseTestAbstract {

    protected ConcreteToAbstractMap expectedMap = new ConcreteToAbstractMap();
    protected ConcreteToAbstractMap resultMap = new ConcreteToAbstractMap();
    protected StaticEnvironment env = CompanyEnvironment.createEnvironment();
    protected Variable self;
    
    public void addSelf(String className) {
        env.addScope();
        
        self = new Variable();
        self.setName("self");
        UmlClass type = (UmlClass) env.lookup(className);
        assert type != null;        
        self.setType(type);
        
        env.addElement(self, true);
    }

    public void removeSelf() {
        env.removeScope();
    }
    
    @Before
    public void setUp() {
        for (Iterator<Node> it = expectedMap.keySet().iterator(); it.hasNext(); ) {
            resultMap.remove(it.next());
            it.remove();
        }
        assert expectedMap.equals(resultMap);
    }

    @After
    public void tearDown() {
    }
    
    public void clear() {
        for (Iterator<Node> it = expectedMap.keySet().iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
        for (Iterator<Node> it = resultMap.keySet().iterator(); it.hasNext(); ) {
            it.next();
            it.remove();
        }
    }

    protected void checkMaps() {
        assert expectedMap.size() == resultMap.size() 
                : "Expected map has "+expectedMap.size()+" keys and result has "+resultMap.size()+".";
        for (Node node : expectedMap.keySet()) {
            Object expected = expectedMap.get(node);
            Object result = resultMap.get(node);
            assert expected.equals(result) :
                    "Expected result is different to calculated result: \n"
                    + "\t - expected:   " + expected + '\n'
                    + "\t - calculated: " + result;
        }
    }
}
