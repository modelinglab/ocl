/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time.extra;

import java.util.List;
import java.util.Objects;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 *
 */
public class ComparableOperation extends Operation {

    UmlClass clazz;

    public ComparableOperation(UmlClass clazz, String name) {
        super(null);
        this.clazz = clazz;
        
        setName(name);
        
        addOwnedParameter(new Parameter("other", clazz, null));
        setType(PrimitiveType.BOOLEAN);
        setSource(clazz);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType,
                                       List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.clazz);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ComparableOperation other = (ComparableOperation) obj;
        if (!Objects.equals(this.clazz, other.clazz)) {
            return false;
        }
        return super.equals(obj);
    }
}
