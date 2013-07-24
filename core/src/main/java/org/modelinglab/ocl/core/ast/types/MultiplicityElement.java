/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.Visitable;
import java.io.Serializable;

/**
 * A {@link MultiplicityElement} is an abstract metaclass which includes optional attributes for 
 * defining the bounds of a multiplicity. A {@link MultiplicityElement} also includes specifications
 * of whether the values in an instantiation of this element must be unique or ordered.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface MultiplicityElement extends Serializable, Cloneable, Visitable {
    public static final int UNBOUND = -1;
    
    /**
     * 
     * @return Specifies the lower bound of the multiplicity interval. Default is one.
     */
    public int getLowerBound();
    
    public void setLowerBound(int lowerBound);
    
    /**
     * In UML Specification, this attribute is an UnlimitedNatural
     * 
     * @return Specifies the upper bound of the multiplicity interval. Default is one.
     */
    public int getUpperBound();
    
    public void setUpperBound(int upperBound);
    
    /**
     * @return For a multivalued multiplicity, this attribute specifies whether the values in an 
     * instantiation of this element are sequentially ordered. Default is false.
     */
    public boolean isOrdered();
    
    public void setOrdered(boolean ordered);
    
    /**
     * 
     * @return For a multivalued multiplicity, this attributes specifies whether the values in an 
     * instantiation of this element are unique. Default is true.
     */
    public boolean isUnique();
    
    public void setUnique(boolean unique);
    
    /**
     * The query isMultivalued() checks whether this multiplicity has an upper bound greater than one.
     * 
     * @pre upperBound()->notEmpty()
     * 
     * @return isMultivalued = (upperBound() > 1)
     */
    public boolean isMultivalued();
    
    /**
     * The query includesCardinality() checks whether the specified cardinality is valid for this multiplicity.
     * @pre upperBound()->notEmpty() and lowerBound()->notEmpty()
     * @return includesCardinality = (lowerBound() <= C) and (upperBound() >= C)
     */
    public boolean includesCardinality(int c);
    
    /**
     * The query includesMultiplicity() checks whether this multiplicity includes all the 
     * cardinalities allowed by the specified multiplicity.
     * 
     * @param m 
     * @pre self.upperBound()->notEmpty() and self.lowerBound()->notEmpty() and 
     * M.upperBound()->notEmpty() and M.lowerBound()->notEmpty()
     * 
     * @return includesMultiplicity = (self.lowerBound() <= M.lowerBound()) and (self.upperBound() >= M.upperBound())
     */
    public boolean includesMultiplicity(MultiplicityElement m);
    
}
