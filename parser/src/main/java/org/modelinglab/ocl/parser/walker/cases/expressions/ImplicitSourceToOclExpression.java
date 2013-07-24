/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.expressions.VariableExp;
import org.modelinglab.ocl.core.ast.utils.OclVisitorAdapter;
import org.modelinglab.ocl.core.ast.utils.Visitable;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.Node;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
class ImplicitSourceToOclExpression extends OclVisitorAdapter<OclExpression, Node> {
    public static ImplicitSourceToOclExpression instance = new ImplicitSourceToOclExpression();

    private ImplicitSourceToOclExpression() {
    }

    @Override
    public OclExpression defaultCase(Visitable obj, Node arguments) {
        throw new OclParserException(arguments, "An implicit variable was expected");
    }

    @Override
    public OclExpression visit(Variable var, Node argument) {
        VariableExp varExp = new VariableExp();
        varExp.setReferredVariable(var);
        return varExp;
    }
    
}
