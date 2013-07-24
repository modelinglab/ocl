/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases;

import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.parser.walker.cases.TypeCase;
import org.modelinglab.ocl.core.ast.expressions.InvalidLiteralExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TypeCaseTest extends CaseTestAbstract {

    @Test
    public void testOut_APathTypeCS_correct() {
        APathTypeCS node = new APathTypeCS(new APathNameNameCS());
        resultMap.put(node.getNameCS(), Arrays.asList(new String[]{"Person"}));

        Classifier result = (Classifier) env.lookup("Person");
        expectedMap.put(node, result);

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }

    @Test
    public void testOut_APathTypeCS_incorrect() {
        APathTypeCS node = new APathTypeCS(new APathNameNameCS());
        resultMap.put(node.getNameCS(), Arrays.asList(new String[]{"invalid", "path"}));

        try {
            TypeCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown if pathname does not correspond to a Classifier";
        } catch (OclParserException ex) {
        }

        resultMap.put(node.getNameCS(), Arrays.asList(new String[]{"self"}));

        try {
            TypeCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown if pathname does not correspond to a Classifier";
        } catch (OclParserException ex) {
        }

        checkMaps();
    }

    @Test
    public void testOut_ACollectionCollectionTypeCS() {

        PTypeCS elementTypeCs = new APathTypeCS();
        Classifier elementType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        resultMap.put(elementTypeCs, elementType);

        ACollectionTypeCS node = new ACollectionTypeCS(elementTypeCs);

        expectedMap.put(node, new CollectionType(elementType));

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }

    @Test
    public void testOut_ABagCollectionTypeCS() {

        PTypeCS elementTypeCs = new APathTypeCS();
        Classifier elementType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        resultMap.put(elementTypeCs, elementType);

        ABagTypeCS node = new ABagTypeCS(elementTypeCs);

        expectedMap.put(node, new BagType(elementType));

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }

    @Test
    public void testOut_AOrderedSetCollectionTypeCS() {

        PTypeCS elementTypeCs = new APathTypeCS();
        Classifier elementType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        resultMap.put(elementTypeCs, elementType);

        AOrderedSetTypeCS node = new AOrderedSetTypeCS(elementTypeCs);

        expectedMap.put(node, new OrderedSetType(elementType));

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }

    @Test
    public void testOut_ASequenceCollectionTypeCS() {

        PTypeCS elementTypeCs = new APathTypeCS();
        Classifier elementType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        resultMap.put(elementTypeCs, elementType);

        ASequenceTypeCS node = new ASequenceTypeCS(elementTypeCs);

        expectedMap.put(node, new SequenceType(elementType));

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }

    @Test
    public void testOut_ASetCollectionTypeCS() {

        PTypeCS elementTypeCs = new APathTypeCS();
        Classifier elementType = PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        resultMap.put(elementTypeCs, elementType);

        ASetTypeCS node = new ASetTypeCS(elementTypeCs);

        expectedMap.put(node, new SetType(elementType));

        TypeCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }
    
    
    @Test
    public void testOut_empty() {
        try {
            List<Variable> vars = Collections.emptyList();
            testTuple(vars);
            assert false : "Tuple types without attributes has no sense!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_attWithoutName() {
        try {
            List<Variable> vars = new ArrayList<Variable>(1);
            vars.add(new Variable());
            testTuple(vars);
            assert false : "An exception should be thrown if there is a variable without name!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_attWithInit() {
        try {
            List<Variable> vars = new ArrayList<Variable>(1);

            Variable var = new Variable();
            var.setName("var1");
            var.setInitExpression(new InvalidLiteralExp());

            vars.add(var);
            testTuple(vars);
            assert false : "An exception should be thrown if there is a variable with init expression!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_attWithoutType() {
        try {
            List<Variable> vars = new ArrayList<Variable>(1);

            Variable var = new Variable();
            var.setName("var1");

            vars.add(var);
            testTuple(vars);
            assert false : "An exception should be thrown if there is a variable without type!";
        } catch (OclParserException ex) {
        }
    }

    @Test
    public void testOut_correct() {
        List<Variable> vars = new ArrayList<Variable>(1);

        Variable var = new Variable();
        var.setName("var1");
        var.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));

        vars.add(var);
        testTuple(vars);
    }

    private void testTuple(List<Variable> vars) {
        List<PVariableDeclarationCS> varDecs = new ArrayList<PVariableDeclarationCS>(vars.size());
        for (Variable var : vars) {
            PVariableDeclarationCS varDec = new ANoTypedNoInitVariableDeclarationCS(new ASimpleSimpleNameCS(new TIdentifier(var.getName())));
            varDecs.add(varDec);

            resultMap.put(varDec, var);
        }

        ATupleTypeCS node = new ATupleTypeCS(varDecs);
        TypeCase.getInstance().out(node, resultMap, env);

        TupleType expectedResult = new TupleType();
        for (Variable var : vars) {
            expectedResult.addAttribute(var.getName(), var.getType());
        }
        expectedMap.put(node, expectedResult);

        checkMaps();
    }
}
