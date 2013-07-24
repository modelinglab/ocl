/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.iterators;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar
 */
public class OclValueIndirection<E> {
    E value;

    public OclValueIndirection() {
    }

    public OclValueIndirection(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
    
}
