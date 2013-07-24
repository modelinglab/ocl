/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.values.IntegerValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.core.values.VoidValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit;
import org.modelinglab.ocl.ext.complextypes.classes.AGTimeUnit.AGTimeUnitEnum;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateAdd;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class DateAddEvaluator extends AbstractDateEvaluator {
    private static final long serialVersionUID = 1L;

    @Override
    public OclValue<?> visitDate(AGDate.AGDateObject val, SwitchArgument arg) {
        assert arg.arguments.size() == 2;
        
        OclValue<?> toAdd = arg.arguments.get(0);
        if (toAdd.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        
        OclValue<?> tu = arg.arguments.get(1);
        if (tu.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        
        assert toAdd instanceof IntegerValue;
        Long valueToAdd = (Long) toAdd.getValue();
        
        assert tu.getValue() instanceof UmlEnumLiteral;
        assert tu.getType() instanceof AGTimeUnit;
        UmlEnumLiteral oclLiteral = (UmlEnumLiteral) tu.getValue();
        AGTimeUnitEnum javaLiteral = AGTimeUnitEnum.fromOclLiteral(oclLiteral);
        
        
        DateTime resultDateTime = javaLiteral.getJodaProperty(val.getJodaDateTime()).addToCopy(valueToAdd);
        
        return new ObjectValue<>(new AGDate.AGDateObject(resultDateTime));
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateAdd.getInstance();
    }
    
}
