/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.oclAny;

import java.util.HashSet;
import java.util.LinkedList;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.standard.operations.oclAny.AsSet;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.core.values.VoidValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclAnyAsSetEvaluator extends AnyOperatorEvaluatorTemplate {
    private static final long serialVersionUID = 1L;

    @Override
    protected OclValue<?> evaluate(OclValue<?> val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        HashSet<OclValue<?>> collection = new HashSet<>(1);
        collection.add(val);
        return new SetValue<>(collection, val.getType());
    }

    @Override
    public OclValue<?> visit(VoidValue val, SwitchArgument arg) {
        assert arg.arguments.isEmpty();
        return new SetValue<>(new LinkedList<OclValue<?>>(), arg.exp.getSource().getType());
    }
    
    @Override
    public Operation getEvaluableOperation() {
        return AsSet.createTemplateOperation();
    }
    
}
