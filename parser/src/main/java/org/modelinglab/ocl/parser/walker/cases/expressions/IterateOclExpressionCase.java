/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IterateExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AIterateOclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.WalkerUtils;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IterateOclExpressionCase {

    private IterateOclExpressionCase() {
    }

    public static IterateOclExpressionCase getInstance() {
        return IterateOclExpressionHolder.INSTANCE;
    }

    /**
     * This method should be called after all variables was translated and before body was translated.
     * <p>
     * Iterator and result variable will be removed from map, and will be added to a new scope in env
     * as implicit variables.
     * </p>
     * <p>
     * A partial IterateExp will be add to map with 'node' key
     * </p>
     * @param node
     * @param map
     * @param env
     * @throws OclParserException 
     */
    public void preBody(AIterateOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        IterateExp result = new IterateExp();

        /*
         * Source
         */
        OclExpression source = (OclExpression) map.remove(node.getSource());
        source = WalkerUtils.getInstance().implicitCollectionCast(source, env);
        result.setSource(source);

        /*
         * Iterator variable
         */
        Variable itVariable = getIterator(node, (CollectionType) source.getType(), map);
        result.addIterator(itVariable);

        /*
         * Result
         */
        Variable resultVar = getResultVar(node, map);
        result.setResult(resultVar);

        env.addScope();
        env.addElement(itVariable, true);
        env.addElement(result.getResult(), true);

        map.put(node, result);
    }

    /**
     * This method should be called after parse body. Removes the last scope in env
     * @param node
     * @param map
     * @param env
     * @throws OclParserException 
     */
    public void postBody(AIterateOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        env.removeScope();
    }

    /**
     * Change the IterateExp indexed as 'node' and set its name and body
     * @param node
     * @param map
     * @param env
     * @throws OclParserException 
     */
    public void out(AIterateOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {

        IterateExp result = (IterateExp) map.remove(node);

        /*
         * Body
         */
        result.setBody((OclExpression) map.remove(node.getBody()));

        map.put(node, result);
    }
    
    protected Variable getIterator(AIterateOclExpressionCS node, CollectionType sourceType, ConcreteToAbstractMap map) {
        Variable itVariable = null;
        if (node.getVar() != null) {
            itVariable = (Variable) map.remove(node.getVar());
        }
        if (itVariable == null) {
            itVariable = new Variable();
            itVariable.setName(""); //@gortiz: I do not like this default name, but the specification imposes that.
            itVariable.setType(sourceType.getElementType());
        }
        if (itVariable.getType() == null || itVariable.getType().oclIsUndefined()) {
            itVariable.setType(sourceType.getElementType());
        }
        if (itVariable.getInitExpression() != null) {
            throw new OclParserException(node, "The iterator variable of an iterate expression must be not initialized");
        }
        return itVariable;
    }
    
    protected Variable getResultVar(AIterateOclExpressionCS node, ConcreteToAbstractMap map) {
        Variable resultVar = (Variable) map.remove(node.getAcc());
        if (resultVar.getType() == null || resultVar.getType().oclIsUndefined()) {
            throw new OclParserException(node, "A result variable declaration must have a defined type.");
        }
        if (resultVar.getInitExpression() == null) {
            throw new OclParserException(node, "A result variable declaration must have an init value");
        }
        assert resultVar.getInitExpression().getType().conformsTo(resultVar.getType());
        
        return resultVar;
    }

    private static class IterateOclExpressionHolder {

        private static final IterateOclExpressionCase INSTANCE = new IterateOclExpressionCase();

        private IterateOclExpressionHolder() {
        }
    }
}
