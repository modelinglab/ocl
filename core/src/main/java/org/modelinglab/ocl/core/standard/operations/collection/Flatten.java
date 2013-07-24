/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitorAdaptor;

/**
 * If the element type is not a collection type, this results in the same collection as self. 
 * If the element type is a collection type, the result is a collection containing all the elements 
 * of all the recursively flattened elements of self. 
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Flatten extends Operation {
    private static final long serialVersionUID = 1L;

    public Flatten(
            Operation templateOperation, 
            Classifier elementType, 
            Classifier resultElementType) {
        super(templateOperation);
        
        CollectionType ct = new CollectionType();
        ct.setElementType(elementType);
        
        CollectionType resultType = new CollectionType();
        resultType.setElementType(resultElementType);
        
        setName("flatten");
        setType(resultType);
        setSource(ct);
    }
    
    public static Flatten createTemplateOperation() {
        return new Flatten(
                null, 
                TemplateParameterType.getGenericCollectionElement(),
                new TemplateParameterType("T2"));
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        Classifier t = ((CollectionType) sourceType).getElementType();
        Classifier t2 = t.accept(new ClassifierVisitorAdaptor<Classifier, Void>() {

            @Override
            public Classifier visit(Classifier type, Void arguments) {
                return type;
            }

            @Override
            public Classifier visit(CollectionType type, Void arguments) {
                return type.getElementType().accept(this, arguments);
            }
            
        }, null);
        
        return new Flatten(this.getTemplateOperation(), t, t2);
    }
    
}
