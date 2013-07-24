/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.vartables;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.values.OclValue;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class VariableTable implements Serializable, IVariableTable {
    private static final long serialVersionUID = 1L;
    
    Map<String, OclValue<?>> values;
    Map<String, Variable> variables;

    public VariableTable() {
        values = new HashMap<>();
        variables = new HashMap<>();
    }

    public VariableTable(Map<String, OclValue<?>> values, Map<String, Variable> variables) {
        this.values = new HashMap<>(values);
        this.variables = new HashMap<>(variables);
    }
    
    public VariableTable(IVariableTable varTable) {
        values = new HashMap<>();
        variables = new HashMap<>();
        
        for (final String varName : varTable) {
            this.createVariable(varTable.getVariable(varName), varTable.getValue(varName));
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Variable getVariable(@Nonnull String varName) {
        Variable result = variables.get(varName);
        assert values.containsKey(varName) == (result != null);
        return result;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public boolean exists(@Nonnull String varName) {
        return getVariable(varName) != null;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Variable createVariable(@Nonnull Variable var, @Nonnull OclValue<?> value) throws VariableAlreadyExistException, IllegalArgumentException {
        String varName = var.getName();
        if (variables.containsKey(varName)) {
            throw new VariableAlreadyExistException(varName);
        }
        if (!value.getType().conformsTo(var.getType())) {
            throw new IllegalArgumentException("Value type ("+value.getType()+") does not conforms with "
                    + "variable type ("+var.getType()+").");
        }
        variables.put(varName, var);
        values.put(varName, value);
        return var;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void setValue(@Nonnull String varName, @Nonnull OclValue<?> value) throws IllegalArgumentException, VariableNotExistException {
        Variable var = variables.get(varName);
        if (var == null) {
            throw new VariableNotExistException(varName);
        }
        if (!value.getType().conformsTo(var.getType())) {
            throw new IllegalArgumentException("Value type ("+value.getType()+") does not conforms with "
                    + "variable type ("+var.getType()+").");
        }
        values.put(varName, value);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public OclValue<?> getValue(@Nonnull String varName) throws VariableNotExistException {
        OclValue<?> result = values.get(varName);
        if (result == null) {
            throw new VariableNotExistException(varName);
        }
        assert variables.containsKey(varName);
        return result;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public void removeVariable(String varName) throws VariableNotExistException {
        if (!variables.containsKey(varName)) {
            assert !values.containsKey(varName);
            throw new VariableNotExistException(varName);
        }
        assert values.containsKey(varName);
        values.remove(varName);
        variables.remove(varName);
    }
    
    public void clear() {
        variables.clear();
        values.clear();
    }
    
    @Override
    public Iterator<String> iterator() {
        return values.keySet().iterator();
    }
    
}
