/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker;

import org.modelinglab.ocl.parser.walker.cases.expressions.IfExpCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.SpecialOperationOclExpressionCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.LetCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.ArgOrItVarCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.NavigationOrAttributeCallOrImplicitCollectCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.ImplicitOperationCallOclExpressionCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.CollectionRangeCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.TypeExpCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.OperationCallOrImplicitCollectCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.CollectionOperationWithoutArgumentsCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.IterateOclExpressionCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.LiteralCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.NameExpCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.CollectionLiteralPartCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.IteratorWithoutVarOrOperationCallOclExpressionCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.VariableDeclarationCase;
import org.modelinglab.ocl.parser.walker.cases.expressions.IteratorWithVarsOclExpressionCase;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.sablecc.analysis.DepthFirstAdapter;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.cases.NameCase;
import org.modelinglab.ocl.parser.walker.cases.TypeCase;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclWalker extends DepthFirstAdapter {

    StaticEnvironment env;
    
    protected ConcreteToAbstractMap concreteNodeToAbstractNode;
    OclExpression oclAst;
    
    public OclWalker(StaticEnvironment env) {
        this.env = env;
    }

    @Override
    public void inStart(Start node) {
        concreteNodeToAbstractNode = new ConcreteToAbstractMap();
    }

    @Override
    public void outStart(Start node) {
        oclAst = (OclExpression) concreteNodeToAbstractNode.remove(node.getPOclExpressionCS());
        concreteNodeToAbstractNode.clear();
        concreteNodeToAbstractNode = null;
    }

    public OclExpression getOclAST() {
        return oclAst;
    }

    @Override
    public void defaultOut(Node node) {
        throw new AssertionError("All nodes should have its own redefined method.");
    }
    
    /*
     * Productions that delegates in NameCase
     */    
    @Override
    public void outASimpleNameNameCS(ASimpleNameNameCS node) {
        NameCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAPathNameCS(APathNameCS node) {
        NameCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAPathNameNameCS(APathNameNameCS node) {
        NameCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outASimpleSimpleNameCS(ASimpleSimpleNameCS node) {
        NameCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAIntegerLiteralOclExpressionCS(AIntegerLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in InvalidLiteralCase
     */
    @Override
    public void outAInvalidLiteralOclExpressionCS(AInvalidLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in ArgOrItVarCase
     */
    @Override
    public void outAItsArgOrItVar(AItsArgOrItVar node) {
        ArgOrItVarCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void inAUntypedItAndOtherItArgOrItVar(AUntypedItAndOtherItArgOrItVar node) {
        ArgOrItVarCase.getInstance().in(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAUntypedItAndOtherItArgOrItVar(AUntypedItAndOtherItArgOrItVar node) {
        ArgOrItVarCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in LiteralCase
     */    
    @Override
    public void outATrueLiteralOclExpressionCS(ATrueLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAFalseLiteralOclExpressionCS(AFalseLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outABagLiteralOclExpressionCS(ABagLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAOrderedSetLiteralOclExpressionCS(AOrderedSetLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outASequenceLiteralOclExpressionCS(ASequenceLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outASetLiteralOclExpressionCS(ASetLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outARealLiteralOclExpressionCS(ARealLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAString1LiteralOclExpressionCS(AString1LiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAString2LiteralOclExpressionCS(AString2LiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in NullLiteralCase
     */
    @Override
    public void outANullLiteralOclExpressionCS(ANullLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in TupleLiteralCase
     */
    @Override
    public void outATupleLiteralOclExpressionCS(ATupleLiteralOclExpressionCS node) {
        LiteralCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    
    /*
     * Productions that delegates in CollectionLiteralPartCase
     */
    @Override
    public void outARangeCollectionLiteralPartCS(ARangeCollectionLiteralPartCS node) {
        CollectionLiteralPartCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAOclExpressionCollectionLiteralPartCS(AOclExpressionCollectionLiteralPartCS node) {
        CollectionLiteralPartCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in CollectionOperationWithoutArgumentsCase
     */
    @Override
    public void outACollectionOperationWithoutArgumentsOclExpressionCS(ACollectionOperationWithoutArgumentsOclExpressionCS node) {
        CollectionOperationWithoutArgumentsCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in CollectionRangeCase
     */
    @Override
    public void outACollectionRangeCS(ACollectionRangeCS node) {
        CollectionRangeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in IfExpCase
     */
    @Override
    public void outAIfOclExpressionCS(AIfOclExpressionCS node) {
        IfExpCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in ImplicitOperationCallOclExpressionCase
     */
    @Override
    public void outAImplicitOperationCallOclExpressionCS(AImplicitOperationCallOclExpressionCS node) {
        ImplicitOperationCallOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in IterateOclExpressionCase
     */
    /**
     * We need to override the case method because the inherited attribute body.env needs var and acc
     * synthesized attributes
     * @param node 
     */
    @Override
    public void caseAIterateOclExpressionCS(AIterateOclExpressionCS node) {
        inAIterateOclExpressionCS(node);
        if(node.getSource() != null)
        {
            node.getSource().apply(this);
        }
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getAcc() != null)
        {
            node.getAcc().apply(this);
        }
        IterateOclExpressionCase.getInstance().preBody(node, concreteNodeToAbstractNode, env);
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        IterateOclExpressionCase.getInstance().postBody(node, concreteNodeToAbstractNode, env);
        outAIterateOclExpressionCS(node);
    }
    
    /*
     * Productions that delegates in IteratorWithVarsOclExpressionCase
     */
    @Override
    public void caseAIteratorWithVarsOclExpressionCS(AIteratorWithVarsOclExpressionCS node) {
        inAIteratorWithVarsOclExpressionCS(node);
        if(node.getSource() != null)
        {
            node.getSource().apply(this);
        }
        if(node.getSimpleNameCS() != null)
        {
            node.getSimpleNameCS().apply(this);
        }
        if(node.getVars() != null)
        {
            node.getVars().apply(this);
        }
        IteratorWithVarsOclExpressionCase.getInstance().preBody(node, concreteNodeToAbstractNode, env);
        if(node.getBody() != null)
        {
            node.getBody().apply(this);
        }
        IteratorWithVarsOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }

    /*
     * Productions that delegates in IteratorWithoutVarOrOperationCallOclExpressionCase
     */
    @Override
    public void caseAIteratorWithoutVarOrOperationCallOclExpressionCS(AIteratorWithoutVarOrOperationCallOclExpressionCS node)
    {
        inAIteratorWithoutVarOrOperationCallOclExpressionCS(node);
        if(node.getSource() != null)
        {
            node.getSource().apply(this);
        }
        if(node.getSimpleNameCS() != null)
        {
            node.getSimpleNameCS().apply(this);
        }
        IteratorWithoutVarOrOperationCallOclExpressionCase.getInstance().preBodyOrArgs(node, concreteNodeToAbstractNode, env);
        {
            List<POclExpressionCS> copy = new ArrayList<POclExpressionCS>(node.getBodyOrArgs());
            for(POclExpressionCS e : copy)
            {
                e.apply(this);
            }
        }
        outAIteratorWithoutVarOrOperationCallOclExpressionCS(node);
    }
    
    @Override
    public void outAIteratorWithoutVarOrOperationCallOclExpressionCS(AIteratorWithoutVarOrOperationCallOclExpressionCS node) {
        IteratorWithoutVarOrOperationCallOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in LetCase
     */
    @Override
    public void caseALetOclExpressionCS(ALetOclExpressionCS node) {
        LetCase.getInstance().in(node, concreteNodeToAbstractNode, env);
        inALetOclExpressionCS(node);
        {
            List<PVariableDeclarationCS> copy = new ArrayList<PVariableDeclarationCS>(node.getLetVars());
            for(PVariableDeclarationCS e : copy)
            {
                e.apply(this);
                LetCase.getInstance().postVariable(node, concreteNodeToAbstractNode, env, e);
            }
        }
        if(node.getInExpr() != null)
        {
            node.getInExpr().apply(this);
        }
        LetCase.getInstance().out(node, concreteNodeToAbstractNode, env);
        outALetOclExpressionCS(node);
    }

    @Override
    public void outALetOclExpressionCS(ALetOclExpressionCS node) {
    }
    
    /*
     * Productions that delegates in NavigationOrAttributeCallOrImplicitCollectCase
     */
    @Override
    public void outANavigationOrPropertyCallOrImplicitCollectOclExpressionCS(ANavigationOrPropertyCallOrImplicitCollectOclExpressionCS node) {
        NavigationOrAttributeCallOrImplicitCollectCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in OperationCallOrImplicitCollectCase
     */
    @Override
    public void outAOperationCallOrImplicitCollectOclExpressionCS(AOperationCallOrImplicitCollectOclExpressionCS node) {
        OperationCallOrImplicitCollectCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    /*
     * Productions that delegates in SpecialOperationOclExpressionCase
     */
    @Override
    public void outAAndOclExpressionCS(AAndOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outADivOclExpressionCS(ADivOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outALessOclExpressionCS(ALessOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outALessOrEqualOclExpressionCS(ALessOrEqualOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAMinusOclExpressionCS(AMinusOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAMultOclExpressionCS(AMultOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAPlusOclExpressionCS(APlusOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAUnaryMinusOclExpressionCS(AUnaryMinusOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outANotOclExpressionCS(ANotOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAMoreOclExpressionCS(AMoreOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAMoreOrEqualOclExpressionCS(AMoreOrEqualOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outANotEqualOclExpressionCS(ANotEqualOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAEqualOclExpressionCS(AEqualOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAOrOclExpressionCS(AOrOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAXorOclExpressionCS(AXorOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAImpliesOclExpressionCS(AImpliesOclExpressionCS node) {
        SpecialOperationOclExpressionCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in VariableCase
     */
    @Override
    public void outATypedAndInitVariableDeclarationCS(ATypedAndInitVariableDeclarationCS node) {
        VariableDeclarationCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAInitVariableDeclarationCS(AInitVariableDeclarationCS node) {
        VariableDeclarationCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outATypedVariableDeclarationCS(ATypedVariableDeclarationCS node) {
        VariableDeclarationCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outANoTypedNoInitVariableDeclarationCS(ANoTypedNoInitVariableDeclarationCS node) {
        VariableDeclarationCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    /*
     * Productions that delegates in CollectionTypeCase
     */
    @Override
    public void outACollectionTypeCS(ACollectionTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outABagTypeCS(ABagTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAOrderedSetTypeCS(AOrderedSetTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outASequenceTypeCS(ASequenceTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outASetTypeCS(ASetTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    
    /*
     * Productions that delegates in TupleTypeCase
     */    
    @Override
    public void outATupleTypeCS(ATupleTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
    @Override
    public void outAPathTypeCS(APathTypeCS node) {
        TypeCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }

    @Override
    public void outANameOclExpressionCS(ANameOclExpressionCS node) {
        NameExpCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }

    @Override
    public void outATypeOclExpressionCS(ATypeOclExpressionCS node) {
        TypeExpCase.getInstance().out(node, concreteNodeToAbstractNode, env);
    }
    
}
