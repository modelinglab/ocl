/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments.java;

import com.google.common.base.Preconditions;
import java.util.Set;
import org.modelinglab.ocl.utils.environments.StandardNamespace;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class JavaToUmlTaskContext {

    private StandardNamespace standardNS;
    private Set<Class> classesToMap;
    private JavaToUmlFilter filter;
    
    public void validate(JavaToUmlTask task) {
        Preconditions.checkArgument(task != null, "Task must be not null.");
        Preconditions.checkArgument(task instanceof JavaToUmlTask, 
                "Task must be instance of " + JavaToUmlTask.class + ".");
        
        Preconditions.checkState(classesToMap != null, "classesToMap must be not null.");
        Preconditions.checkState(standardNS != null, "standardNS must be not null.");
        
        if (filter == null) {
            filter = new JavaToUmlFilter.AcceptAll();
        }
    }

    public JavaToUmlFilter getFilter() {
        return filter;
    }

    public void setFilter(JavaToUmlFilter filter) {
        this.filter = filter;
    }

    public StandardNamespace getStandardNS() {
        return standardNS;
    }

    public void setStandardNS(StandardNamespace standardNS) {
        this.standardNS = standardNS;
    }

    public Set<Class> getClassesToMap() {
        return classesToMap;
    }

    public void setClassesToMap(Set<Class> classesToMap) {
        this.classesToMap = classesToMap;
    }
    
    
    
}
