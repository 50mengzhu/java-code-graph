/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.engine;

import me.mutai.codegraph.antlr.Java8Lexer;
import me.mutai.codegraph.antlr.Java8Parser;
import me.mutai.codegraph.antlr.biz.SimpleClassListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public class AnalyzeEngineImpl implements AnalyzeEngine {

    public void preHandle() {
    }

    public void analyze(String code) {

        Java8Lexer lexer = new Java8Lexer(CharStreams.fromString(code));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();
        ParseTreeWalker walker = new ParseTreeWalker();

        SimpleClassListener listener = new SimpleClassListener();
        walker.walk(listener, tree);

        Java8Parser.StatementContext statement = parser.statement();

        System.out.println(statement.toStringTree());
    }

    public void postHandle() {

    }
}
