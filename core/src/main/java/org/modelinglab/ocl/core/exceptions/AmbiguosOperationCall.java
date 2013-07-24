/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.exceptions;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class AmbiguosOperationCall extends OclException {
    private static final long serialVersionUID = 1L;
    final Classifier source;
    final String operationName;
    final List<Classifier> params;
    final List<Operation> conformedOperations;

    public AmbiguosOperationCall(Classifier source, String operationName, List<Classifier> params, List<Operation> conformedOperations) {
        super(generateMessage(source, operationName, params, conformedOperations));
        this.source = source;
        this.operationName = operationName;
        this.params = params;
        this.conformedOperations = conformedOperations;
    }
    
    private static String generateMessage(Classifier source, String operationName, List<Classifier> params, List<Operation> conformedOperations) {
        StringBuilder sb = new StringBuilder("There are several operations with name '");
        sb.append(operationName);
        sb.append("' in ").append(source.getName()).append(" that conforms with these arguments (");
        sb.append(params.toString()).append(") :");
        for (Operation op : conformedOperations) {
            sb.append('\n').append('\t').append(op.getSignature());
        }
        return sb.toString();
    }

    public String getOperationName() {
        return operationName;
    }

    public List<Classifier> getParams() {
        return params;
    }

    public Classifier getSource() {
        return source;
    }

    public List<Operation> getConformedOperations() {
        return conformedOperations;
    }
}
