/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.junit.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleTypeTest {
    
    public TupleTypeTest() {
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
     * Test of getRestrictedType method, of class TupleType.
     */
    @Test
    public void testGetRestrictedType() {
        final String UNRESTRICTED_SPECIFICATION = "T2";
        final String UNUSED_SPECIFICATION = "T3";
        
        TupleType instance = new TupleType();
        
        instance.addAttribute("att1", PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        instance.addAttribute("att2", TemplateParameterType.getGenericCollectionElement());
        
        instance.addAttribute("att3", new TemplateParameterType(UNRESTRICTED_SPECIFICATION));
        
        TemplateRestrictions restrictions = new TemplateRestrictions();
        restrictions.instantiate(UNUSED_SPECIFICATION, PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        restrictions.instantiate(
                TemplateParameterType.getGenericCollectionElement().getSpecification(), 
                PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING)
        );
        
        TupleType restricted = instance.getRestrictedType(restrictions);
        assert restricted.getAllAttributes().size() == 3;
        
        assert restricted.getAllAttributes().get("att1").equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        
        assert restricted.getAllAttributes().get("att2").equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        
        assert restricted.getAllAttributes().get("att3") instanceof TemplateParameterType;
        assert ((TemplateParameterType) restricted.getAllAttributes().get("att3")).getSpecification().equals(UNRESTRICTED_SPECIFICATION);
        
    }
}
