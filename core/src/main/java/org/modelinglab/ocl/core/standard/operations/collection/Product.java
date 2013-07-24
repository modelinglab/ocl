/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.collection;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.types.TupleType;
import java.util.List;
import javax.annotation.concurrent.Immutable;

/**
 * The cartesian product operation of self and c2.
 * <p><code>
 * product(c2: Collection(T2)) : Set( Tuple( first: T, second: T2) )<br/>
 * post: result = self->iterate (e1; acc: Set(Tuple(first: T, second: T2)) = Set{} |<br/>
 * c2->iterate (e2; acc2: Set(Tuple(first: T, second: T2)) = acc |<br/>
 * acc2->including (Tuple{first = e1, second = e2}) ) )
 * </code></p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Product extends Operation {
    private static final long serialVersionUID = 1L;

    public Product(Operation templateOperation, Classifier t, Classifier t2) {
        super(templateOperation);
        
        CollectionType ct = new CollectionType();
        ct.setElementType(t);
        
        CollectionType ct2 = new CollectionType();
        ct2.setElementType(t2);
        
        Parameter c2 = new Parameter();
        c2.setType(ct2);
        c2.setName("c2");
        addOwnedParameter(c2);
        
        setName("product");
        TupleType resultType = new TupleType();
        
        resultType.addAttribute("first", ct);
        
        resultType.addAttribute("second", ct2);
        
        setType(resultType);
        setSource(ct);
    }
    
    public static Product createTemplateOperation() {
        return new Product(
                null, 
                TemplateParameterType.getGenericCollectionElement(),
                new TemplateParameterType("T2"));
    }
    
    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        Preconditions.checkArgument(argTypes.size() == 1);
        Preconditions.checkArgument(argTypes.get(0) instanceof CollectionType);
        return new Product(this.getTemplateOperation(), ((CollectionType) sourceType).getElementType(), ((CollectionType) argTypes.get(0)).getElementType());
    }
    
}
