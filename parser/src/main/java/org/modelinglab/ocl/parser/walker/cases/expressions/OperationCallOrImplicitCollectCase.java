/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.exceptions.OclException;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.AOperationCallOrImplicitCollectOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.POclExpressionCS;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import org.modelinglab.ocl.parser.walker.OclInvalidOperationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OperationCallOrImplicitCollectCase {

    private OperationCallOrImplicitCollectCase() {
    }

    public static OperationCallOrImplicitCollectCase getInstance() {
        return OperationCallOrImplicitCollectCaseHolder.INSTANCE;
    }

    public void out(AOperationCallOrImplicitCollectOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression source = (OclExpression) map.remove(node.getSource());
        
        List<String> names = (List<String>) map.remove(node.getNameCS());

        /*
         * TODO: Check if names could be a path
         */
        String name = names.get(names.size() - 1);
        assert name != null;

        List<OclExpression> args = new ArrayList<OclExpression>(node.getArgs().size());
        List<Classifier> argTypes = new ArrayList<Classifier>(node.getArgs().size());
        for (POclExpressionCS argExp : node.getArgs()) {
            OclExpression arg = (OclExpression) map.remove(argExp);

            args.add(arg);
            argTypes.add(arg.getType());
        }

        if (source.getType() instanceof CollectionType) { //it should be an implicit collect
            CollectionType sourceType = (CollectionType) source.getType();
            Variable itVar = new Variable();
            assert sourceType.getElementType() != null;
            itVar.setType(sourceType.getElementType());
            itVar.setName("");
            
            VariableExp varExp = new VariableExp();
            varExp.setReferredVariable(itVar);
            
            Operation op = null;
            try {
                op = env.lookupOperation(sourceType.getElementType(), name, argTypes);
            }
            catch (OclException ex) {
                throw new OclParserException(node, ex);
            }
            
            OperationCallExp body = createOperationCall(op, args, varExp);
            
            if (body.getReferredOperation() == null) {
                throw new OclInvalidOperationException(name, body, argTypes, node);
            }

            IteratorExp result;
            try {
                result = env.lookupIterator(sourceType, "collect", body.getType());
            } catch (IllegalIteratorException ex) {
                throw new OclParserException(node, ex);
            }

            result.setBody(body);
            result.setSource(source);

            List<Variable> itVars = new ArrayList<Variable>(1);
            itVars.add(itVar);
            result.setIterators(itVars);
            
            map.put(node, result);
            
        } else { //it should be a operation call
            Operation op = null;
            
            try {
                op = env.lookupOperation(source, name, argTypes);
            }
            catch (OclException ex) {
                throw new OclParserException(node, ex);
            }
            OperationCallExp result = createOperationCall(op, args, source);

            if (result.getReferredOperation() == null) {
                throw new OclInvalidOperationException(name, result, argTypes, node);
            }

            map.put(node, result);
        }
    }

    private OperationCallExp createOperationCall(Operation op, List<OclExpression> args, OclExpression source) {
        OperationCallExp result = new OperationCallExp();
        result.setArguments(args);
        result.setSource(source);
        result.setReferredOperation(op);

        return result;
    }

    private static class OperationCallOrImplicitCollectCaseHolder {

        private static final OperationCallOrImplicitCollectCase INSTANCE = new OperationCallOrImplicitCollectCase();

        private OperationCallOrImplicitCollectCaseHolder() {
        }
    }
}
