/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.vartables;

import java.io.Serializable;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface IVariableTable extends Serializable, Iterable<String> {

     /**
     * @param varName
     *
     * @return The stored variable with this name if any or null in other case.
     */
    @Nonnull
    public Variable getVariable(@Nonnull String varName);
    
    public boolean exists(@Nonnull String varName);

    /**
     * Creates a new variable and set is value
     *
     * @param var
     * @param value
     * @return the new transaction variable
     * @throws IllegalArgumentException if !value.getType().conforms(var.getType())
     * @throws VariableAlreadyExistException if there is already a variable with that name 
     */
    @Nonnull
    public Variable createVariable(@Nonnull Variable var, @Nonnull OclValue<?> value) throws VariableAlreadyExistException, IllegalArgumentException;

    /**
     * Set the value of an existent variable.
     *
     * @param varName
     * @param value
     *
     * @throws VariableNotExistException if there is not variable with this name
     * @throws IllegalArgumentException if !value.getType().conforms(getVariable(varName).getType())
     */
    @Nonnull
    public void setValue(@Nonnull String varName, @Nonnull OclValue<?> value) throws VariableNotExistException, IllegalArgumentException;

    /**
     * Return the value of the stored variable with this name
     *
     * @param varName
     *
     * @return
     *
     * @throws VariableNotExistException if there is not variable with this name
     */
    @Nonnull
    public OclValue<?> getValue(@Nonnull String varName) throws VariableNotExistException;
    
    public void removeVariable(@Nonnull String varName) throws VariableNotExistException;
    
}
