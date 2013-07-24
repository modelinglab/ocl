/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.utils.OclExpressionDFW;

/**
 * Removes all annotations of some class in all expresion nodes of a OCL expressio tree
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclExpAnnotationRemover<E> extends OclExpressionDFW<Class<E>> {

    @Override
    protected void preOclExpression(OclExpression exp, Class<E> arguments) {
        exp.removeAnnotation(arguments);
    }

 }
