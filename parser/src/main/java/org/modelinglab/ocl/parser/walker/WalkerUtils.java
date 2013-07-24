/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker;

import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.OperationCallExp;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class WalkerUtils {

    private WalkerUtils() {
    }

    public static WalkerUtils getInstance() {
        return WalkerUtilsHolder.INSTANCE;
    }

    public OclExpression implicitCollectionCast(OclExpression exp, StaticEnvironment env) {
        Preconditions.checkArgument(exp != null, "expression should be not null!");
        if (exp.getType() instanceof CollectionType) {
            return exp;
        }
        List<Classifier> argTypes = Collections.emptyList();
        Operation op = null;
        try {
            op = env.lookupOperation(exp, "asSet", argTypes);
        }
        catch (AmbiguosOperationCall ex) {
            throw new AssertionError("All classifiers could be casted as set.");
        }

        OperationCallExp opCall = new OperationCallExp();
        List<? extends OclExpression> args = Collections.emptyList();
        opCall.setArguments(args);
        opCall.setReferredOperation(op);
        opCall.setSource(exp);
        
        assert opCall.getType() instanceof CollectionType;

        return opCall;
    }
    
    private static class WalkerUtilsHolder {

        private static final WalkerUtils INSTANCE = new WalkerUtils();

        private WalkerUtilsHolder() {
        }
    }
}
