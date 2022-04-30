/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class TreeNode<T> {

    private T data;

    private List<TreeNode<? extends T>> children = Lists.newArrayList();

    private TreeNode<? extends T> parent;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<TreeNode<? extends T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<? extends T>> children) {
        this.children = children;
    }

    public TreeNode<? extends T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<? extends T> parent) {
        this.parent = parent;
    }
}
