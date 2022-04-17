/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
@Data
public class TreeNode<T> {

    private T data;

    private List<TreeNode<? extends T>> children = Lists.newArrayList();

    private TreeNode<? extends T> parent;

}
