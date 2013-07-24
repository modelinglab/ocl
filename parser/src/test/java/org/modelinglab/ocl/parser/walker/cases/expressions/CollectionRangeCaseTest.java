/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.junit.Test;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;
import org.modelinglab.ocl.core.ast.expressions.IntegerLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.sablecc.node.AAndOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.ACollectionRangeCS;
import org.modelinglab.ocl.parser.sablecc.node.POclExpressionCS;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionRangeCaseTest extends CaseTestAbstract {
    
    @Test
    public void testOut() {
        ACollectionRangeCS node = new ACollectionRangeCS();
        
        POclExpressionCS pOclExp1 = new AAndOclExpressionCS();
        node.setOclExpressionCS1(pOclExp1);
        OclExpression first = new IntegerLiteralExp(2l);
        resultMap.put(pOclExp1, first);
        
        POclExpressionCS pOclExp2 = new AAndOclExpressionCS();
        node.setOclExpressionCS2(pOclExp2);
        OclExpression last = new IntegerLiteralExp(3l);
        resultMap.put(pOclExp2, last);
        
        CollectionRange result = new CollectionRange();
        result.setFirst(first.clone());
        result.setLast(last.clone());
        
        expectedMap.put(node, result);
        
        CollectionRangeCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
}
