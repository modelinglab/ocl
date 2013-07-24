/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.expressions.CollectionItem;
import org.modelinglab.ocl.core.ast.expressions.CollectionLiteralPart;
import org.modelinglab.ocl.core.ast.expressions.CollectionRange;

/**
 *
 * @param <Result> the return type of this visitor's methods. Use Void for visitors that do not need to return results.
 * @param <Arg> the type of the additional parameter to this visitor's methods. Use Void for visitors that do not need an additional parameter.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface CollectionLiteralPartVisitor<Result, Arg> {
    
    Result visit(CollectionRange obj, Arg argument);
    
    Result visit(CollectionItem obj, Arg argument);
    
}
