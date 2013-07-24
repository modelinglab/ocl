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
import org.modelinglab.ocl.core.ast.utils.Visitable;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AImplicitOperationCallOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.POclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ImplicitOperationCallOclExpressionCase {

    private ImplicitOperationCallOclExpressionCase() {
    }

    public static ImplicitOperationCallOclExpressionCase getInstance() {
        return OclExpressionCaseHolder.INSTANCE;
    }

    /**
     * TODO: This implementation does not support call to static operations
     * @param node
     * @param map
     * @param env 
     * @throws OclParserException  
     */
    public void out(AImplicitOperationCallOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        try {
            OperationCallExp result = new OperationCallExp();

            List<String> string = (List<String>) map.remove(node.getNameCS());
            String opName = string.get(string.size() - 1);

            List<OclExpression> args = new ArrayList<OclExpression>(node.getArgs().size());
            List<Classifier> argTypes = new ArrayList<Classifier>(node.getArgs().size());
            for (POclExpressionCS argExp : node.getArgs()) {
                OclExpression arg = (OclExpression) map.remove(argExp);

                args.add(arg);
                argTypes.add(arg.getType());
            }

            Operation op = env.lookupImplicitOperation(opName, argTypes);

            result.setReferredOperation(op);
            Visitable source = env.findImplicitSourceForOperation(opName, argTypes);
            if (source != null) {
                result.setSource(source.accept(ImplicitSourceToOclExpression.instance, node));
            }
            result.setArguments(args);

            if (result.getReferredOperation() == null) {
                throw new OclInvalidOperationException(opName, result, argTypes, node);
            }
            map.put(node, result);
        } catch (OclException ex) {
            throw new OclParserException(node, ex);
        }
    }

    private static class OclExpressionCaseHolder {

        private static final ImplicitOperationCallOclExpressionCase INSTANCE = new ImplicitOperationCallOclExpressionCase();

        private OclExpressionCaseHolder() {
        }
    }
}
