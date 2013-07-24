/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.types.CollectionType.CollectionKind;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CollectionTypeTest {
    
    public CollectionTypeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGenericInstance method, of class CollectionType.
     */
    @Test
    @Ignore
    public void testGetGenericInstance() {
        System.out.println("getGenericInstance");
        CollectionType expResult = null;
        CollectionType result = CollectionType.getGenericInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fixTemplates method, of class CollectionType.
     */
    @Test
    @Ignore
    public void testFixTemplates() {
        System.out.println("fixTemplates");
        TemplateRestrictions restrictions = null;
        CollectionType instance = new CollectionType();
        instance.fixTemplates(restrictions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getRestrictedType method, of class CollectionType.
     */
    @Test
    public void testGetRestrictedType() {
        CollectionType instance;
        CollectionType result;
        
        TemplateRestrictions restrictions = new TemplateRestrictions();
        restrictions.instantiate(TemplateParameterType.getGenericCollectionElement().getSpecification(), AnyType.getInstance());
        
        /*
         * Collection(Boolean).getRestrictedType(restriction) = Collection(Boolean)
         */
        instance = new CollectionType();
        instance.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.COLLECTION;
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /*
         * Bag(Boolean).getRestrictedType(restriction) = Bag(Boolean)
         */
        instance = new BagType();
        instance.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.BAG;
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /*
         * Set(Boolean).getRestrictedType(restriction) = Set(Boolean)
         */
        instance = new SetType();
        instance.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.SET;
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /*
         * OrderedSet(Boolean).getRestrictedType(restriction) = OrderedSet(Boolean)
         */
        instance = new OrderedSetType();
        instance.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.ORDERED_SET;
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /*
         * Sequence(Boolean).getRestrictedType(restriction) = Sequence(Boolean)
         */
        instance = new SequenceType();
        instance.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.SEQUENCE;
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /*
         * Collection(T).getRestrictedType(restriction) = Collection(AnyType)
         */
        instance = new CollectionType();
        instance.setElementType(TemplateParameterType.getGenericCollectionElement());
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.COLLECTION;
        assert result.getElementType() == AnyType.getInstance();
        
        /*
         * Bag(T).getRestrictedType(restriction) = Bag(AnyType)
         */
        instance = new BagType();
        instance.setElementType(TemplateParameterType.getGenericCollectionElement());
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.BAG;
        assert result.getElementType() == AnyType.getInstance();
        
        /*
         * Set(T).getRestrictedType(restriction) = Set(AnyType)
         */
        instance = new SetType();
        instance.setElementType(TemplateParameterType.getGenericCollectionElement());
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.SET;
        assert result.getElementType() == AnyType.getInstance();
        
        /*
         * OrderedSet(T).getRestrictedType(restriction) = OrderedSet(AnyType)
         */
        instance = new OrderedSetType();
        instance.setElementType(TemplateParameterType.getGenericCollectionElement());
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.ORDERED_SET;
        assert result.getElementType() == AnyType.getInstance();
        
        /*
         * Sequence(T).getRestrictedType(restriction) = Sequence(AnyType)
         */
        instance = new SequenceType();
        instance.setElementType(TemplateParameterType.getGenericCollectionElement());
        result = instance.getRestrictedType(restrictions);
        
        assert result.getCollectionKind() == CollectionKind.SEQUENCE;
        assert result.getElementType() == AnyType.getInstance();
    }

}
