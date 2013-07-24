/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.evaluator.evaluators;

import java.util.*;
import org.modelinglab.ocl.core.ast.expressions.*;
import org.modelinglab.ocl.core.ast.types.PrimitiveType;
import org.modelinglab.ocl.core.ast.utils.CollectionLiteralPartVisitor;
import org.modelinglab.ocl.core.ast.utils.OrderedSet;
import org.modelinglab.ocl.core.exceptions.IllegalOclExpression;
import org.modelinglab.ocl.core.exceptions.OclEvaluationException;
import org.modelinglab.ocl.core.values.*;
import org.modelinglab.ocl.evaluator.EvaluatorVisitorArg;
import org.modelinglab.ocl.evaluator.walker.ExpressionWalker;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LiteralExpEval {

    private LiteralExpEval() {
    }

    public static LiteralExpEval getInstance() {
        return LiteralExpEvalHolder.INSTANCE;
    }

    public OclValue<?> evaluate(BooleanLiteralExp exp, EvaluatorVisitorArg env) {
        if (exp.getValue()) {
            return BooleanValue.TRUE;
        }
        return BooleanValue.FALSE;
    }

    public OclValue<?> evaluate(IntegerLiteralExp exp, EvaluatorVisitorArg env) {
        IntegerValue result = new IntegerValue(exp.getValue());
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(InvalidLiteralExp exp, EvaluatorVisitorArg env) {
        InvalidValue result = InvalidValue.instantiate();
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(NullLiteralExp exp, EvaluatorVisitorArg env) {
        VoidValue result = VoidValue.instantiate();
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(RealLiteralExp exp, EvaluatorVisitorArg env) {
        RealValue result = new RealValue(exp.getValue());
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(StringLiteralExp exp, EvaluatorVisitorArg env) {
        StringValue result = new StringValue(exp.getValue());
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(UnlimitedNaturalExp exp, EvaluatorVisitorArg env) {
        return new NaturalValue(exp.getValue());
    }

    public OclValue<?> evaluate(TupleLiteralExp exp, EvaluatorVisitorArg env) throws OclEvaluationException {
        Map<String, OclValue<?>> attributes = new HashMap<>(exp.getParts().size());
        for (TupleLiteralPart part : exp.getParts()) {
            OclExpression partExp = part.getAttribute().getInitExpression();
            if (partExp == null) {
                throw new OclEvaluationException(exp, part + ", which is a tuple part, has no init expression!");
            }
            Object o = attributes.put(part.getName(), partExp.accept(ExpressionWalker.getInstance(), env));
            assert o == null;
            assert attributes.get(part.getName()) != null;
        }
        TupleValue result = new TupleValue(attributes);
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(CollectionLiteralExp exp, final EvaluatorVisitorArg env) throws OclEvaluationException {
        List<OclValue<?>> collection;
        switch (exp.getKind()) {
            case BAG:
                collection = new ArrayList<>(exp.getParts().size());
                break;
            case ORDERED_SET:
                collection = new OrderedSet<>(exp.getParts().size());
                break;
            case SEQUENCE:
                collection = new ArrayList<>(exp.getParts().size());
                break;
            case SET:
                collection = new OrderedSet<>(exp.getParts().size());
                break;
            default:
            case COLLECTION:
                throw new OclEvaluationException(exp, "Collection literals must be of type Bag, "
                        + "OrderedSet, Sequence or Set.");
        }

        MyCollectionLiteralPartVisitor literalPartVisitor = new MyCollectionLiteralPartVisitor();

        for (CollectionLiteralPart part : exp.getParts()) {
            collection.addAll(part.accept(literalPartVisitor, env));
        }

        OclValue<?> result;
        switch (exp.getKind()) {
            case BAG:
                result = new BagValue<>(collection, exp.getType().getElementType(), true);
                break;
            case ORDERED_SET:
                result = new OrderedSetValue<>(collection, exp.getType().getElementType(), true);
                break;
            case SEQUENCE:
                result = new SequenceValue<>(collection, exp.getType().getElementType(), true);
                break;
            case SET:
                result = new SetValue<>(collection, exp.getType().getElementType(), true);
                break;
            default:
            case COLLECTION:
                throw new OclEvaluationException(exp, "Collection literals must be of type Bag, "
                        + "OrderedSet, Sequence or Set.");
        }

        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    public OclValue<?> evaluate(EnumLiteralExp exp, EvaluatorVisitorArg env) {
        EnumValue result = new EnumValue(exp.getEnumerationLiteral());
        env.getUserEvalEnv().getAnalyzer().analyze(exp, result);
        return result;
    }

    // This method is called immediately after an object of this class is deserialized.
    // This method returns the singleton instance.
    protected Object readResolve() {
        return LiteralExpEval.getInstance();
    }

    private static class LiteralExpEvalHolder {

        private static final LiteralExpEval INSTANCE = new LiteralExpEval();

        private LiteralExpEvalHolder() {
        }
    }

    private static class MyCollectionLiteralPartVisitor implements CollectionLiteralPartVisitor<List<? extends OclValue<?>>, EvaluatorVisitorArg> {

        @Override
        public List<? extends OclValue<?>> visit(CollectionItem item, EvaluatorVisitorArg runtimeEnv) {
            OclValue<?> val = item.getItem().accept(ExpressionWalker.getInstance(), runtimeEnv);
            return Collections.singletonList(val);
        }

        @Override
        public List<? extends OclValue<?>> visit(CollectionRange range, EvaluatorVisitorArg runtimeEnv) {
            OclValue<?> firstValue = range.getFirst().accept(ExpressionWalker.getInstance(), runtimeEnv);
            OclValue<?> lastValue = range.getLast().accept(ExpressionWalker.getInstance(), runtimeEnv);

            if (!firstValue.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))) {
                if (firstValue.getType().oclIsUndefined()) {
                    return Collections.singletonList(InvalidValue.instantiate());
                }
                IllegalOclExpression error = new IllegalOclExpression(
                        range,
                        "It was expected that " + firstValue + ", as first element of a range, was a integer.");
                throw new AssertionError(error.getMessage(), error);

            }
            if (!lastValue.getType().equals(PrimitiveType.getInstance(PrimitiveType.PrimitiveKind.INTEGER))) {
                if (lastValue.getType().oclIsUndefined()) {
                    return Collections.singletonList(InvalidValue.instantiate());
                }
                IllegalOclExpression error = new IllegalOclExpression(
                        range,
                        "It was expected that " + lastValue + ", as last element of a range, was a integer.");
                throw new AssertionError(error.getMessage(), error);
            }
            int first = ((Number) firstValue.getValue()).intValue();
            int last = ((Number) lastValue.getValue()).intValue();

            if (first > last) {
                return Collections.singletonList(InvalidValue.instantiate());
            }

            ArrayList<IntegerValue> ints = new ArrayList<>(last - first + 1);

            for (long i = first; i <= last; i++) {
                ints.add(new IntegerValue(i));
            }
            return ints;
        }
    }
}
