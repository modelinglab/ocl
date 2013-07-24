/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator;

import java.io.Serializable;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.values.ObjectValue;
import org.modelinglab.ocl.core.values.SetValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface InstanceProvider<K> extends Serializable {

    /**
     * This method is called before evaluating an expression. The expression cannot be changed.
     *
     * @param exp The expression that will be evaluated. The expression cannot be changed.
     * @param env The enviroment in which ocl expression will be evaluated. If this method changes
     * the environment is expected that {@link InstanceProvider#postExpression(org.modelinglab.ocl.core.ast.expressions.OclExpression, org.modelinglab.ocl.evaluator.EvaluationEnvironment) }
     * restores its previous state.
     */
    public void preExpression(@Nonnull OclExpression exp, @Nonnull UserEvaluationEnvironment env);

    /**
     * This method is called after evaluating an expression.
     *
     * @param exp The expression that was be evaluated. The expression cannot be changed.
     * @param env The enviroment in which ocl expression was be evaluated. If 
     * {@link InstanceProvider#preExpression(org.modelinglab.ocl.core.ast.expressions.OclExpression, org.modelinglab.ocl.evaluator.EvaluationEnvironment) }
     * changes the environment, is expected that this method restores its previous state.
     */
    public void postExpression(@Nonnull OclExpression exp, @Nonnull UserEvaluationEnvironment env);

    /**
     * This method has the semantic of Class.AllInstances() OCL operation
     * @param clazz 
     * @return all instances of this class that are visible in the metamodel.
     */
    public SetValue<ObjectValue<K>> getAllInstances(@Nonnull UmlClass clazz);

    /**
     * Checks if the object passed as argument is valid in this context or not.
     * 
     * For example, maybe there is no object of this class with this ID.
     * @param val
     * @throws UnexpectedObjectException 
     */
    public void checkObject(ObjectValue<Long> val) throws UnexpectedObjectException;
}
