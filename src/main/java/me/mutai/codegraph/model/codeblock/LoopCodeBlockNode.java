/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

/**
 * 循环代码模块，主要用于解析 for while do-while代码
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class LoopCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 循环标识符
     */
    private String identifier;

    /**
     * 循环条件
     */
    private String condition;

    /**
     * 循环体内语句
     */
    private AbstractCodeBlockNode statement;

    @Override
    public void set(AbstractCodeBlockNode node) {
        this.statement = node;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public AbstractCodeBlockNode getStatement() {
        return statement;
    }

    public void setStatement(AbstractCodeBlockNode statement) {
        this.statement = statement;
    }
}
