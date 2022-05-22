package me.mutai.codegraph.engine;

import org.junit.Test;


/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
public class AnalyzeEngineImplTest {


    private static final String onlyIfStatement = "package com.alipay.mutai.demo;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "public class MyTest {\n" +
            "\n" +
            "\n" +
            "    public int cal(Map<String, Object> map, int m, int n) {\n" +
            "\n" +
            "        if (map.get(\"type\") == \"add\" ) {\n" +
            "            return add(m, n);\n" +
            "        }\n" +
            "\n" +
            "        throw new RuntimeException();\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private int add(int m, int n) {\n" +
            "        return m + n;\n" +
            "    }\n" +
            "\n" +
            "    private int multi(int m, int n) {\n" +
            "        return m * n;\n" +
            "    }\n" +
            "\n" +
            "    private int minus(int m, int n) {\n" +
            "        return m - n;\n" +
            "    }\n" +
            "    \n" +
            "    private int divide(int m, int n) {\n" +
            "        return m / n;\n" +
            "    }\n" +
            "    \n" +
            "}";

    public static final String ifElseStatement = "package com.alipay.mutai.demo;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "public class MyTest {\n" +
            "\n" +
            "\n" +
            "    public int cal(Map<String, Object> map, int m, int n) {\n" +
            "\n" +
            "        if (map.get(\"type\") == \"add\" ) {\n" +
            "            return add(m, n);\n" +
            "        } else {\n" +
            "            return multi(m, n);\n" +
            "        }\n" +
            "\n" +
            "        throw new RuntimeException();\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private int add(int m, int n) {\n" +
            "        return m + n;\n" +
            "    }\n" +
            "\n" +
            "    private int multi(int m, int n) {\n" +
            "        return m * n;\n" +
            "    }\n" +
            "\n" +
            "    private int minus(int m, int n) {\n" +
            "        return m - n;\n" +
            "    }\n" +
            "    \n" +
            "    private int divide(int m, int n) {\n" +
            "        return m / n;\n" +
            "    }\n" +
            "    \n" +
            "}";


    public static final String ifElseIfStatement = "package com.alipay.mutai.demo;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "public class MyTest {\n" +
            "\n" +
            "\n" +
            "    public int cal(Map<String, Object> map, int m, int n) {\n" +
            "\n" +
            "        if (map.get(\"type\") == \"add\" ) {\n" +
            "            return add(m, n);\n" +
            "        } else if (map.get(\"type\") == \"multi\") {\n" +
            "            return multi(m, n);\n" +
            "        } else if (map.get(\"type\") == \"minus\") {\n" +
            "            return minus(m, n);\n" +
            "        } else if (map.get(\"type\") == \"divide\") {\n" +
            "            return divide(m, n);\n" +
            "        } else {\n" +
            "            throw new RuntimeException();\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private int add(int m, int n) {\n" +
            "        return m + n;\n" +
            "    }\n" +
            "\n" +
            "    private int multi(int m, int n) {\n" +
            "        return m * n;\n" +
            "    }\n" +
            "\n" +
            "    private int minus(int m, int n) {\n" +
            "        return m - n;\n" +
            "    }\n" +
            "    \n" +
            "    private int divide(int m, int n) {\n" +
            "        return m / n;\n" +
            "    }\n" +
            "    \n" +
            "}";

    public static final String multiIfStatement = "package com.alipay.mutai.demo;\n" +
            "\n" +
            "import java.util.Map;\n" +
            "\n" +
            "public class MyTest {\n" +
            "\n" +
            "\n" +
            "    public int cal(Map<String, Object> map, int m, int n) {\n" +
            "\n" +
            "        if (map.get(\"type\") == \"add\" ) {\n" +
            "            return add(m, n);\n" +
            "        } else if (map.get(\"type\") == \"multi\") {\n" +
            "            return multi(m, n);\n" +
            "        } else if (map.get(\"type\") == \"minus\") {\n" +
            "            return minus(m, n);\n" +
            "        } else if (map.get(\"type\") == \"divide\") {\n" +
            "            return divide(m, n);\n" +
            "        } \n" +
            "\n" +
            "        if (map.get(\"hello\") == \"yes\") {\n" +
            "            return 0;\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "\n" +
            "    private int add(int m, int n) {\n" +
            "        return m + n;\n" +
            "    }\n" +
            "\n" +
            "    private int multi(int m, int n) {\n" +
            "        return m * n;\n" +
            "    }\n" +
            "\n" +
            "    private int minus(int m, int n) {\n" +
            "        return m - n;\n" +
            "    }\n" +
            "    \n" +
            "    private int divide(int m, int n) {\n" +
            "        return m / n;\n" +
            "    }\n" +
            "    \n" +
            "}";


    @Test
    public void testAnalyze() {

        AnalyzeEngine engine = new AnalyzeEngineImpl();
        engine.analyze(multiIfStatement);
    }
}