/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.ACollectionRangeCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionRangeCase {
    
    private CollectionRangeCase() {
    }
    
    public static CollectionRangeCase getInstance() {
        return CollectionRangeCaseHolder.INSTANCE;
    }

    public void out(ACollectionRangeCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        CollectionRange result = new CollectionRange();
        
        OclExpression first = (OclExpression) map.remove(node.getOclExpressionCS1());
        OclExpression last = (OclExpression) map.remove(node.getOclExpressionCS2());
        
        if (!first.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))) {
            throw new OclParserException(node, "It was expected that " +first+ ", as first element "
                    + "of a range, was a integer.");
        }
        if (!last.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))) {
            throw new OclParserException(node, "It was expected that " +last+ ", as last element "
                    + "of a range, was a integer.");
        }
        
        result.setFirst(first);
        result.setLast(last);
        
        map.put(node, result);
    }
    
    private static class CollectionRangeCaseHolder {

        private static final CollectionRangeCase INSTANCE = new CollectionRangeCase();

        private CollectionRangeCaseHolder() {
        }
    }
}
