/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.*;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclVisitorAdapter;
import org.modelinglab.ocl.core.ast.utils.Visitable;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.ANameOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.pojos.UndeclaredVariable;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NameExpCase extends OclVisitorAdapter<OclExpression, NameExpCase.Argument> {

    private NameExpCase() {
    }

    public static NameExpCase getInstance() {
        return EnumOrVariableOrFeatureCallCaseHolder.INSTANCE;
    }

    public void out(ANameOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {

        List<String> names = (List<String>) map.remove(node.getName());
        final String tokenName = names.get(names.size() - 1);

        Element element = env.lookupPathName(names);
        if (element == null) {
            element = env.lookupImplicitAssociationEnd(tokenName);
        }
        if (element == null) {
            element = env.lookupImplicitAttribute(tokenName);
        }
        if (element == null) {
            //we are in iterator variable declaration production, so this unknown literal is a
            //variable declaration without init expression and without type.
            if (env.lookup(UndeclaredVariable.INVALID_VAR_NAME) != null) {
                Variable var = new Variable();
                var.setName(tokenName);
                element = var;
            }
            else {
                throw new OclParserException(node, "It was expected that " + tokenName + " was a "
                + "EnumLiteralExp, Variable o FeatureCall, but it does not exist in the environment.");
            }
        }

        Argument arg = new Argument(node, env, tokenName);
        
        OclExpression result = element.accept(this, arg);

        map.put(node, result);
    }

    @Override
    public OclExpression defaultCase(Visitable obj, Argument arguments) {
        throw new OclParserException(arguments.node, "It was expected that " + obj + " was a "
                + "EnumLiteralExp, Variable, Classifier o FeatureCall");
    }

    @Override
    public OclExpression visit(UmlEnumLiteral enumLiteral, Argument arguments) {
        EnumLiteralExp result = new EnumLiteralExp();
        result.setEnumerationLiteral(enumLiteral);

        return result;
    }

    @Override
    public OclExpression visit(AssociationEnd o, Argument arguments) {
        AssociationEndCallExp result = new AssociationEndCallExp();
        result.setPre(false);

        TypedElement source = arguments.env.findImplicitSourceForAssociationEnd(o.getName());
        assert source != null : "The source of " + o.getName() + " implicit property is invalid!";
        result.setSource(source.accept(ImplicitSourceToOclExpression.instance, arguments.node));
        result.setReferredAssociationEnd(o);

        return result;
    }

    @Override
    public OclExpression visit(Attribute o, Argument arguments) {
        AttributeCallExp result = new AttributeCallExp();
        result.setPre(false);

        TypedElement source = arguments.env.findImplicitSourceForAttribute(o.getName());
        assert source != null : "The source of " + o.getName() + " implicit property is invalid!";
        result.setSource(source.accept(ImplicitSourceToOclExpression.instance, arguments.node));
        result.setReferredAttribute(o);

        return result;
    }

    @Override
    public OclExpression visit(Variable var, Argument argument) {
        VariableExp result = new VariableExp();
        result.setReferredVariable(var);
        return result;
    }
    
    @Override
    public OclExpression visit(Classifier classifier, Argument argument) {
        return classifier.getClassifierType().createExpression();
    }

    public static class Argument {

        final Node node;
        final StaticEnvironment env;
        final String tokenName;

        public Argument(Node node, StaticEnvironment env, String tokenName) {
            this.node = node;
            this.env = env;
            this.tokenName = tokenName;
        }
    }

    private static class EnumOrVariableOrFeatureCallCaseHolder {

        private static final NameExpCase INSTANCE = new NameExpCase();

        private EnumOrVariableOrFeatureCallCaseHolder() {
        }
    }
}
