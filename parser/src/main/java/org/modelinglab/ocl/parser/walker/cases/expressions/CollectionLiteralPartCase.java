/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.CollectionItem;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.sablecc.node.AOclExpressionCollectionLiteralPartCS;
import org.modelinglab.ocl.parser.sablecc.node.ARangeCollectionLiteralPartCS;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionLiteralPartCase {
    
    private CollectionLiteralPartCase() {
    }
    
    public static CollectionLiteralPartCase getInstance() {
        return CollectionLiteralPartCaseHolder.INSTANCE;
    }

    public void out(ARangeCollectionLiteralPartCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        map.put(node, map.remove(node.getCollectionRangeCS()));
    }
    
    public void out(AOclExpressionCollectionLiteralPartCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionItem result = new CollectionItem();
        
        result.setItem((OclExpression) map.remove(node.getOclExpressionCS()));
        
        map.put(node, result);
    }
    
    private static class CollectionLiteralPartCaseHolder {

        private static final CollectionLiteralPartCase INSTANCE = new CollectionLiteralPartCase();

        private CollectionLiteralPartCaseHolder() {
        }
    }
}
