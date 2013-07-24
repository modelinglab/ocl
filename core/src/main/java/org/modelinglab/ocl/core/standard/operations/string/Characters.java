/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.string;

import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.SequenceType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Obtains the characters of self as a sequence.
 * <p>
 * <code>
 * characters() : Sequence(String)<br/>
 * post: result = <br/>
 * &nbsp;&nbsp;if self.size() = 0 then<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;Sequence{}<br/>
 * &nbsp;&nbsp;else<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;Sequence{1..self.size()}->iterate(i; acc : Sequence(String) = Sequence{} |<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;acc->append(self.at(i)))<br/>
 * &nbsp;&nbsp;endif<br/>
 * </code>
 * </p>

 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class Characters extends Operation {
    private static final long serialVersionUID = 1L;
    private static final Characters instance = new Characters();
    
    private Characters() {
        super(null);
        
        setName("characters");
        SequenceType st = new SequenceType();
        st.setElementType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        setType(st);
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
    }
    
    public static Characters getInstance() {
        return instance;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return getInstance();
    }

    @Override
    public Operation specificOperation(Classifier sourceType, java.util.List<Classifier> argTypes, TemplateRestrictions restrictions) {
        return this;
    }
    
}
