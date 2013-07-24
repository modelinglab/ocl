/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.modelinglab.ocl.utils;

import java.util.Collection;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.utils.OclExpressionDFW;

/**
 *
 * @param <C> Collection where found variables are going to be stored
 */
public class VariableFinder<C extends Collection<Variable>> extends OclExpressionDFW<C> {
    
    @Override
    protected void postVariableExp(VariableExp exp, C vars) {
        vars.add(exp.getReferredVariable());
    }

}
