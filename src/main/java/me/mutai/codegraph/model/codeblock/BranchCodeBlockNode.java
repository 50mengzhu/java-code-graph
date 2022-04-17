/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

import lombok.Data;

/**
 * 分支代码模块，主要用于解析 if-else switch try-catch等代码块
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
@Data
public class BranchCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 分支跳转条件
     */
    private String condition;

    /**
     * 分支切换标识符
     */
    private String identifier;

    /**
     * 当前分支包含的内容
     */
    private AbstractCodeBlockNode statement;

    @Override
    public void set(AbstractCodeBlockNode node) {
        this.statement = node;
    }
}
