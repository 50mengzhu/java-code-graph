/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.engine;

import java.io.File;

/**
 * 编译引擎
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public interface CompileEngine {


    void compile(String path);

    void compile(File file);

}
