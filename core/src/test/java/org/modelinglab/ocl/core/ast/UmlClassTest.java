/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UmlClassTest {
    
    public UmlClassTest() {
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
     * Test of getGenericInstance method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testGetGenericInstance() {
        System.out.println("getGenericInstance");
        UmlClass expResult = null;
        UmlClass result = UmlClass.getGenericInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHierarchyLevels method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testGetHierarchyLevels() {
        System.out.println("getHierarchyLevels");
        UmlClass instance = new UmlClass();
        Map expResult = null;
        Map result = instance.getHierarchyLevels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testOwnedAssociationEnds() {
        UmlClass instance = new UmlClass();
        
        AssociationEnd asso1 = new AssociationEnd();
        AssociationEnd asso2 = new AssociationEnd();
        
        asso1.setName("asso1");
        asso2.setName("asso2");
        asso1.setOpposited(asso2);
        
        assert !instance.getOwnedAssociationEnds().contains(asso1);
        assert asso1.getUmlClass() == null;
        
        instance.addOwnedAssociationEnd(asso1);
        assert instance.getOwnedAssociationEnds().contains(asso1);
        assert asso1.getUmlClass() == instance;
        
        instance.removeOwnedAssociationEnd(asso1);
        assert !instance.getOwnedAssociationEnds().contains(asso1);
        assert asso1.getUmlClass() == null;
        
        instance.addOwnedAssociationEnd(asso1);
        assert instance.getOwnedAssociationEnds().contains(asso1);
        assert asso1.getUmlClass() == instance;
        instance.setOwnedAssociationEnds(Arrays.asList(new AssociationEnd[]{asso2}));
        assert !instance.getOwnedAssociationEnds().contains(asso1);
        assert asso1.getUmlClass() != instance;
        assert instance.getOwnedAssociationEnds().contains(asso2);
        assert asso2.getUmlClass() == instance;
    }

    @Test
    public void testOwnedAttributes() {
        UmlClass instance = new UmlClass();
        
        Attribute attr1 = new Attribute();
        Attribute attr2 = new Attribute();
        
        attr1.setName("attr1");
        attr1.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        attr2.setName("attr2");
        attr2.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        assert !instance.getOwnedAttributes().contains(attr1);
        assert attr1.getUmlClass() == null;
        
        instance.addOwnedAttribute(attr1);
        assert instance.getOwnedAttributes().contains(attr1);
        assert attr1.getUmlClass() == instance;
        
        instance.removeOwnedAttribute(attr1);
        assert !instance.getOwnedAttributes().contains(attr1);
        assert attr1.getUmlClass() == null;
        
        instance.addOwnedAttribute(attr1);
        assert instance.getOwnedAttributes().contains(attr1);
        assert attr1.getUmlClass() == instance;
        instance.setOwnedAttributes(Arrays.asList(new Attribute[]{attr2}));
        assert !instance.getOwnedAttributes().contains(attr1);
        assert attr1.getUmlClass() != instance;
        assert instance.getOwnedAttributes().contains(attr2);
        assert attr2.getUmlClass() == instance;
    }
    
    @Test
    public void testSubClasses() {
        UmlClass instance = new UmlClass();
        instance.setName("Instance");
        UmlClass a = new UmlClass();
        a.setName("A");
        UmlClass b = new UmlClass();
        b.setName("B");
        
        assert !instance.getSubClasses().contains(a);
        assert !a.getSuperClasses().contains(instance);
        
        instance.addSubClass(a);
        assert instance.getSubClasses().contains(a);
        assert a.getSuperClasses().contains(instance);
        
        instance.setSubClasses(Arrays.asList(new UmlClass[] {b}));
        assert !instance.getSubClasses().contains(a);
        assert !a.getSuperClasses().contains(instance);
        assert instance.getSubClasses().contains(b);
        assert b.getSuperClasses().contains(instance);
        
        instance.removeSubClass(b);
        assert !instance.getSubClasses().contains(b);
        assert !b.getSuperClasses().contains(instance);
    }
    
    @Test
    public void testSuperClasses() {
        UmlClass instance = new UmlClass();
        instance.setName("Instance");
        UmlClass a = new UmlClass();
        a.setName("A");
        UmlClass b = new UmlClass();
        b.setName("B");
        
        assert !instance.getSuperClasses().contains(a);
        assert !a.getSubClasses().contains(instance);
        
        instance.addSuperClass(a);
        assert instance.getSuperClasses().contains(a);
        assert a.getSubClasses().contains(instance);
        
        instance.setSuperClasses(Arrays.asList(new UmlClass[] {b}));
        assert !instance.getSuperClasses().contains(a);
        assert !a.getSubClasses().contains(instance);
        assert instance.getSuperClasses().contains(b);
        assert b.getSubClasses().contains(instance);
        
        instance.removeSuperClass(b);
        assert !instance.getSuperClasses().contains(b);
        assert !b.getSubClasses().contains(instance);
    }


    /**
     * Test of getSuperClassifiers method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testGetSuperClassifiers() {
        System.out.println("getSuperClassifiers");
        UmlClass instance = new UmlClass();
        List expResult = null;
        List result = instance.getSuperClassifiers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of conformsToProtected method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testConformsToProtected() {
        System.out.println("conformsToProtected");
        Classifier conformsTarget = null;
        TemplateRestrictions templates = null;
        UmlClass instance = new UmlClass();
        boolean expResult = false;
        boolean result = instance.conformsToProtected(conformsTarget, templates);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        UmlClass instance = new UmlClass();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class UmlClass.
     */
    @Test
    @Ignore
    public void testHashCode() {
        System.out.println("hashCode");
        UmlClass instance = new UmlClass();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
