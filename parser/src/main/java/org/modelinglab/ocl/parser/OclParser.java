/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.modelinglab.ocl.parser;

import org.modelinglab.ocl.core.ast.StaticEnvironment;
import org.modelinglab.ocl.core.ast.expressions.OclExpression;
import org.modelinglab.ocl.parser.sablecc.lexer.Lexer;
import org.modelinglab.ocl.parser.sablecc.lexer.LexerException;
import org.modelinglab.ocl.parser.sablecc.node.Start;
import org.modelinglab.ocl.parser.sablecc.parser.Parser;
import org.modelinglab.ocl.parser.sablecc.parser.ParserException;
import org.modelinglab.ocl.parser.walker.OclWalker;
import java.io.IOException;
import java.io.PushbackReader;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;
import org.modelinglab.ocl.core.ast.utils.OclAnalysis;
import org.modelinglab.ocl.core.exceptions.OclException;

/**
 *
 * @author Gonzalo Ortiz Jaureguizar (gortiz at software.imdea.org)
 */
public class OclParser implements Serializable {

    private static final long serialVersionUID = 1L;
    private StaticEnvironment env;
    private List<OclAnalysis> extraAnalysis;

    public OclParser() {
        extraAnalysis = new LinkedList<>();
    }

    public OclParser(StaticEnvironment env) {
        this.env = env;
        extraAnalysis = new LinkedList<>();
    }

    public StaticEnvironment getEnv() {
        return env;
    }

    public void setEnv(StaticEnvironment env) {
        this.env = env;
    }
    
    public List<OclAnalysis> getExtraAnalysis() {
        return Collections.unmodifiableList(extraAnalysis);
    }
    
    public void addAnalysis(OclAnalysis analysis) {
        extraAnalysis.add(analysis);
    }
    
    public void removeAnalysis(OclAnalysis analysis) {
        extraAnalysis.remove(analysis);
    }

    @Nonnull
    public OclExpression parse(@Nonnull String query) throws OclParserException, OclLexerException, IOException, OclException {
        PushbackReader reader = new PushbackReader(new StringReader(query), query.length());
        return parse(reader);
    }
    
    @Nonnull
    public OclExpression parse(@Nonnull PushbackReader reader) throws OclParserException, OclLexerException, IOException, OclException {
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        Start start;
        try {
            start = parser.parse();
        } catch (ParserException ex) {
            throw new OclParserException(ex);
        } catch (LexerException ex) {
            throw new OclLexerException(ex);
        }
        
        OclWalker walker = new OclWalker(env);
        
        int numberOfScopes = env.getNumberOfScopes();
        try {
            start.apply(walker);
            assert numberOfScopes == env.getNumberOfScopes();
        }
        finally {
            assert numberOfScopes <= env.getNumberOfScopes();
            while (numberOfScopes < env.getNumberOfScopes()) {
                env.removeScope();
            }
        }
        
        OclExpression exp = walker.getOclAST();
        postParser(exp);
        
        return exp;
    }
    
    private void postParser(OclExpression exp) throws OclException {
        for (OclAnalysis oclAnalysis : extraAnalysis) {
            try {
                oclAnalysis.analyze(exp);
            } catch (Exception ex) {
                throw new OclException(ex);
            }
        }
    }
}
