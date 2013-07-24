/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.integer;

import org.modelinglab.ocl.core.ast.Parameter;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.PrimitiveType.PrimitiveKind;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;
import javax.annotation.concurrent.Immutable;

/**
 * The number of times that i fits completely within self.
 * <p>
 * <code>
 * div( i : Integer) : Integer<br/>
 * pre : i &lt;&gt; 0<br/>
 * post: if self / i >= 0 then result = (self / i).floor()<br/>
 * else result = -((-self/i).floor())<br/>
 * endif<br/>
 * 
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
@Immutable
public class IntegerDivision extends Operation {

    private static final long serialVersionUID = 1L;
    private static final IntegerDivision instance = new IntegerDivision();

    public static IntegerDivision getInstance() {
        return instance;
    }

    private IntegerDivision() {
        super(null);

        Parameter i = new Parameter();
        i.setName("i");
        i.setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        addOwnedParameter(i);

        setName("div");
        setType(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
        setSource(PrimitiveType.getInstance(PrimitiveKind.INTEGER));
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
