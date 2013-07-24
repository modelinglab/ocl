/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.types;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class TemplateRestrictions implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    
    /**
     * instanciatedTemplate.get("T") returns the classifier assigned to "T"
     */
    private Map<String, Classifier> instanciatedTemplates;
    /**
     * if !fixedTemplates.contains("T") then instancetedTemplates.get("T") should be relaxed 
     * to a supertype
     */
    private Set<String> fixedTemplates;

    public TemplateRestrictions() {
        instanciatedTemplates = new HashMap<String, Classifier>();
        fixedTemplates = new HashSet<String>();
    }

    public TemplateRestrictions(Map<String, Classifier> instanciatedTemplates, Set<String> fixedTemplates) {
        this.instanciatedTemplates = new HashMap<String, Classifier>(instanciatedTemplates);
        this.fixedTemplates = new HashSet<String>(fixedTemplates);
    }
    
    public void instantiate(String template, Classifier instance) {
        instantiate(template, instance, true);
    }
    
    public void instantiate(String template, Classifier instance, boolean fixed) {
        instanciatedTemplates.put(template, instance);
        if (fixed) {
            fixedTemplates.add(template);
        }
    }
    
    public Classifier getInstance(String template) {
        return instanciatedTemplates.get(template);
    }
    
    public boolean isFixed(String template) {
        return fixedTemplates.contains(template);
    }

    public void clear() {
        instanciatedTemplates.clear();
        fixedTemplates.clear();
    }

    public boolean contains(String template) {
        return instanciatedTemplates.containsKey(template);
    }
    
    @Override
    public TemplateRestrictions clone() {
        return new TemplateRestrictions(instanciatedTemplates, fixedTemplates);
    }
}
