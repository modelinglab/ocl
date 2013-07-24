/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.expressions;

import org.modelinglab.ocl.core.ast.TypedElement;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.utils.OclExpressionsVisitor;
import org.modelinglab.ocl.core.exceptions.IllegalOclExpression;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.OclValue;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public abstract class OclExpression extends TypedElement implements Cloneable {

    public OclExpression() {
    }

    /**
     * Copy constructor. It is very important to kwnow that <b>UML classes, attributes, associations and
     * operations are NOT cloned!</b>
     */
    protected OclExpression(OclExpression other) {
        super(other);
    }

    @Override
    @Deprecated
    public void setType(Classifier type) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public abstract String getName();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

    @Override
    public abstract String toString();

    /**
     *
     * @return a parseable OCL expression in standard concrate syntax
     * @throws InvalidOclExpression if tree expression is invalid
     * @see OclExpression#checkValid()
     * @see OclCorrectionChecker
     */
    public String toText() throws IllegalOclExpression {
        return toString();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("This operation has a derived name.");
    }

    /**
     * Clones the expression and all its expression children, variables and parts. It is very important to
     * kwnow that <b>UML classes, attributes, associations and operations are NOT cloned!</b>
     */
    @Override
    public abstract OclExpression clone();

    /**
     * Checks if the expression is valid or legal. In other words, checks that all statical preconditions and
     * invariants are matched. This preconditions are like: <p><code>AttributeCallExp.allInstancies()->forall(ace |
     * ace.source.conformsWith(ace.getAttribute().getOwner())</code></p>
     *
     * @throws IllegalOclExpression
     */
    public void checkIsValid() throws IllegalOclExpression {
        //TODO: IMPLEMENT THIS METHOD IN EACH EXPRESSION!
    }

    /**
     * Expressions that can be evaluated without dynamic values (for example, some literal cases) returns its
     * value. The others return null. <p>Is a precondition that this expression is legal (ie, {@link #checkIsValid()
     * } does not throw exceptions
     *
     * @return If expression can be evaluated with static values, this value is returned. If expression need
     * dyniamic values, null is returned.
     */
    public abstract OclValue getStaticEvaluation() throws OclEvaluationException;

    public abstract <Result, Arg> Result accept(OclExpressionsVisitor<Result, Arg> visitor, Arg args);
}
