/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import com.google.common.collect.Iterables;
import java.util.*;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.ClassifierType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclUtils;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.ast.utils.UmlVisitor;

/**
 * 
 * A class is a type that has objects as its instances.
 * <p>
 * Classes have attributes and operations and participate in inheritance hierarchies. 
 * Multiple inheritance is allowed. The instances of a class are objects. When a class is abstract 
 * it cannot have any direct instances. Any direct instance of a concrete (i.e., non-abstract) class
 * is also an indirect instance of its class’s superclasses. An object has a slot for each of its
 * class’s direct and inherited attributes. An object permits the invocation of operations defined
 * in its class and its class’s superclasses. The context of such an invocation is the invoked 
 * object.
 * </p>
 * <p>
 * A class cannot access private features of another class, or protected features on another class 
 * that is not its supertype. When creating and deleting associations, at least one end must allow 
 * access to the class.
 * </p>
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class UmlClass extends Classifier {

    private static final long serialVersionUID = 1L;
    private boolean _abstract;
    private HashSet<UmlClass> superClasses = new HashSet<UmlClass>();
    private HashSet<UmlClass> subClasses = new HashSet<UmlClass>();
    private OrderedSet<Attribute> ownedAttributes = new OrderedSet<Attribute>();
    private OrderedSet<AssociationEnd> ownedAssociationEnds = new OrderedSet<AssociationEnd>();
    private final SortedMap<String, Set<Operation>> operations = new TreeMap<String, Set<Operation>>();
    private static UmlClass genericInstance;
    private final ClassifierType classifierType = new ClassifierType(this);

    public static UmlClass getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new UmlClass();
            genericInstance.setName("UmlClass");
            genericInstance.setAbstract(true);
        }
        return genericInstance;
    }

    @Override
    public final ClassifierType getClassifierType() {
        return classifierType;
    }

    public final Map<UmlClass, Integer> getHierarchyLevels() {
        Map<UmlClass, Integer> result = new HashMap<UmlClass, Integer>();
        int level = 0;
        Set<UmlClass> actualLevel = new HashSet<UmlClass>();
        Set<UmlClass> nextLevel = new HashSet<UmlClass>();

        actualLevel.add(this);

        while (!actualLevel.isEmpty() || !nextLevel.isEmpty()) {
            Iterator<UmlClass> it = actualLevel.iterator();
            while (it.hasNext()) {
                UmlClass actualClass = it.next();
                it.remove();
                if (result.containsKey(actualClass)) {
                    assert result.get(actualClass) < level;
                } else {
                    result.put(actualClass, level);
                    nextLevel.addAll(actualClass.getSuperClasses());
                }
            }
            //actual level is finished
            Set<UmlClass> auxSet = nextLevel;
            nextLevel = actualLevel;
            actualLevel = auxSet;
            level++;
        }
        return result;
    }

    @Override
    public final void setName(String name) {
        modifyAttempt();
        setNameProtected(name);
    }

    /**
     * 
     * @return The {@link Operation} owned by a class. These do not include the inherited operations.
     */
    public final Set<Operation> getOperations() {
        Set set = new HashSet<Operation>();
        for (Set<Operation> ops : operations.values()) {
            set.addAll(ops);
        }
        return set;
    }

    public final Set<Operation> getOperations(String name) {
        Set<Operation> ops = operations.get(name);
        if (ops == null) {
            ops = new HashSet<Operation>();
            operations.put(name, ops);
        }
        return ops;
    }

    public final void setOperations(Collection<? extends Operation> ownedOperations) {
        this.operations.clear();
        for (Operation op : ownedOperations) {
            addOperation(op);
        }
    }

    public final void addOperation(Operation operationToAdd) {
        getOperations(operationToAdd.getName()).add(operationToAdd);
        operationToAdd.setSource(this);
    }

    public final boolean removeOperation(Operation operationToRemove) {
        return getOperations(operationToRemove.getName()).remove(operationToRemove);
    }

    /**
     * 
     * @return The {@link AssociationEnd} by a class. These do not include the inherited association ends.
     */
    public final OrderedSet<AssociationEnd> getOwnedAssociationEnds() {
        return ownedAssociationEnds.clone();
    }

    public final void setOwnedAssociationEnds(Collection<? extends AssociationEnd> ownedAssociationEnds) {
        for (AssociationEnd associationEnd : this.ownedAssociationEnds) {
            associationEnd.setUmlClassPrivate(null);
        }
        this.ownedAssociationEnds = new OrderedSet<AssociationEnd>(ownedAssociationEnds);
        for (AssociationEnd associationEnd : this.ownedAssociationEnds) {
            associationEnd.setUmlClassPrivate(this);
        }
    }

    public final void addOwnedAssociationEnd(AssociationEnd associationEndToAdd) {
        associationEndToAdd.setUmlClass(this);
    }

    protected final void addOwnedAssociationEndPrivate(AssociationEnd associationEndToAdd) {
        ownedAssociationEnds.add(associationEndToAdd);
    }

    public final boolean removeOwnedAssociationEnd(AssociationEnd asso) {
        if (!ownedAssociationEnds.contains(asso)) {
            return false;
        }
        asso.setUmlClass(null);
        return true;
    }
    
    public AssociationEnd lookforAssociationEnd(@Nonnull String name, boolean lookintoSuperclasses) {
        Iterable<AssociationEnd> assos;
        if (lookintoSuperclasses) {
            assos = getAllAssociationEnds();
        }
        else {
            assos = getOwnedAssociationEnds();
        }
        for (AssociationEnd associationEnd : assos) {
            if (associationEnd.getName().equals(name)) {
                return associationEnd;
            }
        }
        return null;
    }

    protected final boolean removeOwnedAssociationEndPrivate(AssociationEnd asso) {
        return ownedAssociationEnds.remove(asso);
    }

    public final Iterable<Property> getOwnedProperties() {
        return Iterables.concat(getOwnedAttributes(), getOwnedAssociationEnds());
    }
    
    /**
     * 
     * @return The {@link Attribute} owned by a class. These do not include the inherited attributes.
     */
    public final OrderedSet<Attribute> getOwnedAttributes() {
        return ownedAttributes.clone();
    }

    public final void setOwnedAttributes(Collection<? extends Attribute> ownedAttributes) {
        for (Attribute attribute : this.getOwnedAttributes()) {
            attribute.setUmlClassPrivate(null);
        }
        this.ownedAttributes = new OrderedSet<Attribute>(ownedAttributes);
        for (Attribute attribute : this.ownedAttributes) {
            attribute.setUmlClassPrivate(this);
        }
    }

    public final void addOwnedAttribute(Attribute attributeToAdd) {
        attributeToAdd.setUmlClass(this);
    }

    protected final void addOwnedAttributePrivate(Attribute attributeToAdd) {
        ownedAttributes.add(attributeToAdd);
    }

    public final boolean removeOwnedAttribute(Attribute att) {
        if (!ownedAttributes.contains(att)) {
            return false;
        }
        att.setUmlClass(null);
        return true;
    }

    protected final boolean removeOwnedAttributePrivate(Attribute att) {
        return ownedAttributes.remove(att);
    }
    
    public Attribute lookforAttribute(@Nonnull String name, boolean lookintoSuperclasses) {
        Iterable<Attribute> attrs;
        if (lookintoSuperclasses) {
            attrs = getAllAttributes();
        }
        else {
            attrs = getOwnedAttributes();
        }
        for (Attribute attribute : attrs) {
            if (attribute.getName().equals(name)) {
                return attribute;
            }
        }
        return null;
    }

    /**
     * 
     * @return The immediate superclasses of a class, from which the class inherits.
     */
    public final Set<UmlClass> getSuperClasses() {
        return (Set<UmlClass>) superClasses.clone();
    }

    public final void setSuperClasses(Collection<? extends UmlClass> superClasses) {
        for (UmlClass umlClass : this.superClasses) {
            umlClass.removeSubClassPrivate(this);
        }
        this.superClasses = new HashSet(superClasses);
        for (UmlClass umlClass : this.superClasses) {
            umlClass.addSubClassPrivate(this);
        }
    }

    public final void addSuperClass(UmlClass superClassToAdd) {
        addSuperClassPrivate(superClassToAdd);
        superClassToAdd.addSubClassPrivate(this);
    }

    private final void addSuperClassPrivate(UmlClass superClassToAdd) {
        superClasses.add(superClassToAdd);
    }

    public final boolean removeSuperClass(UmlClass superClassToRemove) {
        boolean b1 = superClassToRemove.removeSubClassPrivate(this);
        boolean b2 = removeSuperClassPrivate(superClassToRemove);
        assert b1 == b2;
        return b1;
    }

    public final boolean removeSuperClassPrivate(UmlClass superClassToRemove) {
        return superClasses.remove(superClassToRemove);
    }

    public final Set<UmlClass> getSubClasses() {
        return (Set<UmlClass>) subClasses.clone();
    }

    public final void setSubClasses(Collection<? extends UmlClass> subClasses) {
        for (UmlClass umlClass : this.subClasses) {
            umlClass.removeSuperClassPrivate(this);
        }
        this.subClasses = new HashSet<UmlClass>(subClasses);
        for (UmlClass umlClass : this.subClasses) {
            umlClass.addSuperClassPrivate(this);
        }
    }

    public final void addSubClass(UmlClass subClassToAdd) {
        addSubClassPrivate(subClassToAdd);
        subClassToAdd.addSuperClassPrivate(this);
    }

    private final void addSubClassPrivate(UmlClass subClassToAdd) {
        subClasses.add(subClassToAdd);
    }

    public final boolean removeSubClass(UmlClass subClassToRemove) {
        boolean b1 = subClassToRemove.removeSuperClassPrivate(this);
        boolean b2 = removeSubClassPrivate(subClassToRemove);
        assert b1 == b2;
        return b1;
    }

    public final boolean removeSubClassPrivate(UmlClass subClassToRemove) {
        return subClasses.remove(subClassToRemove);
    }

    /**
     * 
     * @return True when a class is abstract. The default value is false.
     */
    public final boolean isAbstract() {
        return _abstract;
    }

    public final void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    @Override
    public final List<Classifier> getSuperClassifiers() {
        UmlClass actualClass = this;
        LinkedList<Classifier> result = new LinkedList<>();
        while (actualClass != null) {
            switch (actualClass.getSuperClasses().size()) {
                case 0:
                    actualClass = null;
                    break;
                case 1:
                    actualClass = actualClass.getSuperClasses().iterator().next();
                    result.addLast(actualClass);
                    break;
                default:
                    throw new IllegalStateException("Only classes in a simple hierarchy can get their "
                            + "superclassifiers and "+ actualClass.getName() + " has " 
                            + actualClass.superClasses.size() + " superclasses.");
            }   
        }
        
        result.addLast(AnyType.getInstance());
        return result;
    }

    @Override
    protected final boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions templates) {
        if (!(conformsTarget instanceof UmlClass)) {
            return false;
        }
        final UmlClass otherClass = (UmlClass) conformsTarget;
        if (otherClass.equals(UmlClass.getGenericInstance())) {
            return true;
        }
        if (this.equals(otherClass)) {
            return true;
        }
        return OclUtils.getAllSuperclasses(this).contains(otherClass);
    }

    public final Iterable<Attribute> getAllAttributes() {
        Set<UmlClass> classes = OclUtils.getAllSuperclasses(UmlClass.this);
        classes.add(UmlClass.this);

        List<Iterable<Attribute>> its = new ArrayList<Iterable<Attribute>>(classes.size());
        for (final UmlClass umlClass : classes) {
            Iterable<Attribute> attIt = new Iterable<Attribute>() {

                @Override
                public Iterator<Attribute> iterator() {
                    return umlClass.ownedAttributes.iterator();
                }
            };

            its.add(attIt);
        }
        return Iterables.concat(its);
    }

    public final Iterable<AssociationEnd> getAllAssociationEnds() {
        Set<UmlClass> classes = OclUtils.getAllSuperclasses(UmlClass.this);
        classes.add(UmlClass.this);

        List<Iterable<AssociationEnd>> its = new ArrayList<Iterable<AssociationEnd>>(classes.size());
        for (final UmlClass umlClass : classes) {
            Iterable<AssociationEnd> attIt = new Iterable<AssociationEnd>() {

                @Override
                public Iterator<AssociationEnd> iterator() {
                    return umlClass.ownedAssociationEnds.iterator();
                }
            };

            its.add(attIt);
        }
        return Iterables.concat(its);
    }
    
    @Override
    @Nonnull
    public List<Element> getTreeChildren() {
        List<Element> result = super.getTreeChildren();
        result.addAll(ownedAssociationEnds);
        result.addAll(ownedAttributes);
        
        assert operations instanceof SortedMap;

        for (Set<Operation> operationsByName : operations.values()) {
            result.addAll(operationsByName);
        }
        return result;
    }
    
    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return classifierEquals((UmlClass) obj);
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }
    
    /**
     * Objects of this class are only clonable if are immutables. In other case RuntimeException is
     * thrown
     * 
     * @throws IllegalStateException if the object is not immutable
     */
    @Override
    public UmlClass clone() {
        return this;
    }
    
    public <Result, Arg> Result accept(UmlVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }
}
