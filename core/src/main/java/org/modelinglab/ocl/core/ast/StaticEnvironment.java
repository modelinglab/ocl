/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.*;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.expressions.IteratorExp;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.core.ast.types.AnyType;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.CollectionType;
import org.modelinglab.ocl.core.ast.types.InvalidType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import org.modelinglab.ocl.core.ast.types.VoidType;
import org.modelinglab.ocl.core.ast.utils.OclCopier;
import org.modelinglab.ocl.core.exceptions.AmbiguosOperationCall;
import org.modelinglab.ocl.core.exceptions.OclRuntimeException;
import org.modelinglab.ocl.core.standard.OclStandardIterators;
import org.modelinglab.ocl.core.standard.iterators.exceptions.IllegalIteratorException;

/**
 *
 * @author miguelgd
 */
public final class StaticEnvironment implements Serializable {

    private static final Namespace standardNamespace;
    private static final long serialVersionUID = 1L;
    private final LinkedList<Namespace> namespaces;
    private final OperationsStore opStore;
    private boolean immutable = false;

    static {
        standardNamespace = new Namespace();
        standardNamespace.addMember(AnyType.getInstance());
        standardNamespace.addMember(UmlClass.getGenericInstance());
        standardNamespace.addMember(UmlClass.getGenericInstance().getClassifierType());
        standardNamespace.addMember(UmlEnum.getGenericInstance());
        standardNamespace.addMember(UmlEnum.getGenericInstance().getClassifierType());
        standardNamespace.addMember(InvalidType.getInstance());
        standardNamespace.addMember(VoidType.getInstance());
        standardNamespace.addMember(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.BOOLEAN));
        standardNamespace.addMember(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        standardNamespace.addMember(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
        standardNamespace.addMember(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.STRING));
        standardNamespace.addMember(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.UNLIMITED_NATURAL));
    }

    public StaticEnvironment(OperationsStore opStore) {
        this(opStore, new Namespace(), true);
    }
    
    public StaticEnvironment(OperationsStore opStore, Namespace rootNamespace) {
        this(opStore, rootNamespace, true);
    }

    public StaticEnvironment(OperationsStore opStore, Namespace rootNamespace, boolean addStandard) {
        Preconditions.checkArgument(rootNamespace != null, "The root namespace must be not null");
        namespaces = new LinkedList<Namespace>();
        if (addStandard) {
            namespaces.add(standardNamespace);
        }
        namespaces.add(rootNamespace);

        this.opStore = opStore;
    }

    public StaticEnvironment(StaticEnvironment originalEnv, Map<Element, Element> copyMap,
            boolean mapAnnotations, boolean cloneAnnotations) {
        this.opStore = originalEnv.opStore;
        namespaces = new LinkedList<Namespace>();

        OclCopier.Argument arg = new OclCopier.Argument(copyMap, mapAnnotations, cloneAnnotations);
        OclCopier copier = new OclCopier();

        for (Namespace namespace : originalEnv.namespaces) {
            Namespace namespaceCopy = (Namespace) namespace.accept(copier, arg);
            namespaces.addLast(namespaceCopy);
        }
    }

    /**
     * Creates a new StaticEnvironment with the same OperationsStore and the same namespaces that the
     * original. You can add or remove namespaces in this new StaticEnvironment without modify the original
     * one. On the other hand, original namespaces are shared, so if one of them is modified (for example, if
     * a variable is declared), changes are visible in both environments
     *
     * @param env
     */
    public StaticEnvironment(StaticEnvironment env) {
        this.opStore = env.opStore;
        this.namespaces = new LinkedList<>(env.namespaces);
    }
    
    /**
     * Simmilar to {@link #StaticEnvironment(org.modelinglab.ocl.core.ast.StaticEnvironment) } but with another
     * operation store.
     * 
     * @param env
     * @param opStore 
     */
    public StaticEnvironment(StaticEnvironment env, OperationsStore opStore) {
        this.namespaces = new LinkedList<>(env.namespaces);
        this.opStore = opStore;
    }

    public OperationsStore getOpStore() {
        return opStore;
    }

    public Set<UmlClass> getAllClasses() {
        HashSet<UmlClass> result = new HashSet<>();
        for (final Element element : this.getOwnedMembers()) {
            if (element instanceof UmlClass) {
                result.add((UmlClass) element);
            }
        }
        return result;
    }

    public Set<Element> getOwnedMembers() {
        List<Set<Element>> ownedMembersByNamespace = new ArrayList<Set<Element>>(namespaces.size());
        int totalMembers = 0;
        for (Namespace namespace : namespaces) {
            Set<Element> ownedMembers = namespace.getOwnedMembers();
            ownedMembersByNamespace.add(ownedMembers);
            totalMembers += ownedMembers.size();
        }
        Set<Element> result = new HashSet<Element>(totalMembers);
        for (Set<Element> set : ownedMembersByNamespace) {
            result.addAll(set);
        }
        return result;
    }

    /**
     *
     * Find a named element in the current environment, not in its parents, based on a single name.
     *
     * @param name the name of the element we are looking for
     *
     * @return the element or null if there is no element with that name
     */
    public Element lookupLocal(String name) {
        return namespaces.getLast().lookup(name);
    }

    /**
     *
     * Find a named element in the current environment or recursively in its parent environment, based on a
     * single name. <p> The </p>
     *
     * @param name the name of the element we are looking for
     *
     * @return the first element with that name or null if there is no element with that name
     */
    public Element lookup(String name) {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            Element e = it.next().lookup(name);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    /**
     *
     * Find a named element in the current environment or recursively in its parent environment, based on a
     * path name.
     *
     * @param names
     *
     * @return a Variable, Classifier or EnumLiteral referenced by this name or null if there is no element
     *         with this id. Properties (attributes and associationEnds) are not explored
     */
    public Element lookupPathName(@Nonnull List<String> names) {
        Iterator<String> nameIterator = names.iterator();

        if (!nameIterator.hasNext()) {
            throw new IllegalArgumentException("Names is empty");
        }

        Element element = lookup(nameIterator.next());
        if (element == null) {
            return null;
        }

        if (element instanceof Namespace && nameIterator.hasNext()) {
            Namespace ns;
            ns = (Namespace) element;

            element = ns.lookup(nameIterator.next());
        }
        if (element instanceof Namespace) { //namespaces are not valid as result
            assert !nameIterator.hasNext();
            return null;
        }
        if (!nameIterator.hasNext()) { //it has to be a variable or classifier
            assert element instanceof Variable || element instanceof Classifier;
            return element;
        }
        assert nameIterator.hasNext();
        if (!(element instanceof UmlEnum)) { //it has to be a enum
            return null;
        }
        UmlEnum enumClass = (UmlEnum) element;
        String literalName = nameIterator.next();
        
        if (nameIterator.hasNext()) { //the literal has to be the last element of the path
            return null;
        }
        for (final UmlEnumLiteral umlEnumLiteral : enumClass.getLiterals()) {
            if (umlEnumLiteral.getName().equals(literalName)) {
                return umlEnumLiteral;
            }
        }
        return null; //there is no literal with that path
    }

    /**
     * Add a new explicit named element to the environment.
     * 
     * @param elem 
     * @see #addElement(org.modelinglab.ocl.core.ast.Element, boolean) 
     */
    public void addElement(Element elem) {
        addElement(elem, false);
    }
    
    /**
     *
     * Add a new named element to the environment.
     *
     * @param elem
     * @param implicit
     */
    public void addElement(Element elem, boolean implicit) {
        modifyAttempt();
        for (Namespace namespace : namespaces) {
            if (namespace.getOwnedMembers().contains(elem)) {
                throw new OclRuntimeException("There is another element with '" + elem.getName() + "' name in this environment.");
            }
        }
        namespaces.getLast().addMember(elem, implicit);
    }

    /**
     *
     * This function replace ocl environment operation "nestedEnvironment". It adds a new namespace into
     * namespaces stack.
     */
    public void addScope() {
        modifyAttempt();
        Namespace ns = new Namespace();
        ns.setName("_primaryNameSpace" + namespaces.size());
        namespaces.getLast().addMember(ns);
        namespaces.add(ns);
    }

    /**
     * this function is used to remove the last namespace from namespaces stack.
     */
    public void removeScope() {
        modifyAttempt();
        Preconditions.checkState(namespaces.size() > 1, "The root namespace is not removeable.");
        Namespace ns = namespaces.removeLast();
        namespaces.getLast().removeMember(ns);
    }

    public int getNumberOfScopes() {
        return namespaces.size();
    }

    public boolean isIterator(CollectionType sourceType, String name) {
        return OclStandardIterators.getInstance().isIterator(sourceType, name);
    }

    public IteratorExp lookupIterator(CollectionType sourceType, String name, Classifier bodyType) throws IllegalIteratorException {
        return OclStandardIterators.getInstance().getIteratorExp(sourceType, name, bodyType);
    }

    /**
     * @param name
     *
     * @return an implicit TypedElement with type UmlClass. This class contains an attribute with the specific
     *         name. This returned value is null if there is no element that matches this condition
     */
    public TypedElement findImplicitSourceForAttribute(@Nonnull String name) {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            TypedElement te = it.next().findImplicitSourceForAttribute(name);
            if (te != null) {
                return te;
            }
        }
        return null;
    }

    /**
     *
     * Lookup a given attribute name of an implicitly named element in the current environment, including its
     * parents.
     *
     * @param name
     *
     * @return
     */
    public Attribute lookupImplicitAttribute(String name) {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            Attribute e = it.next().lookupImplicitAttribute(name);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    /**
     * @param name
     *
     * @return an implicit TypedElement with type UmlClass. This class contains an association end with the
     *         specific name. This returned value is null if there is no element that matches this condition
     */
    public TypedElement findImplicitSourceForAssociationEnd(@Nonnull String name) {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            TypedElement te = it.next().findImplicitSourceForAssociationEnd(name);
            if (te != null) {
                return te;
            }
        }
        return null;
    }

    /**
     *
     * Lookup a given association end name of an implicitly named element in the current environment,
     * including its parents.
     *
     * @param name
     *
     * @return
     */
    public AssociationEnd lookupImplicitAssociationEnd(String name) {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            AssociationEnd e = it.next().lookupImplicitAssociationEnd(name);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    /**
     * TODO: this method returns any valid element, it should be check that there is only one valid element!
     *
     * @param name
     * @param params
     *
     * @return an implicit TypedElement with type UmlClass. This class contains an operation with the name and
     *         arguments. This returned value is null if there is no element that matches this condition
     *
     * @throws AmbiguosOperationCall
     */
    public TypedElement findImplicitSourceForOperation(@Nonnull String name, List<Classifier> params) throws AmbiguosOperationCall {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            TypedElement te = it.next().findImplicitSourceForOperation(name, params, this);
            if (te != null) {
                return te;
            }
        }
        return null;
    }

    /**
     *
     * Lookup an operation of an implicitly named element with given name and parameter types in the current
     * environment, including its parents.
     *
     * @param name
     * @param params
     *
     * @return
     *
     * @throws AmbiguosOperationCall
     */
    public Operation lookupImplicitOperation(String name, List<Classifier> params) throws AmbiguosOperationCall {
        for (Iterator<Namespace> it = namespaces.descendingIterator(); it.hasNext();) {
            Operation e = it.next().lookupImplicitOperation(name, params, this);
            if (e != null) {
                return e;
            }
        }
        return null;
    }

    public Operation lookupOperation(TypedElement source, String operationName, List<Classifier> argumentTypes) throws AmbiguosOperationCall {
        return lookupOperation(source.getType(), operationName, argumentTypes);
    }

    public Operation lookupOperation(Classifier sourceType, String operationName,
            List<Classifier> argumentTypes) throws AmbiguosOperationCall {
        Iterator<Operation> opIterator = opStore.getOperations(sourceType, operationName);
        TemplateRestrictions restrictions = new TemplateRestrictions();
        Operation result = null;

        foreachOperation: //it is used to go to this block in continue statments
        while (opIterator.hasNext()) {
            Operation op = opIterator.next();
            restrictions.clear();
            sourceType.fixTemplates(restrictions);

            final int paramSize = op.getOwnedParameters().size();
            if (paramSize != argumentTypes.size()) { //this operation has another number of arguments
                continue foreachOperation;
            }
            for (int i = 0; i < paramSize; i++) {
                Classifier parameterType = op.getOwnedParameters().get(i).getType();
                Classifier argumentType = argumentTypes.get(i);

                if (!argumentType.conformsTo(parameterType, restrictions)) { //types do not conforms
                    continue foreachOperation; //goes to the next operation
                }
            }

            //Check oclAsType case! It is neccesary to pass real arguments to specific operation! Maybe we can postpone the specificOperacion call and return an abstract operation!
            if (result != null) {
                op = op.specificOperation(sourceType, argumentTypes, restrictions);
                result = getOverridingOperation(result, op, argumentTypes, restrictions, sourceType);
                if (result == op) {
                    result = result.specificOperation(sourceType, argumentTypes, restrictions);
                }
            } else {
                result = op.specificOperation(sourceType, argumentTypes, restrictions);
            }
        }

        return result;
    }

    public static Operation getOverridingOperation(
            Operation op1,
            Operation op2,
            List<Classifier> argumentTypes,
            TemplateRestrictions restrictions,
            Classifier originalSource) throws AmbiguosOperationCall {

        assert op1 != null || op2 != null :
                "At least one of the operations must be not null";
        if (op1 == null) {
            return op2;
        }
        if (op2 == null) {
            return op1;
        }
        if (op1.getSource().equals(op2.getSource())) {
            throw new AmbiguosOperationCall(originalSource, op1.getName(), argumentTypes, Arrays.
                    asList(new Operation[] {
                        op1, op2
                    }));
        }

        if (op1.overrides(op2, restrictions)) {
            return op1;
        }
        if (op2.overrides(op1, restrictions)) {
            return op2;
        }
        throw new AmbiguosOperationCall(originalSource, op1.getName(), argumentTypes, Arrays.
                asList(new Operation[] {
                    op1, op2
                }));
    }

    protected void modifyAttempt() {
        if (immutable) {
            throw new IllegalStateException("This class is inmmutable.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaticEnvironment other = (StaticEnvironment) obj;
        if (this.namespaces != other.namespaces && (this.namespaces == null || !this.namespaces.equals(
                other.namespaces))) {
            return false;
        }
        if (this.opStore != other.opStore && (this.opStore == null || !this.opStore.equals(other.opStore))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.namespaces != null ? this.namespaces.size() : 0);
        hash = 29 * hash + (this.opStore != null ? this.opStore.hashCode() : 0);
        return hash;
    }
}