/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author daixiao
 * @version v 0.1 2022/4/10
 */
public class LogUtil {

    public static void info(Logger logger, String fmt, Object... args) {
        if (logger.isInfoEnabled()) {
            if (args != null && args.length > 0) {
                List<String> logs = Arrays.stream(args)
                        .map(JSONObject::toJSONString)
                        .collect(Collectors.toList());

                logger.info(fmt, logs);
            }
        }
    }


    public static void warn(Logger logger, Throwable t, String fmt, Object ... args) {
        if (logger.isWarnEnabled()) {
            if (args != null && args.length > 0) {
                List<String> logs = Arrays.stream(args)
                        .map(JSONObject::toJSONString)
                        .collect(Collectors.toList());

                logger.warn(fmt, t, logs);
            }
        }

    }
}
