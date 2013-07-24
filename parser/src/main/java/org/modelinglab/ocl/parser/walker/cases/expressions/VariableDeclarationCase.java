/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VariableDeclarationCase {

    private VariableDeclarationCase() {
    }

    public static VariableDeclarationCase getInstance() {
        return VariableCaseHolder.INSTANCE;
    }

    public void out(ATypedAndInitVariableDeclarationCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        Variable result = new Variable();

        result.setName((String) map.remove(node.getName()));
        result.setInitExpression((OclExpression) map.remove(node.getValue()));
        result.setType((Classifier) map.remove(node.getType()));

        checkVariable(node, result, env);

        map.put(node, result);
    }

    public void out(AInitVariableDeclarationCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        Variable result = new Variable();

        result.setName((String) map.remove(node.getName()));
        result.setInitExpression((OclExpression) map.remove(node.getValue()));
        /*
         * gortiz: OCL 2.2 Specification (VariableDeclarationCS) said that the type should be
         * undefined in this case!
         */
        result.setType(VoidType.getInstance());

        checkVariable(node, result, env);

        map.put(node, result);
    }

    public void out(ATypedVariableDeclarationCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        Variable result = new Variable();

        result.setName((String) map.remove(node.getName()));
        result.setInitExpression(null);
        result.setType((Classifier) map.remove(node.getType()));

        checkVariable(node, result, env);

        map.put(node, result);
    }

    public void out(ANoTypedNoInitVariableDeclarationCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        Variable result = new Variable();

        result.setName((String) map.remove(node.getName()));
        result.setType(VoidType.getInstance());

        checkVariable(node, result, env);

        map.put(node, result);
    }

    private void checkVariable(Node node, Variable var, StaticEnvironment env) throws OclParserException {
        if (env.lookup(var.getName()) != null) {
            throw new OclParserException(node, "Variable with name " + var.getName() +" is already "
                    + "defined in the current scope.");
        }
        
        /*
         * gortiz: OCL 2.2 Specification (VariableDeclarationCS) said that the type should be
         * undefined when the type is not specified. So in the case of a declaration with expression
         * but no type, type will be set to null and the expression could not conform!
         */
//        if (var.getInitExpression() == null) {
//            return;
//        }
//        if (var.getInitExpression().getType().conformsTo(var.getType())) {
//            return;
//        }
//        throw new OclParserException(node, var.getName() + " init expression ("
//                + var.getInitExpression() + ") does not conform to variable type ("
//                + var.getType().getName() + ")");
    }

    private static class VariableCaseHolder {

        private static final VariableDeclarationCase INSTANCE = new VariableDeclarationCase();

        private VariableCaseHolder() {
        }
    }
}
