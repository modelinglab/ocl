/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.complextypes.classes;

import java.util.HashSet;
import java.util.Set;
import org.modelinglab.ocl.core.ast.OperationsStore;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import org.modelinglab.ocl.ext.complextypes.evaluator.string.AllStringEvaluators;
import org.modelinglab.ocl.ext.complextypes.evaluator.umlClass.AllUmlClassEvaluators;
import org.modelinglab.ocl.ext.complextypes.evaluator.umlEnum.AllUmlEnumEvaluators;
import org.modelinglab.ocl.ext.complextypes.operations.string.AllStringOperations;
import org.modelinglab.ocl.ext.complextypes.operations.umlClass.AllUmlClassOperations;
import org.modelinglab.ocl.ext.complextypes.operations.umlEnum.AllUmlEnumOperations;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class ComplexTypesLibrary {

    private ComplexTypesLibrary() {
    }
    
    public static Set<Classifier> createTypes() {
        Set<Classifier> result = new HashSet<>(0);
        
        return result;
    }
    
    public static OperationsStore createStore() {
        OperationsStore.OperationsStoreFactory factory = new OperationsStore.OperationsStoreFactory();
        
        factory.addAllOperations(AllStringOperations.getOperators());
        factory.addAllOperations(AllUmlClassOperations.getOperators());
        factory.addAllOperations(AllUmlEnumOperations.getOperators());
        
        return factory.createStore();
    }
    
    public static Set<OperationEvaluator> getEvaluators() {
        Set<OperationEvaluator> evaluators = new HashSet<>();
        
        evaluators.addAll(AllStringEvaluators.getEvaluators());
        evaluators.addAll(AllUmlClassEvaluators.getEvaluators());
        evaluators.addAll(AllUmlEnumEvaluators.getEvaluators());
        
        return evaluators;
    }
    
}
