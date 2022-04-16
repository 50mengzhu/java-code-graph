/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public class Test {

    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        List<String> optionList = new ArrayList<String>(Arrays.asList("-d","F:\\work\\test\\code-graph\\target\\classes"));


        Iterable<? extends JavaFileObject> classes = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File("F:\\work\\workdog\\code\\thread\\src\\MyTest.java")));

        JavaCompiler.CompilationTask task =
                compiler.getTask(null, null, null, optionList, null, classes);

        Boolean call = task.call();

        System.out.println(call);

        try {
            final Class<?> testThreadPool = compiler.getClass().getClassLoader().loadClass("com.alipay.mutai.demo.MyTest");
            System.out.println(testThreadPool);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        System.out.println();

    }



}
