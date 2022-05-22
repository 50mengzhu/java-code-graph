/**
 * Copyright (c) 2015-2022 daixiao All rights reserved.
 */
package me.mutai.codegraph.util;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 *
 * @author daixiao
 * @version v 0.1 2022/4/17
 */
public class LocalCache {

    private static ConcurrentMap<String, Object> cache = new ConcurrentHashMap<>();


    public static ConcurrentMap<String, Object> getCache() {
        return cache;
    }

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static boolean contains(String key) {
        return !StringUtils.isBlank(key) && cache.containsKey(key);
    }

    public static Object computeIfAbsent(String key,
                                         Function<? super String, ?> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }

    public static Object get(String key) {
        return StringUtils.isBlank(key) ? null : cache.get(key);
    }
}
