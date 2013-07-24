/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser.walker.cases.expressions;

import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.TupleType;
import org.modelinglab.ocl.parser.OclParserException;
import org.modelinglab.ocl.parser.sablecc.node.ANavigationOrPropertyCallOrImplicitCollectOclExpressionCS;
import org.modelinglab.ocl.parser.sablecc.node.Node;
import org.modelinglab.ocl.parser.walker.ConcreteToAbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class NavigationOrAttributeCallOrImplicitCollectCase {

    private NavigationOrAttributeCallOrImplicitCollectCase() {
    }

    public static NavigationOrAttributeCallOrImplicitCollectCase getInstance() {
        return NavigationOrAttributeCallOrImplicitCollectHolder.INSTANCE;
    }

    public void out(ANavigationOrPropertyCallOrImplicitCollectOclExpressionCS node, ConcreteToAbstractMap map, StaticEnvironment env) throws OclParserException {
        OclExpression source = (OclExpression) map.remove(node.getSource());

        List<String> names = (List<String>) map.remove(node.getNameCS());

        /*
         * TODO: Check if names could be a path
         */
        String name = names.get(names.size() - 1);
        assert name != null;

        if (source.getType() instanceof CollectionType) { //it should be an implicit collect
            CollectionType colType = (CollectionType) source.getType();
            Variable itVar = new Variable();
            assert colType.getElementType() != null;

            itVar.setName("");
            itVar.setType(colType.getElementType());

            VariableExp varExp = new VariableExp();
            varExp.setReferredVariable(itVar);

            CallExp body = createFeatureOrTupleCall(node, name, varExp);

            IteratorExp result;
            try {
                result = env.lookupIterator((CollectionType) source.getType(), "collect", body.getType());
            } catch (IllegalIteratorException ex) {
                throw new OclParserException(node, ex);
            }

            result.setBody(body);
            result.setSource(source);

            List<Variable> itVars = new ArrayList<Variable>(1);
            itVars.add(itVar);
            result.setIterators(itVars);

            map.put(node, result);
        } else {
            CallExp body = createFeatureOrTupleCall(node, name, source);
            map.put(node, body);
        }
    }

    private CallExp createFeatureOrTupleCall(Node node, String featureName, OclExpression source) throws OclParserException {
        CallExp exp = null;

        Classifier sourceType = source.getType(); //should be an UmlClass or a TupleType
        if (sourceType instanceof TupleType) {
            TupleType tuple = (TupleType) sourceType;

            Classifier resultType = tuple.getAttributeType(featureName);
            if (resultType == null) {
                throw new OclParserException(node, "There is no property called " + featureName
                        + " in " + tuple + '.');
            }
            TupleAttributeCallExp result = new TupleAttributeCallExp();
            result.setTupleAttributeId(featureName);
            
            exp = result;
        }
        //sourceType should be an UmlClass
        else if ((sourceType instanceof UmlClass)) {
            UmlClass clazz = (UmlClass) sourceType;

            for (Attribute attribute : clazz.getAllAttributes()) {
                if (attribute.getName().equals(featureName)) {
                    AttributeCallExp assoExp = new AttributeCallExp();
                    exp = assoExp;

                    assoExp.setReferredAttribute(attribute);
                    break;
                }
            }
            if (exp == null) {
                for (AssociationEnd associationEnd : clazz.getAllAssociationEnds()) {
                    if (associationEnd.getName().equals(featureName)) {
                        AssociationEndCallExp assoExp = new AssociationEndCallExp();
                        exp = assoExp;

                        assoExp.setReferredAssociationEnd(associationEnd);
                        break;
                    }
                }
            }
            if (exp == null) {
                throw new OclParserException(node, "There is no property called '" + featureName + "' in " + clazz.getName() + ".");
            }
        }
        else { //sourcetype is not tuple or an uml class
            throw new OclParserException(node, "Source of that feature call should be an uml class or a tuple.");
        }

        exp.setSource(source);

        return exp;
    }

    private static class NavigationOrAttributeCallOrImplicitCollectHolder {

        private static final NavigationOrAttributeCallOrImplicitCollectCase INSTANCE = new NavigationOrAttributeCallOrImplicitCollectCase();

        private NavigationOrAttributeCallOrImplicitCollectHolder() {
        }
    }
}
