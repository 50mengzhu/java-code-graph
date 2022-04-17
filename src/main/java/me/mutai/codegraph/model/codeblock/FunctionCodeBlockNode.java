/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

import lombok.Data;

import java.util.List;

/**
 * 函数代码块，用于解析 java函数
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
@Data
public class FunctionCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 函数所在的类
     */
    private String clazz;

    /**
     * 函数名
     */
    private String method;

    /**
     * 函数参数类型
     */
    private List<String> argTypes;

    /**
     * 参数列表
     */
    private List<String> args;

    /**
     * 返回类型
     */
    private String returnType;


    /**
     * 函数调用具体逻辑
     */
    private AbstractCodeBlockNode statement;


    @Override
    public void set(AbstractCodeBlockNode node) {
        this.statement = node;
    }
}
