/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model.codeblock;

/**
 * 初始化类解析器
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class ClassCodeBlockNode extends AbstractCodeBlockNode {

    /**
     * 所在类
     */
    private String clazz;

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
