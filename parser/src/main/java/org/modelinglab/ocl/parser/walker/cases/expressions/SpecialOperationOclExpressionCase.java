/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class SpecialOperationOclExpressionCase {
    
    private SpecialOperationOclExpressionCase() {
    }
    
    public static SpecialOperationOclExpressionCase getInstance() {
        return SpecialOperationOclExpressionCaseHolder.INSTANCE;
    }

    public void out(AAndOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "and",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(ADivOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "/",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(ALessOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "<",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(ALessOrEqualOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "<=",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AMinusOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "-",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

        public void out(AMultOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "*",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(APlusOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        
        OclExpression source = (OclExpression) map.get(node.getOclExpressionCS1());
        OclExpression arg = (OclExpression) map.get(node.getOclExpressionCS2());
        
        if (source.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING))
                && arg.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING))) {
            
            infixOperation(
                node,
                "concat",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
        }
        else {
            infixOperation(
                    node,
                    "+",
                    (OclExpression) map.remove(node.getOclExpressionCS1()),
                    (OclExpression) map.remove(node.getOclExpressionCS2()),
                    map,
                    env);
        }
    }

    public void out(AUnaryMinusOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        unaryOperation(
                node,
                "-",
                (OclExpression) map.remove(node.getOclExpressionCS()),
                map,
                env);
    }

    public void out(ANotOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        unaryOperation(
                node,
                "not",
                (OclExpression) map.remove(node.getOclExpressionCS()),
                map,
                env);
    }

    public void out(AMoreOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                ">",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AMoreOrEqualOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                ">=",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(ANotEqualOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "<>",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AEqualOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "=",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AOrOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "or",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AXorOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "xor",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    public void out(AImpliesOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        infixOperation(
                node,
                "implies",
                (OclExpression) map.remove(node.getOclExpressionCS1()),
                (OclExpression) map.remove(node.getOclExpressionCS2()),
                map,
                env);
    }

    private void unaryOperation(Node node, String opName, OclExpression source, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OperationCallExp opCall = new OperationCallExp();

        opCall.setSource(source);

        List<Classifier> argTypes = Collections.emptyList();

        try {
            opCall.setReferredOperation(env.lookupOperation(
                opCall.getSource(),
                opName,
                argTypes));
        }
        catch (AmbiguosOperationCall ex) {
            throw new OclParserException(node, ex);
        }

        if (opCall.getReferredOperation() == null) {
            throw new OclInvalidOperationException(opName, opCall, argTypes, node);
        }
        map.put(node, opCall);
    }

    private void infixOperation(Node node, String opName, OclExpression arg1, OclExpression arg2, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OperationCallExp result = new OperationCallExp();

        result.setSource(arg1);
        result.addArgument(arg2);

        List<Classifier> argTypes = Arrays.asList(new Classifier[]{arg2.getType()});

        try {
            result.setReferredOperation(env.lookupOperation(
                result.getSource(),
                opName,
                argTypes));
        }
        catch (AmbiguosOperationCall exception) {
            throw new OclParserException(node, exception);
        }

        if (result.getReferredOperation() == null) {
            throw new OclInvalidOperationException(opName, result, argTypes, node);
        }
        map.put(node, result);
    }
    
    private static class SpecialOperationOclExpressionCaseHolder {

        private static final SpecialOperationOclExpressionCase INSTANCE = new SpecialOperationOclExpressionCase();

        private SpecialOperationOclExpressionCaseHolder() {
        }
    }
}
