/*
 * Sources from http://sablecc.org/wiki/LexerUnitTests
 */
package org.modelinglab.ocl.parser;

import java.io.PushbackReader;
import org.modelinglab.ocl.parser.sablecc.lexer.Lexer;
import java.io.StringReader;

/**
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class LexerTesterTool {

    private LexerTesterTool() {
    }

    public static Lexer createLexer(String input) {
        /*
         * This code should fail if sablecc grammar is regenerated. You should change 
         * org.modelinglab.ocl.parser.sablecc.lexer.Lexer to allow PushbackReaderWrapper as
         * input instead of PushbackReader!
         */
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
