/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

/**
 * 分支代码模块，主要用于解析 if-else switch try-catch等代码块
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class BranchCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 分支跳转条件
     */
    private AbstractCodeBlockNode condition;

    /**
     * 分支切换标识符
     */
    private String identifier;

    /**
     * 当前分支包含的内容
     */
    private AbstractCodeBlockNode statement;

    /**
     * 下一个兄弟节点
     */
    private AbstractCodeBlockNode next;

    /**
     * 前置兄弟节点
     */
    private AbstractCodeBlockNode pre;

    @Override
    public void set(AbstractCodeBlockNode node) {
        this.statement = node;
    }

    public AbstractCodeBlockNode getCondition() {
        return condition;
    }

    public void setCondition(AbstractCodeBlockNode condition) {
        this.condition = condition;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public AbstractCodeBlockNode getStatement() {
        return statement;
    }

    public void setStatement(AbstractCodeBlockNode statement) {
        this.statement = statement;
    }

    public AbstractCodeBlockNode getNext() {
        return next;
    }

    public void setNext(AbstractCodeBlockNode next) {
        this.next = next;
    }

    public AbstractCodeBlockNode getPre() {
        return pre;
    }

    public void setPre(AbstractCodeBlockNode pre) {
        this.pre = pre;
    }
}
