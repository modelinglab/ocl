/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.CollectionLiteralPartCase;
import org.modelinglab.ocl.core.ast.expressions.BooleanLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.CollectionItem;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionLiteralPartCaseTest extends CaseTestAbstract {
   
    @Test
    public void testOut_ARangeCollectionLiteralPartCS() {
        ARangeCollectionLiteralPartCS node = new ARangeCollectionLiteralPartCS();
        
        ACollectionRangeCS aCollectionRangeCS = new ACollectionRangeCS(null, null);
        CollectionRange range = new CollectionRange();
        resultMap.put(aCollectionRangeCS, range);
        
        node.setCollectionRangeCS(aCollectionRangeCS);
        
        expectedMap.put(node, range);
        
        CollectionLiteralPartCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }

    @Test
    public void testOut_AOclExpressionCollectionLiteralPartCS() {
        POclExpressionCS subExp = new ATrueLiteralOclExpressionCS();
        AOclExpressionCollectionLiteralPartCS node = new AOclExpressionCollectionLiteralPartCS(subExp);
        
        OclExpression exp = new BooleanLiteralExp(Boolean.TRUE);
        
        resultMap.put(subExp, exp);
        
        CollectionItem item = new CollectionItem();
        item.setItem(exp.clone());
        expectedMap.put(node, item);
        
        CollectionLiteralPartCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
        
    }
}
