/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class CodeBlockNode {

    // 当前代码所属的基本信息 - class、method、args等等
    private String clazz;

    private String method;

    private List<String> argTypes;

    private List<String> argNames;

    // 代码分析需要的数据

    /**
     * 时间戳，用于进行调用关系的先后顺序排列
     */
    private Long timestamp = System.currentTimeMillis();

    private List<AbstractCodeBlock> codeBlocks = new ArrayList<>();


    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public List<String> getArgTypes() {
        return argTypes;
    }

    public void setArgTypes(List<String> argTypes) {
        this.argTypes = argTypes;
    }

    public List<String> getArgNames() {
        return argNames;
    }

    public void setArgNames(List<String> argNames) {
        this.argNames = argNames;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public List<AbstractCodeBlock> getCodeBlocks() {
        return codeBlocks;
    }

    public void setCodeBlocks(List<AbstractCodeBlock> codeBlocks) {
        this.codeBlocks = codeBlocks;
    }
}
