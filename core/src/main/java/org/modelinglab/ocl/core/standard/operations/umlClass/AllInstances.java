/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.umlClass;

import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.SetType;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;

/**
 * Returns a set that contais all instance of some UML class
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class AllInstances extends Operation {

    private static final long serialVersionUID = 1L;
    private static AllInstances templateInstance = null;

    private AllInstances(@Nullable Operation templateOperation, ClassifierType classifier) {
        super(templateOperation);

        setName("allInstances");

        CollectionType resultType = new SetType(classifier.getReferredClassifier());
        setType(resultType);
        setSource(classifier);
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        if (sourceType instanceof ClassifierType) {
            return new AllInstances(createTemplateOperation(), (ClassifierType) sourceType);
        } else {
            throw new OclRuntimeException("allInstances() operation is only defined on Classifiers and "
                    + sourceType + " is not a ClassifierType.");
        }
    }

    public static AllInstances createTemplateOperation() {
        if (templateInstance == null) {
            templateInstance = new AllInstances(null, AnyType.getInstance().getClassifierType());
        }
        return templateInstance;
    }
}
