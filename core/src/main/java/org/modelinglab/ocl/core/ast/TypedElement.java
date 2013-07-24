/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.util.Collection;
import org.modelinglab.ocl.core.ast.types.Classifier;

/**
 * A typed element is a kind of named element that represents elements with types.
 * <p>
 * Elements with types are instances of TypedElement. A typed element may optionally have no type. 
 * The type of a typed element constrains the set of values that the typed element may refer to.
 * </p>
 * 
 *
 * @param <T> 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class TypedElement extends Element {

    public TypedElement() {
    }

    protected TypedElement(TypedElement other) {
        super(other);
    }
    
    /**
     * 
     * @return The type of the element.
     */
	public abstract Classifier getType();
    
    public abstract void setType(Classifier type);
    

}
