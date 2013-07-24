/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser;

import org.modelinglab.ocl.core.ast.types.MultiplicityElement;
import org.modelinglab.ocl.core.ast.UmlEnumLiteral;
import org.modelinglab.ocl.core.ast.AssociationEnd;
import org.modelinglab.ocl.core.ast.Attribute;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.UmlEnum;
import org.modelinglab.ocl.core.ast.*;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class CompanyEnvironment {
    
    private CompanyEnvironment() {
    }
    
    public static StaticEnvironment createEnvironment() {
        StaticEnvironment env;
        OperationsStore os = new OperationsStore.OperationsStoreFactory().createStore();
        
        env = new StaticEnvironment(os);
     
        Attribute attr;
        AssociationEnd asso1, asso2;
        
        UmlClass person;
        person = new UmlClass();
        person.setName("Person");
        attr = new Attribute();
        attr.setName("name");
        attr.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        person.addOwnedAttribute(attr);
        Operation op = new Operation(null) {

            @Override
            public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
                return this;
            }
        };
        op.setName("opWithoutArgs");
        List<Parameter> params = Collections.emptyList();
        op.setOwnedParameters(params);
        op.setSource(person);
        op.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        person.addOperation(op);
        
        op = new Operation(null) {

            @Override
            public Operation specificOperation(Classifier sourceType, List<Classifier> argTypes, TemplateRestrictions restrictions) {
                return this;
            }
        };
        op.setName("opWithArgs");
        params = Arrays.asList(new Parameter[] {
            new Parameter("arg1", PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN), op)
        });
        op.setOwnedParameters(params);
        op.setSource(person);
        op.setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        person.addOperation(op);
        
        
        UmlClass employee;
        employee = new UmlClass();
        employee.setName("Employee");
        employee.addSuperClass(person);
        
        UmlClass employment = new UmlClass();
        employment.setName("Employment");
        attr = new Attribute();
        attr.setName("salary");
        attr.setReferredType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        employee.addOwnedAttribute(attr);
        
        asso1 = new AssociationEnd();
        asso1.setName("employments");
        asso1.setLowerBound(0);
        asso1.setUpperBound(MultiplicityElement.UNBOUND);
        asso1.setUmlClass(employee);
        asso2 = new AssociationEnd();
        asso2.setName("employees");
        asso2.setLowerBound(0);
        asso2.setUpperBound(MultiplicityElement.UNBOUND);
        asso2.setUmlClass(employment);
        
        asso1.setOpposited(asso2);
        
        env.addElement(person, false);
        env.addElement(employee, false);
        env.addElement(employment, false);
        
        UmlEnum daysOfWeek = new UmlEnum();
        daysOfWeek.setName("DaysOfWeek");
        daysOfWeek.addLiteral(new UmlEnumLiteral("MONDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("TUESDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("WEDNESDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("THURSDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("FRIDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("SATURDAY"));
        daysOfWeek.addLiteral(new UmlEnumLiteral("SUNDAY"));
        
        env.addElement(daysOfWeek, false);
        
        return env;
    }
}
