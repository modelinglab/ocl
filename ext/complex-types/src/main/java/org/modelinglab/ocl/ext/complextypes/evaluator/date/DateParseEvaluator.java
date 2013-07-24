/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.evaluator.date;

import org.joda.time.DateTime;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.values.ClassifierValue;
import org.modelinglab.ocl.core.values.InvalidValue;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.StringValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.classes.AGDate;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateNow;
import org.modelinglab.ocl.ext.complextypes.operations.date.DateParse;

/**
 *
 */
public class DateParseEvaluator extends OperationEvaluator {

    @Override
    public OclValue<?> visit(ClassifierValue val, SwitchArgument arg) {
        if (!(val.getType() instanceof AGDate)) {
            throw new IllegalArgumentException("This evaluator expects that source was Date.");
        }

        OclValue<?> arg1 = arg.arguments.get(0);
        if (arg1.getType().oclIsUndefined()) {
            return InvalidValue.instantiate();
        }
        assert arg1 instanceof StringValue;
        StringValue toParse = (StringValue) arg1;

        return new ObjectValue<>(new AGDate.AGDateObject(DateTime.parse(toParse.getValue())));
    }

    @Override
    public Operation getEvaluableOperation() {
        return DateParse.getInstance();
    }
}
