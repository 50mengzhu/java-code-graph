/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

/**
 * 抽象代码结构，主要关注 分支、循环、函数调用 等
 *
 * 作为一个树状结构进行管理
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public abstract class AbstractCodeBlockNode extends TreeNode<AbstractCodeBlockNode> {

    /**
     * 设置语句
     *
     * @param node
     */
    public void set(AbstractCodeBlockNode node) {}
}
