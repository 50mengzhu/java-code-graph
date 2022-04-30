/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.engine;

import org.apache.bcel.Repository;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.JavaClass;
import org.junit.Test;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/20
 */
public class TestBCEL {

    @Test
    public void testApi() {
        try {
            JavaClass javaClazz = Repository.lookupClass("me.mutai.codegraph.Test");
            final Attribute[] attributes = javaClazz.getAttributes();
        } catch (Exception ex) {

            ex.printStackTrace();
        }

    }
}
