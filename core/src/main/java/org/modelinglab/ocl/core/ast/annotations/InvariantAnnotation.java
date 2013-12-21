/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.annotations;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is an OCL element annotation for representing an invariant
 * 
 * @author Miguel Angel Garcia de Dios <miguelangel.garcia at imdea.org>
 */
public class InvariantAnnotation implements Serializable {
    private static final long serialVersionUID = 1L;
    private Collection<Invariant> invariants;
   

    public InvariantAnnotation() {
        this.invariants = new ArrayList<>();
    }

    public Collection<Invariant> getInvariants() {
        return this.invariants;
    }

    public void setInvariants(Collection<Invariant> invariants) {
        this.invariants = invariants;
    }

    public void addInvariants(Collection<Invariant> moreInvariants) {
        this.invariants.addAll(moreInvariants);
    }
    
    public void addInvariant(Invariant invariant) {
        this.invariants.add(invariant);
    }
}
