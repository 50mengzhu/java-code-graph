/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.engine;

/**
 * 流程分析引擎
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public interface AnalyzeEngine {


    /**
     * 前置分析处理
     */
    void preHandle();


    /**
     * 分析处理
     */
    void analyze(String code);


    /**
     * 后置处理
     */
    void postHandle();

}
