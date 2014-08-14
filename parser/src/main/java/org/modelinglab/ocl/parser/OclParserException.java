/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser;

import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import org.modelinglab.ocl.parser.sablecc.parser.ParserException;

/**
 * TODO (@gortiz): toString
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclParserException extends OclRuntimeException {
    private static final long serialVersionUID = 1L;
    
    private final Node node;

    public OclParserException(Node node) {
        this.node = node;
    }

    public OclParserException(Node node, String message) {
        super(message);
        this.node = node;
    }

    public OclParserException(Node node, String message, Throwable cause) {
        super(message, cause);
        this.node = node;
    }

    public OclParserException(Node node, Throwable cause) {
        super(cause);
        this.node = node;
    }
    
    public OclParserException(ParserException ex) {
        super(ex.getMessage(), ex.getCause());
        this.node = ex.getToken();
    }

    public Node getNode() {
        return node;
    }
}
