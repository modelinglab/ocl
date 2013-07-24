/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser;

import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.parser.sablecc.lexer.LexerException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclLexerException extends OclRuntimeException {
    private static final long serialVersionUID = 1L;
    
    public OclLexerException(LexerException ex) {
        super(ex.getMessage(), ex.getCause());
    }
}
