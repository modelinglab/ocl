/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast.utils;

import java.io.Serializable;
import org.modelinglab.ocl.core.ast.Element;
import java.util.Comparator;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ElementComparator implements Comparator<Element>, Serializable {

    private static ElementComparator instance;
    private static final long serialVersionUID = 1L;
    
    private ElementComparator() {
        
    }
    
    public synchronized static Comparator<Element> getInstance() {
        if (instance == null) {
            instance = new ElementComparator();
        }
        return instance;
    }
    
    @Override
    public int compare(Element o1, Element o2) {
        return o1.getName().compareTo(o2.getName());
    }
    
}
