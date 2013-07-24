/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases;

import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.Element;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TypeCase {
    
    private TypeCase() {
    }

    public static TypeCase getInstance() {
        return TypeCaseHolder.INSTANCE;
    }
    
    public void out(APathTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        List<String> ids = (List<String>) map.remove(node.getNameCS());
        
        Element result;
        result = env.lookupPathName(ids);
        
        if (result == null || !(result instanceof Classifier)) {
            throw new OclParserException(node, "The path name must be a name of a Classifier in current environment.");
        }
        
        map.put(node, result);
    }
    
    public void out(ASetTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionType result = new SetType();
        
        result.setElementType((Classifier) map.remove(node.getTypeCS()));
        
        map.put(node, result);
    }
    
    public void out(ABagTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionType result = new BagType();
        
        result.setElementType((Classifier) map.remove(node.getTypeCS()));
        
        map.put(node, result);
    }
    
    public void out(ASequenceTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionType result = new SequenceType();
        
        result.setElementType((Classifier) map.remove(node.getTypeCS()));
        
        map.put(node, result);
    }

    public void out(ACollectionTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionType result = new CollectionType();
        
        result.setElementType((Classifier) map.remove(node.getTypeCS()));
        
        map.put(node, result);
    }
    
    public void out(AOrderedSetTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionType result = new OrderedSetType();
        
        result.setElementType((Classifier) map.remove(node.getTypeCS()));
        
        map.put(node, result);
    }

    public void out(ATupleTypeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        TupleType result = new TupleType();
        
        if (node.getElems().isEmpty()) {
            throw new OclParserException(node, "Tuple types without attributes has no sense!");
        }
        
        ArrayList<Variable> vars = new ArrayList<Variable>(node.getElems().size());
        
        for (PVariableDeclarationCS att : node.getElems()) {
            Variable var = (Variable) map.remove(att);
            vars.add(var);
            
            result.addAttribute(var.getName(), var.getType());
        }
        for (Variable variable : vars) {
            if (variable.getName() == null) {
                throw new OclParserException(node, "There is a variable that has no name!");
            }
            if (variable.getInitExpression() != null) {
                throw new OclParserException(node, variable.getName() + " has an initializer "
                        + "expression which is illegal in tuple attributes");
            }
            if (variable.getType() == null) {
                throw new OclParserException(node, variable.getName() + " has an undefined type, "
                        + "which is illegal in tuple attributes.");
            }
        }
        
        map.put(node, result);
    }
    
    private static class TypeCaseHolder {

        private static final TypeCase INSTANCE = new TypeCase();

        private TypeCaseHolder() {
        }
    }
}
