/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

/**
 * 编译引擎实现类
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public class CompileEngineImpl implements CompileEngine {

    private static final Logger logger = LoggerFactory.getLogger(CompileEngineImpl.class);

    private static JavaCompiler javaCompiler;


    static {
        javaCompiler = ToolProvider.getSystemJavaCompiler();
    }



    public void compile(String path) {

        /*if (StringUtils.isBlank(path)) {
            LogUtil.info(logger, "path is empty:{}", path);
            return;
        }

        int result = javaCompiler.run(null, null, null, path);
        if (result != 0) {
            LogUtil.info(logger, "error compile java resource! retsult:{}", result);
        }*/


    }

    public void compile(File file) {

    }
}
