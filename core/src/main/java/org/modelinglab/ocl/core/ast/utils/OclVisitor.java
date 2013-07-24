/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

/**
 *
 * @param <Result> the return type of this visitor's methods. Use Void for visitors that do not need to return results.
 * @param <Arg> the type of the additional parameter to this visitor's methods. Use Void for visitors that do not need an additional parameter.
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface OclVisitor<Result, Arg> extends
        OclExpressionsVisitor<Result, Arg>,
        ClassifierVisitor<Result, Arg>,
        UmlVisitor<Result, Arg>,
        CollectionLiteralPartVisitor<Result, Arg>,
        TupleLiteralPartVisitor<Result, Arg> {
}
