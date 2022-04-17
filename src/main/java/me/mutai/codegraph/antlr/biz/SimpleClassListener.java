/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.antlr.biz;

import me.mutai.codegraph.antlr.Java8Parser;
import me.mutai.codegraph.antlr.Java8ParserBaseListener;
import me.mutai.codegraph.model.CodeBlockNode;
import me.mutai.codegraph.model.CodeNode;
import me.mutai.codegraph.model.IfCodeBlock;
import me.mutai.codegraph.util.LocalCache;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @author daixiao
 * @version v 0.1 2022/4/16
 */
public class SimpleClassListener extends Java8ParserBaseListener {

    /**
     * 记录当前词法分析的方法名
     */
    private volatile String currentMethod;

    /**
     * if-else 块的时间戳，用于判断不同的判断条件的时效
     */
    private volatile Long timestamp;

    /**
     * 判断是否第一次进入 if代码块
     */
    private volatile boolean firstEnterIfTag = true;

    /**
     * 是否存在前置 else
     */
    private volatile boolean hasPreElse = false;


    private List<CodeBlockNode> codeBlockNodes = new ArrayList<>();
    private CodeBlockNode currentCodeNode;
    private volatile String currentClazz;


    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {

        Java8Parser.MethodBodyContext bodyContext = ctx.methodBody();
        String text = bodyContext.getText();
        System.out.println("[enter]获取方法体内容" + text);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println("[exit]退出方法体匹配");
    }


    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {

        TerminalNode node = ctx.Identifier();
        currentMethod = node.getText();
        System.out.println("[enter]进入方法定义");


        String cacheKey = StringUtils.join(Arrays.asList(currentClazz, currentMethod), "#");
        currentCodeNode = (CodeBlockNode) LocalCache.computeIfAbsent(cacheKey, t -> {
            CodeBlockNode blockNode = new CodeBlockNode();
            blockNode.setMethod(currentMethod);
            blockNode.setClazz(currentClazz);
            return blockNode;
        });

        codeBlockNodes.add(currentCodeNode);
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {

        System.out.println("[enter]匹配单 if模式");
        dealIfStatement(ctx.expression().getText(), null);
    }


    @Override
    public void exitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        timestamp = null;
        firstEnterIfTag = true;
        hasPreElse = false;
        System.out.println("[exit]退出匹配 if模式");
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {

        System.out.println("[enter]匹配 if-else模式");

        dealIfStatement(ctx.expression().getText(), null);
        if (ctx.ELSE().getSymbol().getTokenIndex() != -1
                && ctx.ELSE().getSymbol().getLine() != -1) {
            hasPreElse = true;
        }
    }

    @Override
    public void exitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {

        // TODO 暂且放在这里
        if (hasPreElse) {
            dealElseStatement(null);
        }

        timestamp = null;
        firstEnterIfTag = true;
        hasPreElse = false;
        System.out.println("[exit]退出匹配 if-else模式");
    }


    @Override
    public void enterStatement(Java8Parser.StatementContext ctx) {

        /*if (careStatementList.contains(currentState)) {
            System.out.println("语句" + ctx.getText());
        }*/

        System.out.println("[enter]获取语句");
    }


    @Override
    public void enterReturnStatement(Java8Parser.ReturnStatementContext ctx) {
        Java8Parser.ExpressionContext expression = ctx.expression();
        System.out.println("返回值" + expression.getText());
    }

    private void dealIfStatement(String condition, String content) {
        if (firstEnterIfTag) {
            timestamp = System.currentTimeMillis();
        }

        String identifier = "if";
        if (hasPreElse) {
            identifier = "else if";
        }

        buildBranch(identifier, condition, content);
    }


    private void dealElseStatement(String content) {
        buildBranch("else", null, content);
    }

    private void buildBranch(String identifier, String condition, String content) {
        String cacheKey = StringUtils.join(Arrays.asList(currentClazz, currentMethod, "if", timestamp.toString()), "#");
        IfCodeBlock ifCodeBlock = (IfCodeBlock) LocalCache.computeIfAbsent(cacheKey, k -> {
            IfCodeBlock block = new IfCodeBlock();
            block.setTimestamp(timestamp);
            return block;
        });
        CodeNode codeNode = new CodeNode();
        codeNode.setCondition(condition);
        codeNode.setIdentifier(identifier);
        codeNode.setStatements(null);
        ifCodeBlock.getCodeNodes().add(codeNode);


        if (firstEnterIfTag) {
            currentCodeNode.getCodeBlocks().add(ifCodeBlock);
            firstEnterIfTag = false;
        }
    }

    public List<CodeBlockNode> getCodeBlockNodes() {
        return codeBlockNodes;
    }

    public void setCodeBlockNodes(List<CodeBlockNode> codeBlockNodes) {
        this.codeBlockNodes = codeBlockNodes;
    }
}
