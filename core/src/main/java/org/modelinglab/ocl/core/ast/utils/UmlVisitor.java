/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Comment;
import org.modelinglab.ocl.core.ast.Namespace;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;

/**
 *
 * @param <Result> the return type of this visitor's methods. Use Void for visitors that do not need to return results.
 * @param <Arg> the type of the additional parameter to this visitor's methods. Use Void for visitors that do not need an additional parameter.
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface UmlVisitor<Result, Arg> {

    Result visit(AssociationEnd o, Arg argument);

    Result visit(Attribute o, Arg argument);

    Result visit(Comment o, Arg argument);

    Result visit(Operation o, Arg argument);

    Result visit(Parameter o, Arg argument);

    Result visit(UmlClass o, Arg argument);

    Result visit(UmlEnum o, Arg argument);

    Result visit(UmlEnumLiteral o, Arg argument);
    
    Result visit(Namespace o, Arg argument);
    
    
}
