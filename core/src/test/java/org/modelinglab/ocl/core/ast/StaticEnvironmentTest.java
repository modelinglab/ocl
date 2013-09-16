/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.operations.RealOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.InvalidOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.SequenceOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.IntegerOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.UmlClassOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.AnyOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.OrderedSetOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.BoolOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.VoidOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.StringOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.BagOperationsTestHelper;
import org.modelinglab.ocl.core.ast.operations.SetOperationsTestHelper;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class StaticEnvironmentTest {
    
    StaticEnvironment env;
    
    public StaticEnvironmentTest() {
        env = new StaticEnvironment(new OperationsStore.OperationsStoreFactory().createStore());
    }

    /**
     * Test of standard operations of 'any' classifier
     */
    @Test
    public void testLookupOperationStandard() throws Exception {
        AnyOperationsTestHelper.test(env, AnyType.getInstance());
        
        BagType bagType = new BagType();
        bagType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        BagOperationsTestHelper.test(env, bagType);
        
        SetType setType = new SetType();
        setType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        SetOperationsTestHelper.test(env, setType);
        
        SequenceType sequenceType = new SequenceType();
        sequenceType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        SequenceOperationsTestHelper.test(env, sequenceType);
        
        OrderedSetType orderedSetType = new OrderedSetType();
        orderedSetType.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        OrderedSetOperationsTestHelper.test(env, orderedSetType);
        
        BoolOperationsTestHelper.test(env, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        IntegerOperationsTestHelper.test(env, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        
        InvalidOperationsTestHelper.test(env, InvalidType.getInstance());
        
        VoidOperationsTestHelper.test(env, VoidType.getInstance());
        
        RealOperationsTestHelper.test(env, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        
        StringOperationsTestHelper.test(env, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        UmlClass umlClass = new UmlClass();
        umlClass.setName("Person");
        UmlClassOperationsTestHelper.test(env, umlClass);
    }
}
