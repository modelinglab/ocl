/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.umlClass;

import java.util.ArrayList;
import java.util.List;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.standard.operations.umlClass.AllInstances;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.EnumValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class AllInstancesEvaluator extends OperationEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public Operation getEvaluableOperation() {
        return AllInstances.createTemplateOperation();
    }

    @Override
    public OclValue<?> visit(ClassifierValue val, SwitchArgument arg) {
        assert val.getValue() instanceof UmlClass;
        
        if (val.getValue() instanceof UmlEnum) {
            UmlEnum enumType = (UmlEnum) val.getValue();
            
            List<UmlEnumLiteral> literals = enumType.getLiterals();
            ArrayList<EnumValue> allValueLiterals = new ArrayList<>(literals.size());
            for (final UmlEnumLiteral literal : literals) {
                allValueLiterals.add(new EnumValue(literal));
            }
            
            return new SetValue<>(allValueLiterals, enumType, true);
        }
        else {
            UmlClass clazz = (UmlClass) val.getValue();
            assert arg.runtimeEnv.getUserEvalEnv().getClasses().get(clazz.getPath()).equals(clazz);
            return arg.runtimeEnv.getUserEvalEnv().getInstancesProvider().getAllInstances(clazz);
        }
    }
}
