/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

import lombok.Data;

/**
 * 用作终止抽象代码块节点的代码板块，返回字符串或其他操作
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
@Data
public class NormalCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 除开 分支、循环、函数调用之外的 统一处理语句
     */
    private String statement;
}
