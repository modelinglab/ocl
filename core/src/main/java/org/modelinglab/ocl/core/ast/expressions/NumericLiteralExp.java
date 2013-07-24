/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class NumericLiteralExp<E extends Number> extends PrimitiveLiteralExp<E> {

    public NumericLiteralExp(E value) {
        super(value);
    }

    public NumericLiteralExp() {
    }

}
