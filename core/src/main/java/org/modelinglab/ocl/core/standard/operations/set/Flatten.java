/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.set;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitorAdaptor;

/**
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Flatten extends Operation {

    private static final long serialVersionUID = 1L;

    public Flatten(Operation templateOperation, Classifier tType, Classifier t2Type) {
        super(templateOperation);

        SetType st = new SetType();
        st.setElementType(tType);

        SetType resultType = new SetType();
        resultType.setElementType(t2Type);

        setName("flatten");
        setType(resultType);
        setSource(st);
    }

    public static Flatten createTemplateOperation() {
        return new Flatten(
                null,
                TemplateParameterType.getGenericCollectionElement(),
                new TemplateParameterType("T2"));
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        Classifier t = ((SetType) sourceType).getElementType();
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
