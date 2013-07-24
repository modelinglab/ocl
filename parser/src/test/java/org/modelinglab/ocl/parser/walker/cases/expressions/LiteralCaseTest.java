/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.*;
import org.modelinglab.ocl.parser.walker.cases.CaseTestAbstract;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LiteralCaseTest extends CaseTestAbstract {

    
    @Test
    public void testOut_ATrueLiteralOclExpressionCS() {
        ATrueLiteralOclExpressionCS node = new ATrueLiteralOclExpressionCS();
        
        expectedMap.put(node, new BooleanLiteralExp(Boolean.TRUE));
        
        LiteralCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }
    
    @Test
    public void testOut_AFalseLiteralOclExpressionCS() {
        AFalseLiteralOclExpressionCS node = new AFalseLiteralOclExpressionCS();
        
        expectedMap.put(node, new BooleanLiteralExp(Boolean.FALSE));
        
        LiteralCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }
    
    @Test
    public void testOut_CollectionLiterals() {

        ArrayList<PCollectionLiteralPartCS> parts = new ArrayList<>(5);

        PCollectionLiteralPartCS part = new ARangeCollectionLiteralPartCS();
        parts.add(part);
        
        CollectionRange range = new CollectionRange();
        range.setFirst(new IntegerLiteralExp(2l));
        range.setLast(new IntegerLiteralExp(10l));

        CollectionLiteralExp result = new CollectionLiteralExp();
        result.setParts(Arrays.asList(new CollectionLiteralPart[] {range.clone()}));
        
        {
            clear();
            
            ABagLiteralOclExpressionCS node = new ABagLiteralOclExpressionCS();
            node.setElements(parts);

            resultMap.put(part, range.clone());
            CollectionType colType = CollectionType.newCollection(CollectionType.CollectionKind.BAG);
            colType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
            result.setType(colType);
            expectedMap.put(node, result);
            
            LiteralCase.getInstance().out(node, resultMap, env);
            
            checkMaps();
        }

        {
            clear();
            
            AOrderedSetLiteralOclExpressionCS node = new AOrderedSetLiteralOclExpressionCS();
            node.setElements(parts);

            resultMap.put(part, range.clone());
            CollectionType colType = CollectionType.newCollection(CollectionType.CollectionKind.ORDERED_SET);
            colType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
            result.setType(colType);
            expectedMap.put(node, result);
            
            LiteralCase.getInstance().out(node, resultMap, env);
            
            checkMaps();
        }

        {
            clear();
            
            ASequenceLiteralOclExpressionCS node = new ASequenceLiteralOclExpressionCS();
            node.setElements(parts);

            resultMap.put(part, range.clone());
            CollectionType colType = CollectionType.newCollection(CollectionType.CollectionKind.SEQUENCE);
            colType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
            result.setType(colType);
            expectedMap.put(node, result);
            
            LiteralCase.getInstance().out(node, resultMap, env);
            
            checkMaps();
        }
        
        {
            clear();
            
            ASetLiteralOclExpressionCS node = new ASetLiteralOclExpressionCS();
            node.setElements(parts);

            resultMap.put(part, range.clone());
            CollectionType colType = CollectionType.newCollection(CollectionType.CollectionKind.SET);
            colType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
            result.setType(colType);
            expectedMap.put(node, result);
            
            LiteralCase.getInstance().out(node, resultMap, env);
            
            checkMaps();
        }
    }
    
    @Test
    public void testOut_IntegerNotNumber() {

        AIntegerLiteralOclExpressionCS node = new AIntegerLiteralOclExpressionCS();

        node.setInteger(new TInteger("thisIsNotANumber"));

        try {
            LiteralCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when the input is not a integer!";
        } catch (OclParserException ex) {
            clear();
        }
    }

    @Test
    public void testOut_IntegerNumber() {
        AIntegerLiteralOclExpressionCS node = new AIntegerLiteralOclExpressionCS();
        
        node.setInteger(new TInteger("1234"));

        IntegerLiteralExp expectedResult = new IntegerLiteralExp(1234l);

        expectedMap.put(node, expectedResult);

        LiteralCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }
    
    @Test
    public void testOut_RealNotNumber() {

        ARealLiteralOclExpressionCS node = new ARealLiteralOclExpressionCS();

        node.setReal(new TReal("thisIsNotANumber"));

        try {
            LiteralCase.getInstance().out(node, resultMap, env);
            assert false : "An exception should be thrown when the input is not a Real!";
        } catch (OclParserException ex) {
            clear();
        }
    }

    @Test
    public void testOut_RealNumber() {
        ARealLiteralOclExpressionCS node = new ARealLiteralOclExpressionCS();
        
        node.setReal(new TReal("1234"));

        RealLiteralExp expectedResult = new RealLiteralExp(1234d);

        expectedMap.put(node, expectedResult);

        LiteralCase.getInstance().out(node, resultMap, env);

        checkMaps();
    }
    
    @Test
    public void testOut_String1() {
        String text = "this is a string with double quote.";
        
        AString1LiteralOclExpressionCS node = new AString1LiteralOclExpressionCS();
        node.setString(new TString('"' + text + '"'));
        
        expectedMap.put(node, new StringLiteralExp(text));
        
        LiteralCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }

    @Test
    public void testOut_String2() {
        String text = "this is a string with double quote.";
        
        AString2LiteralOclExpressionCS node = new AString2LiteralOclExpressionCS();
        node.setString2(new TString2('\'' + text + '\''));
        
        expectedMap.put(node, new StringLiteralExp(text));
        
        LiteralCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
    
    @Test
    public void testOut_null() {
        ANullLiteralOclExpressionCS node = new ANullLiteralOclExpressionCS();
        
        expectedMap.put(node, new NullLiteralExp());
        
        LiteralCase.getInstance().out(node, resultMap, env);
        
        checkMaps();
    }
    
    @Test
    public void testOut() {
        AInvalidLiteralOclExpressionCS node = new AInvalidLiteralOclExpressionCS();
        
        expectedMap.put(node, new InvalidLiteralExp());
        
        LiteralCase.getInstance().out(node, resultMap, env);
        checkMaps();
    }
}
