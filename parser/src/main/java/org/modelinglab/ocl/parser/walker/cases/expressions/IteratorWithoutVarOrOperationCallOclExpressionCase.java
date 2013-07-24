/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AIteratorWithoutVarOrOperationCallOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.POclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import org.modelinglab.ocl.parser.walker.WalkerUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 * TODO: this class is not tested!
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IteratorWithoutVarOrOperationCallOclExpressionCase {

    private IteratorWithoutVarOrOperationCallOclExpressionCase() {
    }

    public static IteratorWithoutVarOrOperationCallOclExpressionCase getInstance() {
        return IteratorWithoutVarOrOperationCallOclExpressionCaseHolder.INSTANCE;
    }
    
    public void preBodyOrArgs(AIteratorWithoutVarOrOperationCallOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression source = (OclExpression) map.remove(node.getSource());
        source = WalkerUtils.getInstance().implicitCollectionCast(source, env);
        map.put(node.getSource(), source);
        
        String name = (String) map.get(node.getSimpleNameCS());
        
        if (env.isIterator((CollectionType) source.getType(), name)) {
            //if the node is an iterator, then we need to add the implicit iteratior variable
            Variable var = new Variable();
            var.setName("");
            var.setType(((CollectionType) source.getType()).getElementType());
            
            map.put(node, var);
            
            env.addScope();
            env.addElement(var, true);
        }
    }

    public void out(AIteratorWithoutVarOrOperationCallOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression result;

        OclExpression source = (OclExpression) map.remove(node.getSource()); //preBodyOrArgs method implies that this expression is a collection 

        String name = (String) map.remove(node.getSimpleNameCS());

        List<OclExpression> arguments = new ArrayList<OclExpression>(node.getBodyOrArgs().size());
        for (POclExpressionCS pOclExpressionCS : node.getBodyOrArgs()) {
            arguments.add((OclExpression) map.remove(pOclExpressionCS));
        }

        if (env.isIterator((CollectionType) source.getType(), name)) {
            /*
             * if the node is an iterator, in method added the implicit iterator variable with node 
             * key and added a new scope that we need to remove
             */            
            env.removeScope();        
            if (arguments.size() != 1) {
                throw new OclParserException(node, "One (and only one) expression was expected as iterator argument.");
            }
            OclExpression body = arguments.get(0);
            IteratorExp itExp;
            try {
                itExp = env.lookupIterator((CollectionType) source.getType(), name, body.getType());
            } catch (IllegalIteratorException ex) {
                throw new OclParserException(node, ex);
            }
            result = itExp;

            Variable var = (Variable) map.remove(node); //is stored in in method
            itExp.setIterators(Arrays.asList(new Variable[]{var}));

            itExp.setBody(body);
            itExp.setSource(source);
        } else { //it should be a collection operation call with arrow (->)
            OperationCallExp opCall = new OperationCallExp();
            result = opCall;

            opCall.setArguments(arguments);
            opCall.setSource(source);

            List<Classifier> argTypes = new ArrayList<Classifier>(arguments.size());
            for (OclExpression arg : arguments) {
                argTypes.add(arg.getType());
            }
            try {
                Operation op = env.lookupOperation(source, name, argTypes);
                opCall.setReferredOperation(op);
            }
            catch (OclException ex) {
                throw new OclParserException(node, ex);
            }

            if (opCall.getReferredOperation() == null) {
                throw new OclInvalidOperationException(name, opCall, argTypes, node);
            }
        }

        map.put(node, result);
    }

    private static class IteratorWithoutVarOrOperationCallOclExpressionCaseHolder {

        private static final IteratorWithoutVarOrOperationCallOclExpressionCase INSTANCE = new IteratorWithoutVarOrOperationCallOclExpressionCase();

        private IteratorWithoutVarOrOperationCallOclExpressionCaseHolder() {
        }
    }
}
