/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LiteralCase {

    private LiteralCase() {
    }

    public static LiteralCase getInstance() {
        return PrimitiveLiteralCaseHolder.INSTANCE;
    }
    
    public void out(ATrueLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        map.put(node, new BooleanLiteralExp(Boolean.TRUE));
    }

    public void out(AFalseLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        map.put(node, new BooleanLiteralExp(Boolean.FALSE));
    }

    public void out(AIntegerLiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        IntegerLiteralExp result = new IntegerLiteralExp();

        try {
            result.setValue(Long.valueOf(node.getInteger().getText()));
        } catch (NumberFormatException ex) {
            throw new OclParserException(node, ex);
        }
        concreteNodeToAbstractNode.put(node, result);
    }

    public void out(ARealLiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        RealLiteralExp result = new RealLiteralExp();

        try {
            result.setValue(Double.valueOf(node.getReal().getText()));
        } catch (NumberFormatException ex) {
            throw new OclParserException(node, ex);
        }

        concreteNodeToAbstractNode.put(node, result);
    }
    
    public void out(AString1LiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        StringLiteralExp result = new StringLiteralExp();
        
        result.setValue(node.getString().getText().substring(1, node.getString().getText().length()-1));
        
        concreteNodeToAbstractNode.put(node, result);
    }

    public void out(AString2LiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        StringLiteralExp result = new StringLiteralExp();
        
        result.setValue(node.getString2().getText().substring(1, node.getString2().getText().length()-1));
        
        concreteNodeToAbstractNode.put(node, result);
    }

    public void out(ATupleLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        TupleLiteralExp result = new TupleLiteralExp();
        
        ArrayList<Variable> vars = new ArrayList<>(node.getVars().size());
        
        for (PVariableDeclarationCS varDec : node.getVars()) {
            Variable var = (Variable) map.remove(varDec);
            vars.add(var);
            
            TupleLiteralPart tlp = new TupleLiteralPart(var);
            result.addPart(tlp);
        }
        
        for (Variable variable : vars) {
            if (variable.getName() == null) {
                throw new OclParserException(node, "There is a tuple literal part that has no name!");
            }
            if (variable.getInitExpression() == null) {
                throw new OclParserException(node, variable.getName() + " has no initializer expression "
                        + " which is illegal in literal tuple attributes");
            }
            if (variable.getType().oclIsUndefined()) {
                throw new OclParserException(node, variable.getName() + " type is OclUndefined which "
                        + "is illegal in literal tuple attributes");
            }
            
        }
        
        map.put(node, result);
    }
    
    public void out(ABagLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression result = createCollectionLiteral(node, CollectionType.CollectionKind.BAG, node.getElements(), map);

        map.put(node, result);
    }

    public void out(AOrderedSetLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression result = createCollectionLiteral(node, CollectionType.CollectionKind.ORDERED_SET, node.getElements(), map);

        map.put(node, result);
    }

    public void out(ASequenceLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression result = createCollectionLiteral(node, CollectionType.CollectionKind.SEQUENCE, node.getElements(), map);

        map.put(node, result);
    }

    public void out(ASetLiteralOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression result = createCollectionLiteral(node, CollectionType.CollectionKind.SET, node.getElements(), map);

        map.put(node, result);
    }

    private CollectionLiteralExp createCollectionLiteral(
            Node node,
            CollectionType.CollectionKind kind,
            Collection<PCollectionLiteralPartCS> parts,
            ConcreteToAbstractMap map) throws OclParserException {

        CollectionLiteralExp literal = new CollectionLiteralExp();

        List<Classifier> elementTypes = new ArrayList<>(parts.size());

        for (PCollectionLiteralPartCS part : parts) {
            CollectionLiteralPart abstractPart = (CollectionLiteralPart) map.remove(part);

            literal.addPart(abstractPart);

            elementTypes.add(abstractPart.getType());
        }

        CollectionType type = CollectionType.newCollection(kind);
        type.setElementType(OclUtils.getLowestSharedType(elementTypes));

        literal.setType(type);

        return literal;
    }

    public void out(ANullLiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        NullLiteralExp result = new NullLiteralExp();
        
        concreteNodeToAbstractNode.put(node, result);
    }
    
    public void out(AInvalidLiteralOclExpressionCS node, ConcreteToAbstractMap concreteNodeToAbstractNode, StaticEnvironment env) throws OclParserException {
        InvalidLiteralExp result = new InvalidLiteralExp();
        
        concreteNodeToAbstractNode.put(node, result);
    }
    
    private static class PrimitiveLiteralCaseHolder {

        private static final LiteralCase INSTANCE = new LiteralCase();

        private PrimitiveLiteralCaseHolder() {
        }
    }
}
