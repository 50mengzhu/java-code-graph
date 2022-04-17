/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.antlr.biz;

import com.google.common.collect.Lists;
import me.mutai.codegraph.antlr.Java8Parser;
import me.mutai.codegraph.antlr.Java8ParserBaseListener;
import me.mutai.codegraph.model.codeblock.AbstractCodeBlockNode;
import me.mutai.codegraph.model.codeblock.BranchCodeBlockNode;
import me.mutai.codegraph.model.codeblock.ClassCodeBlockNode;
import me.mutai.codegraph.model.codeblock.FunctionCodeBlockNode;
import me.mutai.codegraph.model.codeblock.NormalCodeBlockNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Stack;

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
     * 是否存在前置 else
     */
    private volatile boolean hasPreElse = false;


    private volatile String currentClazz;

    private volatile String currentStatementTag;

    private static final List<String> needAddStatement;

    static {
        needAddStatement = Lists.newArrayList();
        needAddStatement.add("branch");
        needAddStatement.add("loop");
        needAddStatement.add("function");
    }


    /**
     * 类解析器解析数据
     */
    private AbstractCodeBlockNode rootCodeBlockNode = new ClassCodeBlockNode();

    private Stack<AbstractCodeBlockNode> currentCodeBlockNodeStack = new Stack<>();

    private Stack<AbstractCodeBlockNode> currentParentNodeStack = new Stack<>();

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {
        currentParentNodeStack.push(rootCodeBlockNode);
    }

    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {

        Java8Parser.MethodBodyContext bodyContext = ctx.methodBody();
        String text = bodyContext.getText();
        System.out.println("[enter]获取方法体内容" + text);
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println("[exit]退出方法体匹配");
        currentParentNodeStack.pop();
    }


    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {

        TerminalNode node = ctx.Identifier();
        currentMethod = node.getText();
        System.out.println("[enter]进入方法定义");


        AbstractCodeBlockNode currentParentNode = currentParentNodeStack.peek();
        FunctionCodeBlockNode funcCodeNode = new FunctionCodeBlockNode();
        funcCodeNode.setMethod(currentMethod);
        funcCodeNode.setParent(currentParentNode);
        currentParentNode.getChildren().add(funcCodeNode);
        currentParentNodeStack.push(funcCodeNode);

    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {

        System.out.println("[enter]匹配单 if模式");
        dealIfStatement(ctx.expression().getText());
    }


    @Override
    public void exitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        hasPreElse = false;
        currentStatementTag = null;
        currentParentNodeStack.pop();
        currentCodeBlockNodeStack.pop();
        System.out.println("[exit]退出匹配 if模式");
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {

        System.out.println("[enter]匹配 if-else模式");

        dealIfStatement(ctx.expression().getText());
        if (ctx.ELSE().getSymbol().getTokenIndex() != -1
                && ctx.ELSE().getSymbol().getLine() != -1) {
            hasPreElse = true;
        }
    }

    @Override
    public void exitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {

        // TODO 暂且放在这里
        if (hasPreElse) {
            dealElseStatement();
        }

        hasPreElse = false;
        currentParentNodeStack.pop();
        currentCodeBlockNodeStack.pop();
        System.out.println("[exit]退出匹配 if-else模式");
    }


    @Override
    public void enterStatement(Java8Parser.StatementContext ctx) {

        /*if (careStatementList.contains(currentState)) {
            System.out.println("语句" + ctx.getText());
        }*/

        if (needAddStatement.contains(currentStatementTag)) {
            AbstractCodeBlockNode codeBlockNode = currentCodeBlockNodeStack.peek();
            NormalCodeBlockNode normalCodeBlockNode = new NormalCodeBlockNode();
            normalCodeBlockNode.setStatement(ctx.getText());
            codeBlockNode.set(normalCodeBlockNode);
        }
        System.out.println("[enter]获取语句");
    }

    @Override
    public void exitStatement(Java8Parser.StatementContext ctx) {
        currentStatementTag = null;
        System.out.println("[exit]退出获取语句");
    }

    @Override
    public void enterReturnStatement(Java8Parser.ReturnStatementContext ctx) {
        Java8Parser.ExpressionContext expression = ctx.expression();
        System.out.println("返回值" + expression.getText());
    }

    private void dealIfStatement(String condition) {

        String identifier = "if";
        if (hasPreElse) {
            identifier = "else if";
        }

        if (StringUtils.isBlank(currentStatementTag)) {
            currentStatementTag = "branch";
        }

        buildBranch(identifier, condition);
    }


    private void dealElseStatement() {
        buildBranch("else", null);
    }

    private void buildBranch(String identifier, String condition) {

        AbstractCodeBlockNode currentParentNode = currentParentNodeStack.peek();
        BranchCodeBlockNode codeBlockNode = new BranchCodeBlockNode();
        codeBlockNode.setCondition(condition);
        codeBlockNode.setIdentifier(identifier);
        codeBlockNode.setParent(currentParentNode);
        currentParentNode.getChildren().add(codeBlockNode);
        currentCodeBlockNodeStack.push(codeBlockNode);
        currentParentNodeStack.push(codeBlockNode);

    }

    public AbstractCodeBlockNode getRootCodeBlockNode() {
        return rootCodeBlockNode;
    }
}
