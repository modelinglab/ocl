/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.string;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * Queries the character at position i in self.
 * <p>
 * <code>
 * at(i : Integer) : String </br>
 * pre: i &gt; 0 <br/>
 * pre: i &lt;= self.size() <br/>
 * post: result = self.substring(i, i) <br/>
 * </code>
 * </p>
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class At extends Operation {

    private static final long serialVersionUID = 1L;
    private static final At instance = new At();

    public static At getInstance() {
        return instance;
    }

    private At() {
        super(null);

        Parameter i = new Parameter();
        i.setName("i");
        i.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(i);

        setName("at");
        setType(PrimitiveType.getInstance(PrimitiveKind.STRING));
        setSource(PrimitiveType.getInstance(PrimitiveKind.STRING));
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
