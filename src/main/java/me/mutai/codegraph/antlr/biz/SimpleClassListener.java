/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.antlr.biz;

import me.mutai.codegraph.antlr.Java8Parser;
import me.mutai.codegraph.antlr.Java8ParserBaseListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daixiao
 * @version v 0.1 2022/4/16
 */
public class SimpleClassListener extends Java8ParserBaseListener {

    /**
     * {
     * "methodName": {
     * "timestamp": [
     * {
     * "identifier": {
     * "condition": "",
     * "content": {}
     * }
     * },{
     * "identifier": {
     * "condition": "",
     * "content": {}
     * }
     * }
     * ]
     * }
     * }
     */
    private Map<String, Map<Long, List<Map<String, BranchCode>>>> result = new HashMap<>();

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

        result.computeIfAbsent(currentMethod, k -> new HashMap<>());
        System.out.println("[enter]进入方法定义");
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

    public Map<String, Map<Long, List<Map<String, BranchCode>>>> getResult() {
        return result;
    }

    public void setResult(Map<String, Map<Long, List<Map<String, BranchCode>>>> result) {
        this.result = result;
    }

    private void dealIfStatement(String condition, String content) {
        if (firstEnterIfTag) {
            timestamp = System.currentTimeMillis();
            firstEnterIfTag = false;
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
        Map<String, BranchCode> statement = new HashMap<>();
        BranchCode branchCode = new BranchCode();
        branchCode.setCondition(condition);
        branchCode.setContent(content);

        statement.put(identifier, branchCode);
        List<Map<String, BranchCode>> maps = result
                .get(currentMethod)
                .computeIfAbsent(timestamp, k -> new ArrayList<>());
        maps.add(statement);
    }


    class BranchCode {

        private String condition;

        private String content;


        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
