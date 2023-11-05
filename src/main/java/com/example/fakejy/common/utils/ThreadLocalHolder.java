package com.example.fakejy.common.utils;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

import java.util.Map;

public class ThreadLocalHolder {

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    static {
        THREAD_LOCAL.set(Maps.newConcurrentMap());
    }

    public static void set(String key, Object value) {
        THREAD_LOCAL.get().put(key, value);
    }

    public static Object get(String key) {
        return THREAD_LOCAL.get().get(key);
    }

    public static void remove(String key) {
        THREAD_LOCAL.get().remove(key);
    }

    public static void clear() {
        THREAD_LOCAL.get().clear();
    }
}
