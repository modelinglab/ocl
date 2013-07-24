/*
 * Sources from http://sablecc.org/wiki/LexerUnitTests
 */
package org.modelinglab.ocl.utils.test;

import java.io.PushbackReader;
import java.io.StringReader;
import org.modelinglab.ocl.parser.sablecc.lexer.Lexer;

/**
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LexerTesterTool {

    private LexerTesterTool() {
    }

    public static Lexer createLexer(String input) {
        Lexer lex = new Lexer(new PushbackReader(new StringReader(input), input.length()));
        return lex;
    }

    public static String getNextTokenClass(Lexer lex) throws Exception {
        String tclass = lex.next().getClass().getName();
        tclass = tclass.substring(tclass.lastIndexOf('.') + 1);
        return tclass;
    }

    public static void testTokens(String input, String... tokens) throws Exception {
        Lexer lex = createLexer(input);

        for (String tok : tokens) {
            String tclass = getNextTokenClass(lex);
            assert tok.equals(tclass) : "Token types should match (" + tok + " != " + tclass + ")";
        }
    }
}
