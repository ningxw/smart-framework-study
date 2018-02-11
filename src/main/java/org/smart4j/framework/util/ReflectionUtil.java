package org.smart4j.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class ReflectionUtil {
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            instance = cls.newInstance();
        } catch (Exception e) {
            Logger.error(ReflectionUtil.class, "new instance failure", e);
            throw new RuntimeException(e);
        }

        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;

        method.setAccessible(true);
        try {
            result = method.invoke(obj, args);
        } catch (Exception e) {
            Logger.error(ReflectionUtil.class, "invoke method failure", e);
            throw new RuntimeException(e);
        }

        return result;
    }

    public static void setField(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            Logger.error(ReflectionUtil.class, "set field failure", e);
            throw new RuntimeException(e);
        }
    }
}
