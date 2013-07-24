/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface Visitable {
    
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments);
    
}
