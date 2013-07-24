/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.operations.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.modelinglab.ocl.core.values.BagValue;
import org.modelinglab.ocl.core.values.OclValue;
import org.modelinglab.ocl.core.values.SetValue;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class BagToSet {

    private BagToSet() {
    }
    
    public static <E extends OclValue<?>> SetValue<E> bagToSet(BagValue<E> val, OperationEvaluator.SwitchArgument arg) {
        //gortiz: A multithread implementation could improve throughput when val.getValue().size() is big
        Multiset<E> multiset = HashMultiset.create(val.getValue());
        return new SetValue<>(multiset.elementSet(), val.getType().getElementType());
    }
    
}
