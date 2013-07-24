/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.environments;

import org.modelinglab.ocl.core.ast.Namespace;
import org.modelinglab.ocl.core.ast.UmlClass;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public final class StandardNamespace extends Namespace {
    private static final long serialVersionUID = 1L;

    public StandardNamespace() {
        addMember(createDateClass());
        addMember(createFileClass());
    }
    
    private UmlClass createDateClass() {
        UmlClass date = new UmlClass();
        date.setName("Date");
        
        return date;
    }
    
    private UmlClass createFileClass() {
        UmlClass file = new UmlClass();
        file.setName("File");
        
        return file;
    }
}
