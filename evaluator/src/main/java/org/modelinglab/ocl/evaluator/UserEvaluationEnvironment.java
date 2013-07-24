/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.evaluator.operations.OperationEvaluator;
import java.io.Serializable;
import java.util.*;
import org.modelinglab.ocl.core.ast.OperationsStore;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.evaluator.operations.StandardOperationEvaluatorsProvider;

/**
 * Instances of this class provides classes and especial evaluators defined by the user of the library.
 * In other words, contains all valid classes and one evaluator for each non standard operation that user
 * wants to use.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class UserEvaluationEnvironment implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Classes indexed by its path
     */
    private final Map<String, UmlClass> classes;
    private final InstanceProvider instancesProvider;
    private final Map<Operation, OperationEvaluator> userOperationEvaluators;
    private final OperationsStore userOperationStore;
    private final OclEvaluatorAnalyzer analyzer;

    public UserEvaluationEnvironment(
            Collection<UmlClass> classes,
            InstanceProvider instancesProvider,
            Set<OperationEvaluator> uoe,
            OperationsStore userOperationStore,
            OclEvaluatorAnalyzer analyzer) {

        /*
         * Initializing instancesProvider attribute
         */
        this.instancesProvider = instancesProvider;

        /*
         * Initializing classes attribute
         */
        HashMap<String, UmlClass> tempClasses = new HashMap<>(classes.size());
        for (UmlClass umlClass : classes) {
            Object o = tempClasses.put(umlClass.getPath(), umlClass);
            Preconditions.checkArgument(o == null, "There are at least two classes whose path is " + umlClass.getPath());
        }
        this.classes = Collections.unmodifiableMap(tempClasses);

        /*
         * Initializing operationEvaluators attribute
         */
        Map<Operation, OperationEvaluator> tempOpEvals = new HashMap<>(uoe.size());
        for (OperationEvaluator opEv : uoe) {
            Preconditions.checkArgument(
                    StandardOperationEvaluatorsProvider.getInstance().getEvaluator(opEv.getEvaluableOperation()) == null,
                    "The standard evaluator of " + opEv.getEvaluableOperation().getSignature() + " is not overloadable.");

            Object o = tempOpEvals.put(opEv.getEvaluableOperation(), opEv);

            Preconditions.checkArgument(o == null, "There are at least two evaluators for " + opEv.getEvaluableOperation());
        }
        this.userOperationEvaluators = Collections.unmodifiableMap(tempOpEvals);

        this.userOperationStore = userOperationStore;
        
        if (analyzer == null) {
            this.analyzer = new DefaultOclEvaluatorAnalyzer();
        } else {
            this.analyzer = analyzer;
        }
    }

    public UserEvaluationEnvironment(
            Collection<UmlClass> classes,
            InstanceProvider instancesProvider,
            Set<OperationEvaluator> uoe,
            OperationsStore userOperationStore) {
        this(classes, instancesProvider, uoe, userOperationStore, null);
    }

    public UserEvaluationEnvironment(
            StaticEnvironment staticEnv,
            InstanceProvider instancesProvider,
            Set<OperationEvaluator> uoe,
            OclEvaluatorAnalyzer analyzer) {

        this(staticEnv.getAllClasses(), instancesProvider, uoe, staticEnv.getOpStore(), analyzer);
    }

    public UserEvaluationEnvironment(
            StaticEnvironment staticEnv,
            InstanceProvider instancesProvider,
            Set<OperationEvaluator> uoe) {

        this(staticEnv.getAllClasses(), instancesProvider, uoe, staticEnv.getOpStore(), null);
    }

    public Map<String, UmlClass> getClasses() {
        return classes;
    }

    public Map<Operation, OperationEvaluator> getUserOperationEvaluators() {
        return userOperationEvaluators;
    }

    public OperationEvaluator getUserOperationEvaluator(Operation op) {
        return userOperationEvaluators.get(op);
    }

    public InstanceProvider getInstancesProvider() {
        return instancesProvider;
    }

    public OperationsStore getUserOperationStore() {
        return userOperationStore;
    }

    public OclEvaluatorAnalyzer getAnalyzer() {
        return analyzer;
    }
}
