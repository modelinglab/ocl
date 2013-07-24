/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.vartables;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.values.OclValue;

/**
 * A HierarchyVarTable is an IVariableTable that extends another IVariableTable. The extended IVariableTable
 * is called parent. An HierarchyVarTable shows to the world its own variables and variables defined in its
 * parent variable table. The most important point is that a HierarchyVarTable <b>can not</b> modify its
 * parent, so this variables are "protected".
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class HierarchyVarTable implements IVariableTable {

    private static final long serialVersionUID = 1L;
    final IVariableTable parent;
    final VariableTable modificable;

    /**
     * Creates a new variable table that already see (but not modifies) variables in its parent
     *
     * @param parent
     */
    public HierarchyVarTable(IVariableTable parent) {
        this.parent = parent;

        modificable = new VariableTable();
    }

    /**
     * @param varName
     *
     * @return if there is a variable with that name in this object, returns its value. In other case
     *         delegates in the parent variable table.
     */
    @Override
    public Variable getVariable(String varName) {
        Variable var = modificable.getVariable(varName);
        if (var == null) {
            var = parent.getVariable(varName);
        }
        return var;
    }

    /**
     *
     * @param varName
     *
     * @return (this table).exists(varName) || parent.exits(varName)
     */
    @Override
    public boolean exists(String varName) {
        return getVariable(varName) != null;
    }

    /**
     *
     * @param var
     * @param value
     *
     * @return if this.exits(var.getName() is false, then creates the variable in the modificable variable
     *         table. Parent table is not being change.
     *
     * @throws IllegalArgumentException if this.exists(var.getName())
     */
    @Override
    public Variable createVariable(Variable var, OclValue<?> value) throws IllegalArgumentException, VariableAlreadyExistException {
        if (parent.getVariable(var.getName()) != null) {
            throw new VariableAlreadyExistException(var.getName());
        }
        return modificable.createVariable(var, value);
    }

    /**
     * Sets <em>value</em> as the value of variable with name <em>varName</em> <b>in the modificable variable
     * table</b>. If variable is defined in the parent variable table (and this implies that is not defined in
     * modificable variable table), an IllegalArgumentException is thrown
     * 
     * @param varName
     * @param value 
     * @throws IllegalArgumentException if value does not conforms with variable type or if variable is in the
     * parent variable table
     */
    @Override
    public void setValue(String varName, OclValue<?> value) throws IllegalArgumentException, VariableNotExistException {
        if (parent.exists(varName)) {
            throw new IllegalArgumentException(varName + " is a variable defined in the protected variable "
                    + "table, so it can not be modified.");
        }
        else {
            modificable.setValue(varName, value);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public OclValue<?> getValue(String varName) throws IllegalArgumentException, VariableNotExistException {
        if (modificable.getVariable(varName) != null) {
            return modificable.getValue(varName);
        }
        return parent.getValue(varName);
    }

    @Override
    public void removeVariable(String varName) throws VariableNotExistException {
        if (parent.exists(varName)) {
            throw new IllegalArgumentException(varName + " is a variable defined in the protected variable "
                    + "table, so it can not be deleted.");
        }
        else {
            modificable.removeVariable(varName);
        }
    }

    @Override
    public Iterator<String> iterator() {
        List<Iterable<String>> its = new ArrayList<>(2);
        its.add(parent);
        its.add(modificable);
        return Iterables.concat(its).iterator();
    }
}
