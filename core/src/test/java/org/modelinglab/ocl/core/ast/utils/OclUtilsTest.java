/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.VoidType;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclUtilsTest {

    UmlClass child, father, grandfather, grandmother;

    public OclUtilsTest() {
        grandfather = new UmlClass();
        grandmother = new UmlClass();
        father = new UmlClass();
        child = new UmlClass();

        grandfather.setName("grandfather");
        grandmother.setName("grandmother");
        father.setName("father");
        child.setName("child");

        grandfather.addSubClass(father);
        father.addSuperClass(grandfather);

        grandmother.addSubClass(father);
        father.addSuperClass(grandmother);

        father.addSubClass(child);
        child.addSuperClass(father);
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
     * Test of getAllSuperclasses method, of class OclUtils.
     */
    @Test
    public void testGetAllSuperclasses() {
        Set<UmlClass> superClasses;

        superClasses = OclUtils.getAllSuperclasses(child);
        assert superClasses.size() == 3;
        assert superClasses.contains(father);
        assert superClasses.contains(grandfather);
        assert superClasses.contains(grandmother);

        superClasses = OclUtils.getAllSuperclasses(father);
        assert superClasses.size() == 2;
        assert superClasses.contains(grandfather);
        assert superClasses.contains(grandmother);

        assert OclUtils.getAllSuperclasses(grandfather).isEmpty();

        assert OclUtils.getAllSuperclasses(grandmother).isEmpty();
    }

    /**
     * Test of getLowestSharedType method, of class OclUtils.
     */
    @Test
    public void testGetLowestSharedType_Classifier_Classifier() {
        assert OclUtils.getLowestSharedType(VoidType.getInstance(), VoidType.getInstance()) == VoidType.getInstance();

        assert OclUtils.getLowestSharedType(VoidType.getInstance(), InvalidType.getInstance()) == VoidType.getInstance();

        assert OclUtils.getLowestSharedType(InvalidType.getInstance(), VoidType.getInstance()) == VoidType.getInstance();

        assert OclUtils.getLowestSharedType(
                VoidType.getInstance(),
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN)
            ) == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        assert OclUtils.getLowestSharedType(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN),
                VoidType.getInstance()
            ) == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);

        assert OclUtils.getLowestSharedType(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER),
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER)
            ) == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        
        assert OclUtils.getLowestSharedType(
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER),
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN)
            ) == AnyType.getInstance();
        
        CollectionType ct1;
        CollectionType ct2;
        CollectionType result;
        
        /*
         * Test that Collection(T) and Collection(T) share Collection(T) type
         */
        ct1 = new CollectionType();
        ct1.setElementType(TemplateParameterType.getGenericCollectionElement());
        
        ct2 = new CollectionType();
        ct2.setElementType(TemplateParameterType.getGenericCollectionElement());
        
        result = (CollectionType) OclUtils.getLowestSharedType(ct1, ct2);
        assert result.getElementType() == TemplateParameterType.getGenericCollectionElement();
        assert result.getCollectionKind() == CollectionType.CollectionKind.COLLECTION;
        
        /*
         * Test that Collection(T) and Collection(String) share Collection(String) type
         */
        ct1 = new CollectionType();
        ct1.setElementType(TemplateParameterType.getGenericCollectionElement());
        
        ct2 = new CollectionType();
        ct2.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        result = (CollectionType) OclUtils.getLowestSharedType(ct1, ct2);
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING);
        assert result.getCollectionKind() == CollectionType.CollectionKind.COLLECTION;
        
        /*
         * Test that Collection(T) and Set(String) share Collection(String) type
         */
        ct1 = new CollectionType();
        ct1.setElementType(TemplateParameterType.getGenericCollectionElement());
        
        ct2 = new SetType();
        ct2.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        result = (CollectionType) OclUtils.getLowestSharedType(ct1, ct2);
        assert result.getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING);
        assert result.getCollectionKind() == CollectionType.CollectionKind.COLLECTION;
        
    }

    /**
     * Test of getLowestSharedType method, of class OclUtils.
     */
    @Test
    public void testGetLowestSharedType_Collection() {
        Collection<Classifier> classifiers;
        Classifier result;
        CollectionType c1, c2, c3;
        
        /**
         * Set{} => VoidType
         */
        classifiers = Arrays.asList(new Classifier[] {
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result instanceof VoidType;
        
        /**
         * Set{true} => Boolean
         */
        classifiers = Arrays.asList(new Classifier[] {
            PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN)
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /**
         * Set{true, null} => Boolean
         */
        classifiers = Arrays.asList(new Classifier[] {
            PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN),
            VoidType.getInstance()
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN);
        
        /**
         * Set{true, 2} => AnyType
         */
        classifiers = Arrays.asList(new Classifier[] {
            PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN),
            PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER)
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result == AnyType.getInstance();
        
        /**
         * Set{Set{2}, 2} => AnyType
         */
        c1 = new SetType();
        c1.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        classifiers = Arrays.asList(new Classifier[] {
            c1,
            PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER)
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result == AnyType.getInstance();
        
        /**
         * Set{Set{2}, Sequence{true}} => Collection(AnyType)
         */
        c1 = new SetType();
        c1.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        c2 = new SequenceType();
        c2.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        classifiers = Arrays.asList(new Classifier[] {
            c1,
            c2
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result instanceof CollectionType;
        assert ((CollectionType) result).getElementType() == AnyType.getInstance();

        /**
         * Set{Set{2}, Sequence{5}} => Collection(Integer)
         */
        c1 = new SetType();
        c1.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        c2 = new SequenceType();
        c2.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        classifiers = Arrays.asList(new Classifier[] {
            c1,
            c2
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result instanceof CollectionType;
        assert ((CollectionType) result).getElementType() == PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER);
        
        /**
         * Set{Set{2}, Sequence{5}, Sequence{true}} => Collection(AnyType)
         */
        c1 = new SetType();
        c1.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        c2 = new SequenceType();
        c2.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        c3 = new SequenceType();
        c3.setElementType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        classifiers = Arrays.asList(new Classifier[] {
            c1,
            c2,
            c3
        });
        result = OclUtils.getLowestSharedType(classifiers);
        assert result instanceof CollectionType;
        assert ((CollectionType) result).getElementType() == AnyType.getInstance();
    }
}
