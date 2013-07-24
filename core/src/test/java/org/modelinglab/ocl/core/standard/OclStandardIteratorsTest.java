/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard;

import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.BagType;
import org.modelinglab.ocl.core.ast.types.OrderedSetType;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.junit.*;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclStandardIteratorsTest {
    
    private final CollectionType collectionType;
    private final BagType bagType;
    private final OrderedSetType orderedSetType;
    private final SetType setType;
    private final SequenceType sequenceType;
    private final SetType bodyType;
    private final CollectionType sequenceBodyType, bagBodyType;
    private final CollectionType sequenceTBodyType, bagTBodyType;
    private final PrimitiveType booleanType;
    
    public OclStandardIteratorsTest() {
        booleanType = PrimitiveType.getInstance(PrimitiveKind.BOOLEAN);
        
        collectionType = new CollectionType();
        collectionType.setElementType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        
        bagType = new BagType();
        bagType.setElementType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        
        orderedSetType = new OrderedSetType();
        orderedSetType.setElementType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        
        setType = new SetType();
        setType.setElementType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        
        sequenceType = new SequenceType();
        sequenceType.setElementType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        
        bodyType = new SetType();
        bodyType.setElementType(booleanType);
        
        sequenceBodyType = new SequenceType();
        sequenceBodyType.setElementType(bodyType);
        
        bagBodyType = new BagType();
        bagBodyType.setElementType(bodyType);
        
        sequenceTBodyType = new SequenceType();
        sequenceTBodyType.setElementType(bodyType.getElementType());
        
        bagTBodyType = new BagType();
        bagTBodyType.setElementType(bodyType.getElementType());
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
    
    @Test
    public void testIsIterator() {
        OclStandardIterators instance = OclStandardIterators.getInstance();
        
        assert instance.isIterator(collectionType, "any");
        assert !instance.isIterator(collectionType, "Any");
    }

    /**
     * Test of getIteratorExp method, of class OclStandardIteratorStore.
     */
    @Test
    public void testGetIteratorExp() throws IllegalIteratorException {
        OclStandardIterators instance = OclStandardIterators.getInstance();
        
        assert instance.getIteratorExp(collectionType, "any", booleanType).getType().equals(collectionType.getElementType());
        assert instance.getIteratorExp(collectionType, "exists", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(collectionType, "forAll", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(collectionType, "isUnique", bodyType).getType().equals(booleanType);
        assert instance.getIteratorExp(collectionType, "one", booleanType).getType().equals(booleanType);
        
        assert instance.getIteratorExp(bagType, "any", booleanType).getType().equals(bagType.getElementType());
        assert instance.getIteratorExp(bagType, "exists", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(bagType, "forAll", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(bagType, "isUnique", bodyType).getType().equals(booleanType);
        assert instance.getIteratorExp(bagType, "one", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(bagType, "collect", bodyType).getType().equals(bagTBodyType);
        assert instance.getIteratorExp(bagType, "collectNested", bodyType).getType().equals(bagBodyType);
        assert instance.getIteratorExp(bagType, "reject", booleanType).getType().equals(bagType);
        assert instance.getIteratorExp(bagType, "select", booleanType).getType().equals(bagType);
        assert instance.getIteratorExp(bagType, "sortedBy", bodyType).getType().equals(sequenceType);
        
        assert instance.getIteratorExp(orderedSetType, "any", booleanType).getType().equals(orderedSetType.getElementType());
        assert instance.getIteratorExp(orderedSetType, "exists", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(orderedSetType, "forAll", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(orderedSetType, "isUnique", bodyType).getType().equals(booleanType);
        assert instance.getIteratorExp(orderedSetType, "one", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(orderedSetType, "collect", bodyType).getType().equals(sequenceTBodyType);
        assert instance.getIteratorExp(orderedSetType, "collectNested", bodyType).getType().equals(sequenceBodyType);
        assert instance.getIteratorExp(orderedSetType, "reject", booleanType).getType().equals(orderedSetType);
        assert instance.getIteratorExp(orderedSetType, "select", booleanType).getType().equals(orderedSetType);
        assert instance.getIteratorExp(orderedSetType, "sortedBy", bodyType).getType().equals(orderedSetType);
        
        assert instance.getIteratorExp(sequenceType, "any", booleanType).getType().equals(sequenceType.getElementType());
        assert instance.getIteratorExp(sequenceType, "exists", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(sequenceType, "forAll", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(sequenceType, "isUnique", bodyType).getType().equals(booleanType);
        assert instance.getIteratorExp(sequenceType, "one", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(sequenceType, "collect", bodyType).getType().equals(sequenceTBodyType);
        assert instance.getIteratorExp(sequenceType, "collectNested", bodyType).getType().equals(sequenceBodyType);
        assert instance.getIteratorExp(sequenceType, "reject", booleanType).getType().equals(sequenceType);
        assert instance.getIteratorExp(sequenceType, "select", booleanType).getType().equals(sequenceType);
        assert instance.getIteratorExp(sequenceType, "sortedBy", bodyType).getType().equals(sequenceType);
        
        assert instance.getIteratorExp(setType, "any", booleanType).getType().equals(setType.getElementType());
        assert instance.getIteratorExp(setType, "exists", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(setType, "forAll", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(setType, "isUnique", bodyType).getType().equals(booleanType);
        assert instance.getIteratorExp(setType, "one", booleanType).getType().equals(booleanType);
        assert instance.getIteratorExp(setType, "collect", bodyType).getType().equals(bagTBodyType);
        assert instance.getIteratorExp(setType, "collectNested", bodyType).getType().equals(bagBodyType);
        assert instance.getIteratorExp(setType, "reject", booleanType).getType().equals(setType);
        assert instance.getIteratorExp(setType, "select", booleanType).getType().equals(setType);
        assert instance.getIteratorExp(setType, "sortedBy", bodyType).getType().equals(orderedSetType);
    }
}
