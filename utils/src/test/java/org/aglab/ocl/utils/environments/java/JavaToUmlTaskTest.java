/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.aglab.ocl.utils.environments.java;

import org.aglab.ocl.utils.environments.java.classes.SameClass1;
import org.aglab.ocl.utils.environments.java.classes.SimpleChildClass;
import org.aglab.ocl.utils.environments.java.classes.SameClass2;
import org.aglab.ocl.utils.environments.java.classes.CollectionClass;
import org.aglab.ocl.utils.environments.java.classes.SimpleClass;
import org.modelinglab.ocl.utils.environments.java.JavaToUmlTask;
import org.modelinglab.ocl.utils.environments.java.JavaToUmlTaskContext;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.Namespace;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.utils.environments.StandardNamespace;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.junit.Ignore;
import org.junit.Test;
import org.omg.CORBA.UserException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class JavaToUmlTaskTest {
    
    JavaToUmlTask task;
    JavaToUmlTaskContext context;
    
    public JavaToUmlTaskTest() {
        StandardNamespace standardNS = new StandardNamespace();
        
        context = new JavaToUmlTaskContext();
        context.setStandardNS(standardNS);
        
        task = new JavaToUmlTask(context);
    }
    
    @Test
    public void testExecute_Null() {
        context.setClassesToMap(null);
        
        try {
            task.call();
            assert false : "Task should fail if classesToMap is null.";
        }
        catch (IllegalStateException ex) {
        }
    }

    @Test
    public void testExecute_Empty() {
        context.setClassesToMap(new HashSet<Class>());
        
        task.call();
    }
    
    @Ignore
    @Test
    public void testExecute_StandardClasses() {
        Set<Class> standardClasses = new HashSet<Class>();
        
        standardClasses.add(Integer.class);
        standardClasses.add(Float.class);
        standardClasses.add(Double.class);
        standardClasses.add(String.class);
        standardClasses.add(Character.class);
        standardClasses.add(File.class);
        standardClasses.add(Date.class);
        
        context.setClassesToMap(standardClasses);
        
        Namespace result    = task.call();
        Namespace expected  = new Namespace();        
        
        assert result.equals(expected);
    }
    
    @Test
    public void testExecute_CollisionToStandard() {
        Set<Class> classes = new HashSet<Class>();
        
        classes.add(org.aglab.ocl.utils.environments.java.classes.File.class);
        
        context.setClassesToMap(classes);
        
        try {
            task.call();
            assert false : "Task should fail if there is a class which name is the same than other "
                    + "in standard namespace.";
        }
        catch (RuntimeException ex) {
            
        }        
    }
    
    @Test
    public void testExecute_CollisionToOther() {
        Set<Class> classes = new HashSet<Class>();
        
        classes.add(SameClass1.SameClass.class);
        classes.add(SameClass2.SameClass.class);
        
        context.setClassesToMap(classes);
        
        try {
            task.call();
            assert false : "Task should fail if there is a class which name is the same than other.";
        }
        catch (RuntimeException ex) {
        }
    }
    
    @Ignore
    @Test
    public void testExecute_Simple() {
        Set<Class> classes = new HashSet<Class>();
        
        classes.add(SimpleClass.class);
        
        context.setClassesToMap(classes);
        
        Namespace result    = task.call();
        
        UmlClass expectedClazz = new UmlClass();
        expectedClazz.setName(SimpleClass.class.getSimpleName());

        Attribute att = new Attribute();
        att.setName("i");
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("fl");
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("dou");
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("s");
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("c");
        att.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("fi");
        att.setReferredType((Classifier) context.getStandardNS().lookup("File"));
        expectedClazz.addOwnedAttribute(att);
        
        att = new Attribute();
        att.setName("da");
        att.setReferredType((Classifier) context.getStandardNS().lookup("Date"));
        expectedClazz.addOwnedAttribute(att);
        
        UmlClass resultClass = (UmlClass) result.lookup("SimpleClass");
        assert resultClass != null;
        
        for (Attribute attribute : expectedClazz.getOwnedAttributes()) {
            Attribute otherAtt = resultClass.lookforAttribute(attribute.getName(), false);
            assert otherAtt != null : "There is no attribute with '"+ attribute.getName() +"' name.";
            assert otherAtt.getName().equals(attribute.getName());
            assert otherAtt.getType().equals(attribute.getType());
            assert otherAtt.getUpperBound() == attribute.getUpperBound();
            assert otherAtt.getLowerBound() == attribute.getLowerBound();
            assert otherAtt.isUnique()      == attribute.isUnique();
            assert otherAtt.isOrdered()     == attribute.isOrdered();
        }
    }
    
    @Test
    public void testExecute_hierarchy() {
        Set<Class> classes = new HashSet<Class>();

        /*
         * not necessary to add the parent class. this class will be automatically mapped
         */
//        classes.add(SimpleClass.class);
        classes.add(SimpleChildClass.class);
        
        context.setClassesToMap(classes);
        
        Namespace result    = task.call();
        
        UmlClass simpleClass = (UmlClass) result.lookup("SimpleClass");
        UmlClass simpleChildClass = (UmlClass) result.lookup("SimpleChildClass");
        UmlClass serializable = (UmlClass) result.lookup("Serializable");
        
        assert simpleClass != null;
        assert simpleChildClass != null;
        assert serializable != null;
        
        assert simpleClass.getSubClasses().contains(simpleChildClass);
        assert simpleChildClass.getSuperClasses().contains(simpleClass);
        
        assert serializable.getSubClasses().contains(simpleClass);
        assert simpleClass.getSuperClasses().contains(serializable);
        assert OclUtils.getAllSuperclasses(simpleChildClass).contains(serializable);
    }
    
    @Ignore
    @Test
    public void testExecute_Collection() {
        Set<Class> classes = new HashSet<Class>();

        classes.add(CollectionClass.class);
        
        context.setClassesToMap(classes);
        
        Namespace result    = task.call();
        
        assert false;
    }
}
