/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.Element;
import java.util.List;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class CallExp extends OclExpression {
    
    OclExpression source;

    public CallExp() {
    }
    
    protected CallExp(CallExp other) {
        super(other);
        setSource(other.getSource().clone());
    }

    public OclExpression getSource() {
        return source;
    }

    public final void setSource(OclExpression source) {
        modifyAttempt();
        changeChild(this.source, source);
        this.source = source;
    }

    @Override
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.add(source);
        return result;
    }

    protected boolean equalsCallExp(@Nonnull CallExp other) {
        if (this.source != other.source && (this.source == null || !this.source.equals(other.source))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (this.source != null ? this.source.hashCode() : 0);
        return hash;
    }

    @Override
    public abstract boolean equals(Object obj);
    
    
    
}
