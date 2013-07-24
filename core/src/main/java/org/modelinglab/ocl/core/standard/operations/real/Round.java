/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.core.standard.operations.real;

import javax.annotation.concurrent.Immutable;
import org.modelinglab.ocl.core.ast.Operation;
import org.modelinglab.ocl.core.ast.types.Classifier;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.types.TemplateRestrictions;

/**
 * The integer that is closest to self. When there are two such integers, the largest one.
 *
 * <p/>
 * <code>post: ((self - result).abs() < 0.5) or ((self - result).abs() = 0.5 and (result > self))</code>
 */
@Immutable
public class Round extends Operation {

    private static final long serialVersionUID = 1L;
    private static final Round instance = new Round();

    public static Round getInstance() {
        return instance;
    }

    private Round() {
        super(null);

        setName("round");
        setType(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER));
        setSource(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.REAL));
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