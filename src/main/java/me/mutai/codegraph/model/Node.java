/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model;

/**
 * 调用链路中展示的单节点定义。
 * 单个节点包含
 * <ol>
 *     <li>方法明细</li>
 *     <li>路径进入条件</li>
 * </ol>
 *
 *
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public class Node {

    private NodeDetail detail;

    private PathCondition condition;
}
