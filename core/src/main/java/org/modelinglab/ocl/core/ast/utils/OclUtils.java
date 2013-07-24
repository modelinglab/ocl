/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import com.google.common.base.Preconditions;
import org.modelinglab.ocl.core.ast.UmlClass;
import java.util.*;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclUtils {

    private OclUtils() {
    }

    /**
     * Obtains all of the classes that generalize the specified class.
     * 
     * @param clazz
     * @return all classes that generalize the specified class or empty none (clazz is not included)
     */
    public static Set<UmlClass> getAllSuperclasses(UmlClass clazz) {
        Preconditions.checkArgument(clazz != null,
                "It is not possible to get superclasses of a null class");

        Set<UmlClass> superClasses = new HashSet<UmlClass>();

        LinkedList<UmlClass> unexaminedClasses = new LinkedList<UmlClass>();
        unexaminedClasses.addAll(clazz.getSuperClasses());

        while (!unexaminedClasses.isEmpty()) {
            UmlClass nextClass = unexaminedClasses.removeLast();
            superClasses.add(nextClass);
            unexaminedClasses.addAll(nextClass.getSuperClasses());
        }
        return superClasses;
    }

    /**
     * TODO: This function is not complete. It would be improved in future
     * @param c1
     * @param c2
     * @return 
     */
    public static Classifier getLowestSharedType(Classifier c1, Classifier c2) {
        TemplateRestrictions restrictions = new TemplateRestrictions();
        
        if (c1.conformsTo(c2, restrictions)) {
            return c2.getRestrictedType(restrictions);
        }
        if (c2.conformsTo(c1, restrictions)) {
            return c1.getRestrictedType(restrictions);
        }
        List<Classifier> c1types = new LinkedList<Classifier>();
        c1types.add(c1);
        c1types.addAll(c1.getSuperClassifiers());

        List<Classifier> c2types = new LinkedList<Classifier>();
        c2types.add(c2);
        c2types.addAll(c2.getSuperClassifiers());

        
        for (Classifier c1Temp : c1types) {
            if (c1Temp instanceof AnyType) {
                break;
            }
            for (Classifier c2Temp : c2types) {
                if (c2Temp instanceof AnyType) {
                    break;
                }
                restrictions.clear();
                if (c1Temp.conformsTo(c2Temp, restrictions)) { //c1 extends c2 => c2 is super
                    return c2Temp.getRestrictedType(restrictions);
                }
                restrictions.clear();
                if (c2Temp.conformsTo(c1Temp, restrictions)) { //c2 extends c1 => c1 is super
                    return c1Temp.getRestrictedType(restrictions);
                }
            }
        }
        return AnyType.getInstance();
    }

    public static Classifier getLowestSharedType(Collection<? extends Classifier> classifiers) {
        Classifier lowestSharedType = VoidType.getInstance();
        for (Classifier classifier : classifiers) {
            lowestSharedType = getLowestSharedType(lowestSharedType, classifier);
        }
        return lowestSharedType;
    }
}
