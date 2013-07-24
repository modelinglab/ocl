/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class DataType extends Classifier {

    public DataType() {
    }

    protected DataType(DataType other) {
        super(other);
    }
}
