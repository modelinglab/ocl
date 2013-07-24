/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.parser.walker.cases.expressions.CollectionOperationWithoutArgumentsCase;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.operations.collection.Size;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**bbbb
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionOperationWithoutArgumentsCaseTest extends CaseTestAbstract {
    @Test
    public void testOut_ACollectionOperationWithoutArgumentsOclExpressionCS() {
        ACollectionOperationWithoutArgumentsOclExpressionCS node = new ACollectionOperationWithoutArgumentsOclExpressionCS();
        
        PSimpleNameCS simpleName = new ASimpleSimpleNameCS(new TIdentifier("size"));
        node.setSimpleNameCS(simpleName);
        resultMap.put(simpleName, "size");
        
        POclExpressionCS source = new ATrueLiteralOclExpressionCS();
        node.setSource(source);
        
        CollectionLiteralExp literalExp = new CollectionLiteralExp();
        literalExp.setType(CollectionType.newCollection(CollectionType.CollectionKind.BAG));
        literalExp.getType().setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        resultMap.put(source, literalExp.clone());
        
        CollectionOperationWithoutArgumentsCase.getInstance().out(node, resultMap, env);
        
        OperationCallExp result = new OperationCallExp();
        List<OclExpression> args = Collections.emptyList();
        result.setArguments(args);
        result.setSource(literalExp.clone());
        result.setReferredOperation(new Size(Size.createTemplateOperation(), PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER)));
        
        expectedMap.put(node, result);
        
        checkMaps();
        
        clear();
        /*
         * If the operation does not exist in source type, an exception should be thrown
         */
        resultMap.put(simpleName, "_fakeOperation");
        resultMap.put(source, literalExp.clone());
        expectedMap.put(node, result);
        try {
            CollectionOperationWithoutArgumentsCase.getInstance().out(node, resultMap, env);
            assert false : "If the operation does not exist in source type, an exception should be thrown";
        }
        catch (OclParserException ex) {
            
        }
        clear();
    }
}
