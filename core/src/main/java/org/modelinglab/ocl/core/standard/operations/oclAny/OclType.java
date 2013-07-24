/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.core.standard.operations.oclAny;

import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.TemplateParameterType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.standard.operations.bag.AsBag;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclType extends Operation {
    private static final long serialVersionUID = 1L;

    public static OclType createTemplateOperation() {
        return new OclType(AnyType.getInstance());
    }

    private OclType(Classifier sourceType) {
        super(null);
        
        setName("oclType");
        setType(sourceType.getClassifierType());
        setSource(AnyType.getInstance());
    }

    @Override
    public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return new OclType(sourceType);
    }

    @Override
    public OclValue getStaticEvaluation(Classifier sourceType, OclValue sourceVals, List<OclValue> argsVals) throws OclEvaluationException {
        return null; //this operation returns the evaluation time type!
    }
 }
