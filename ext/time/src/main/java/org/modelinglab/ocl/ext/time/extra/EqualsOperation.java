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
public class EqualsOperation extends Operation {

    public EqualsOperation(UmlClass clazz) {
        super(null);
        
        setName("=");
        
        addOwnedParameter(new Parameter("other", clazz, null));
        setType(PrimitiveType.BOOLEAN);
        setSource(clazz);
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType,
                                       List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
}
