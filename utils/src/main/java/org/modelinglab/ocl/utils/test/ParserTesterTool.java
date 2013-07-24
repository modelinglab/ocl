/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.utils.test;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.UmlClass;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.core.ast.expressions.Variable;
import org.modelinglab.ocl.parser.walker.OclWalker;
import org.modelinglab.ocl.parser.sablecc.lexer.Lexer;
import org.modelinglab.ocl.parser.sablecc.lexer.LexerException;
import org.modelinglab.ocl.parser.sablecc.node.Start;
import org.modelinglab.ocl.parser.sablecc.parser.Parser;
import org.modelinglab.ocl.parser.sablecc.parser.ParserException;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;
import javax.annotation.Nonnull;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class ParserTesterTool {

    private ParserTesterTool() {
    }

    public static void addSelf(StaticEnvironment env, String className) {
        env.addScope();

        Variable self = new Variable();
        self.setName("self");
        UmlClass type = (UmlClass) env.lookup(className);
        assert type != null;
        self.setType(type);

        env.addElement(self, true);
    }

    public static void removeSelf(StaticEnvironment env) {
        env.removeScope();
    }

    public static void testParser(@Nonnull String query, @Nonnull OclExpression expectedExpression, StaticEnvironment env)
            throws ParserException, LexerException, IOException {

        OclExpression result = parse(query, env);
        assert expectedExpression.equals(result) : "Expected and result expression are different:\n"
                + "Result   : " + result.toString() + "\n"
                + "Expected : " + expectedExpression.toString();
    }

    public static OclExpression parse(@Nonnull String query, StaticEnvironment env) throws ParserException, LexerException, IOException {
        Lexer lexer = new Lexer(new PushbackReader(new StringReader(query), query.length()));
        Parser parser = new Parser(lexer);
        Start start = parser.parse();

        OclWalker walker = new OclWalker(env);
        start.apply(walker);

        return walker.getOclAST();
    }
}
