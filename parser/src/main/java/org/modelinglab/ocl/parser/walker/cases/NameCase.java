/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NameCase {
    
    private NameCase() {
    }
    
    public static NameCase getInstance() {
        return SimpleNameCaseHolder.INSTANCE;
    }
    
    public void out(ASimpleSimpleNameCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        String result = node.getIdentifier().getText();
        
        concreteNodeToAbstractNode.put(node, result);
    }

    public void out(APathNameCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        ArrayList<String> result = new ArrayList<String>(node.getRestNames().size() + 1);
        
        result.add((String) concreteNodeToAbstractNode.remove(node.getFirstName()));
        for (PSimpleNameCS pSimpleNameCS : node.getRestNames()) {
            result.add((String) concreteNodeToAbstractNode.remove(pSimpleNameCS));
        }
        
        concreteNodeToAbstractNode.put(node, result);
    }
    
    public void out(APathNameNameCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        concreteNodeToAbstractNode.put(node, concreteNodeToAbstractNode.remove(node.getPathNameCS()));
    }
    
    public void out(ASimpleNameNameCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        String identifier = (String) concreteNodeToAbstractNode.remove(node.getSimpleNameCS());
        
        concreteNodeToAbstractNode.put(node, Arrays.asList(new String[] {identifier}));
    }
    
    private static class SimpleNameCaseHolder {

        private static final NameCase INSTANCE = new NameCase();

        private SimpleNameCaseHolder() {
        }
    }
}
