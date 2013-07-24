/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import java.util.List;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclInvalidOperationException extends OclParserException {
    private static final long serialVersionUID = 1L;
    Operation op;

    public OclInvalidOperationException(String opName, OperationCallExp opCall, List<Classifier> argTyps, Node node, Throwable cause) {
        super(node, getMessage(opName, opCall, argTyps), cause);
    }

//    public OclInvalidOperationException(OperationCallExp opCall, List<Classifier> argTyps, Node node, String message, Throwable cause) {
//        super(node, message, cause);
//    }
//
//    public OclInvalidOperationException(OperationCallExp opCall, List<Classifier> argTyps, Node node, String message) {
//        super(node, message);
//    }

    public OclInvalidOperationException(String opName, OperationCallExp opCall, List<Classifier> argTyps, Node node) {
        super(node, getMessage(opName, opCall, argTyps));
    }
    
    private static String getMessage(String opName, OperationCallExp opCall, List<Classifier> argTyps) {
        String str = "There is no operation with this signature: ";
        StringBuilder sb = new StringBuilder(str.length() * 2);
        sb.append(str);
        
        try {
            OclExpression source = opCall.getSource();
            Classifier c = source.getType();
            if (c == null) {
                sb.append("<null>");
            }
            else {
                sb.append(c);
            }
        } catch (RuntimeException e) {
            sb.append("<null>");
        }
        sb.append("::");
        if (opName == null) {
            sb.append("<null>");
        }
        else {
            sb.append(opName);
        }
        sb.append('(');
        for (final Classifier classifier : argTyps) {
            sb.append(classifier).append(", ");
        }
        sb.append(')');
        return sb.toString();
    }
}
