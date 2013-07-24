/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import org.modelinglab.ocl.core.ast.utils.ClassifierVisitor;
import org.modelinglab.ocl.core.ast.utils.OclVisitor;
import java.util.*;
import javax.annotation.Nullable;

/**
 * TupleType (informally known as record type or struct) combines different types into a single 
 * aggregate type. The parts of a TupleType are described by its attributes, each having a name and 
 * a type. There is no restriction on the kind of types that can be used as part of a tuple. 
 * In particular, a TupleType may contain other tuple types and collection types. Each attribute
 * of a TupleType represents a single feature of a TupleType. Each part is uniquely identified by 
 * its name.
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TupleType extends DataType {
    private static final long serialVersionUID = 1L;

    final HashMap<String,Classifier> attributes;
    
    private static TupleType genericInstance = null;
    
    public TupleType() {
        attributes = new HashMap<String, Classifier>();
    }

    private TupleType(TupleType other) {
        super(other);
        attributes = new HashMap<String, Classifier>(other.attributes);
    }
    
    public static TupleType getGenericInstance() {
        if (genericInstance == null) {
            genericInstance = new TupleType();
        }
        return genericInstance;        
    }

    @Override
    public ClassifierType getClassifierType() {
        return new ClassifierType(this);
    }
    
    /**
     * TODO: This method is not complete!
     * @return 
     */
    @Override
    public List<Classifier> getSuperClassifiers() {
        List<Classifier> result = new ArrayList<Classifier>(1);
        result.add(AnyType.getInstance());
        return result;
    }

    @Override
    public TupleType getRestrictedType(TemplateRestrictions restrictions) {
        
        TupleType result = new TupleType(this);
        
        for (String tupleAttId : attributes.keySet()) {
            if (attributes.get(tupleAttId) instanceof TemplateParameterType) {
                final TemplateParameterType template = (TemplateParameterType) attributes.get(tupleAttId);
                Classifier newType = restrictions.getInstance(template.getSpecification());
                
                if (newType != null) {
                    result.attributes.put(tupleAttId, newType);
                }
            }
        }        
        return result;
    }

    /**
     * Tuple types conform to each other when their names and types conform to each other. Note that 
     * allProperties is an additional operation in the UML.
     * 
     * <br/>
     * <code>
     * 
     * context TupleType <br/>
     * inv: TupleType.allInstances()->forAll (t |<br/>
     *     ( t.allProperties()->forAll (tp |</br>
     *          -- make sure at least one tuplepart has the same name<br/>
     *          -- (uniqueness of tuplepart names will ensure that not two<br/>
     *          -- tupleparts have the same name within one tuple)</br>
     *          self.allProperties()->exists(stp|stp.name = tp.name) and</br>
     *          -- make sure that all tupleparts with the same name conforms.</br>
     *          self.allProperties()->forAll(stp | (stp.name = tp.name) implies</br>
     *          stp.type.conformsTo(tp.type))</br>
     *      )<br/>
     *      implies<br/>
     *          self.conformsTo(t)</br>
     *      )<br/>
     * </code>
     * @param conformsTarget
     * @return  
     */
    @Override
    protected boolean conformsToProtected(Classifier conformsTarget, TemplateRestrictions instanciatedTemplates) {
        if (!(conformsTarget instanceof TupleType)) {
            return false;
        }
        final TupleType otherTuple = (TupleType) conformsTarget;
        
        for (String otherAttType : otherTuple.attributes.keySet()) {
            Classifier thisAttType = this.attributes.get(otherAttType);
            if (thisAttType == null) {
                return false;
            }
            if (!thisAttType.conformsTo(otherTuple.attributes.get(otherAttType))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TupleType other = (TupleType) obj;
        if (this.attributes != other.attributes && (this.attributes == null || !this.attributes.equals(other.attributes))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.attributes != null ? this.attributes.hashCode() : 0);
        return hash;
    }

    @Override
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tuple(");
        for (String attId : attributes.keySet()) {
            sb.append(attId.toString()).append(':').append(attributes.get(attId)).append(',');
        }
        sb.replace(sb.length() - 1, sb.length(), ")");
        return sb.toString();
    }

    public Map<String, Classifier> getAllAttributes() {
        return (Map<String, Classifier>) attributes.clone();
    }

    public void addAttribute(String attributeId, Classifier attributeType) {
        attributes.put(attributeId, attributeType);
    }

    public boolean removeAttribute(String attributeIdToRemove) {
        return attributes.remove(attributeIdToRemove) != null;
    }

    public void setAllAttributes(Map<? extends String, ? extends Classifier> newAttributes) {
        attributes.clear();
        attributes.putAll(newAttributes);
    }

    @Nullable
    public Classifier getAttributeType(String tupleAttributeId) {
        return attributes.get(tupleAttributeId);
    }

    @Override
    public <Result, Arg> Result accept(ClassifierVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public <Result, Arg> Result accept(OclVisitor<Result, Arg> visitor, Arg arguments) {
        return visitor.visit(this, arguments);
    }

    @Override
    public TupleType clone() {
        return new TupleType(this);
    }
}
