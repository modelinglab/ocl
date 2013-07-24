/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AIteratorWithVarsOclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.WalkerUtils;
import org.modelinglab.ocl.parser.walker.pojos.ItVariableList;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class IteratorWithVarsOclExpressionCase {

    private IteratorWithVarsOclExpressionCase() {
    }

    public static IteratorWithVarsOclExpressionCase getInstance() {
        return IteratorOclExpressionCaseHolder.INSTANCE;
    }
    
    public void preBody(AIteratorWithVarsOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        /*
         * inside an iterator expression the body is evaluated with a new environment that
         * includes the iterator variable.
         */
        env.addScope();
        
        OclExpression source = (OclExpression) map.remove(node.getSource());
        source = WalkerUtils.getInstance().implicitCollectionCast(source, env);
        
        Object vars = map.get(node.getVars());
        
        if (!(vars instanceof ItVariableList)) {
            throw new OclParserException(node, "A list of iterator variables was expected.");
        }
        
        ItVariableList itVars = (ItVariableList) vars;
        
        for (Variable var : itVars) {
            if (var.getName() == null) {
                var.setName("");
            }
            if (var.getType() == null || var.getType().oclIsUndefined()) {
                var.setType(((CollectionType) source.getType()).getElementType());
            }
            if (var.getInitExpression() != null) {
                throw new OclParserException(node, "All iterator variables of an iterator expression must be not initialized");
            }
            env.addElement(var, true);
        }
        map.put(node.getSource(), source);
    }

    public void out(AIteratorWithVarsOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        /*
         * removes the body environment
         */
        env.removeScope();
        
        OclExpression source = (OclExpression) map.remove(node.getSource());
        assert source.getType() instanceof CollectionType;

        String itName = (String) map.remove(node.getSimpleNameCS());

        OclExpression body = (OclExpression) map.remove(node.getBody());

        IteratorExp result = null;

        try {
            result = env.lookupIterator((CollectionType) source.getType(), itName, body.getType());
        }
        catch (OclRuntimeException | IllegalIteratorException ex) {
            throw new OclParserException(node, ex);
        }
        
        result.setBody(body);
        result.setSource(source);

        Object vars = map.remove(node.getVars());
        
        if (!(vars instanceof ItVariableList)) {
            throw new OclParserException(node, "A list of iterator variables was expected.");
        }
        
        ItVariableList itVars = (ItVariableList) vars;
        /*
         * Variable correctness is verified in preBody method
         */
        result.setIterators(itVars);

        map.put(node, result);
    }
        
    private static class IteratorOclExpressionCaseHolder {

        private static final IteratorWithVarsOclExpressionCase INSTANCE = new IteratorWithVarsOclExpressionCase();

        private IteratorOclExpressionCaseHolder() {
        }
    }
}
