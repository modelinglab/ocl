/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import org.modelinglab.ocl.parser.sablecc.node.ACollectionOperationWithoutArgumentsOclExpressionCS;
import org.modelinglab.ocl.parser.walker.WalkerUtils;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionOperationWithoutArgumentsCase {
    
    private CollectionOperationWithoutArgumentsCase() {
    }
    
    public static CollectionOperationWithoutArgumentsCase getInstance() {
        return CollectionOperationHolder.INSTANCE;
    }

    public void out(ACollectionOperationWithoutArgumentsOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        try {
            OperationCallExp result = new OperationCallExp();
        
//            List<String> names = (List<String>) map.remove(node.getSimpleNameCS());
//
//            /*
//             * TODO: Check if names could be a path
//             */
//            String opName = names.get(names.size() - 1);
            String opName = (String) map.remove(node.getSimpleNameCS());

            OclExpression source = (OclExpression) map.remove(node.getSource());
            source = WalkerUtils.getInstance().implicitCollectionCast(source, env);
            result.setSource(source);
            
            List<Classifier> argTypes = Collections.emptyList();
            Operation op = env.lookupOperation(source, opName, argTypes);
            
            result.setReferredOperation(op);
            List<OclExpression> args = Collections.emptyList();
            result.setArguments(args);

            if (result.getReferredOperation() == null) {
                throw new OclInvalidOperationException(opName, result, argTypes, node);
            }

            map.put(node, result);
        }
        catch (OclException ex) {
            throw new OclParserException(node, ex);
        }
    }
    
    private static class CollectionOperationHolder {

        private static final CollectionOperationWithoutArgumentsCase INSTANCE = new CollectionOperationWithoutArgumentsCase();

        private CollectionOperationHolder() {
        }
    }
}
