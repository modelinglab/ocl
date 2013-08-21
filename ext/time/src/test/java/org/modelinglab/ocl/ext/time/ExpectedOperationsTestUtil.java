/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.ext.time;

import com.google.common.collect.Iterators;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.OperationsStore;
import org.modelinglab.ocl.core.ast.types.Classifier;

/**
 *
 */
public class ExpectedOperationsTestUtil {

    static OperationsStore operationsStore = new DateLibrary().createStore();

    public static void test(Class<?> clazz, Collection<Method> expectedMethods) {
        Classifier classifier = DateUtils.translateToClassifier(clazz);

        for (final Method method : expectedMethods) {
            String methodName = method.getName();

            boolean found = false;
            Iterator<Operation> it = Iterators.concat(
                    operationsStore.getOperations(classifier, methodName),
                    operationsStore.getOperations(classifier.getClassifierType(), methodName));
            while (it.hasNext()) {
                if (conforms(method, it.next())) {
                    found = true;
                    break;
                }
            }
            assert found : "Method " + method + " is not implemented in " + classifier;
        }
    }

    private static boolean conforms(Method method, Operation op) {
        if (!method.getName().equals(op.getName())) {
            return false;
        }
        if (method.getParameterTypes().length != op.getOwnedParameters().size()) {
            return false;
        }
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Class<?> javaArg = method.getParameterTypes()[i];
            Classifier oclArg = op.getOwnedParameters().get(i).getType();

            if (!DateUtils.translateToClassifier(javaArg).equals(oclArg)) {
                return false;
            }
        }
        return true;
    }
}
