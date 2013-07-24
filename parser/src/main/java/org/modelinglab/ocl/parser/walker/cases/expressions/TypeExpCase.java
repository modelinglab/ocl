/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.TypeExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.ATypeOclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TypeExpCase {

    private TypeExpCase() {
    }
    
    public void out(ATypeOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        Classifier type = (Classifier) map.remove(node.getType());
        
        TypeExp result = type.getClassifierType().createExpression();
        
        map.put(node, result);
    }

    public static TypeExpCase getInstance() {
        return TypeExpCaseHolder.INSTANCE;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return TypeExpCase.getInstance();
    }

    private static class TypeExpCaseHolder {
        private static final TypeExpCase INSTANCE = new TypeExpCase();

        private TypeExpCaseHolder() {
        }
    }
 }
