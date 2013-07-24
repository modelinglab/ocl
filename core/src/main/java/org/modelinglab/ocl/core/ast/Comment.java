/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.ast;

import java.util.Collection;
import java.util.Set;
import org.modelinglab.ocl.core.ast.utils.Visitable;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public interface Comment extends Visitable {
    
    /**
     * 
     * @return Specifies a string that is the comment.
     */
    public String getBody();
    
    public void setBody(String body);
    
    public Set<? extends Element> getAnnotatedElements();
    
    public void setAnnotatedElements(Collection<? extends Element> annotatedElements);
    
    public void addAnnotatedElement(Element elementToAdd);
    
    public boolean removeAnnotatedElement(Element elementToRemove);
}
