package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.expressions.OclExpression;

/**
 * Interface used by generic OCL analysis
 */
public interface OclAnalysis {

    /**
     * This method is called when an OCL expression is analyzed.
     *
     * @param exp the expression to be analyze
     * <p/>
     * @throws Exception exception thrown if the analysis considers the
     *                   expression wrong
     */
    public void analyze(OclExpression exp) throws Exception;
}
