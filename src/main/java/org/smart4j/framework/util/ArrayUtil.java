package org.smart4j.framework.util;

public class ArrayUtil {
    public static boolean isNotEmpty(Object[] arrays) {
        return arrays != null && arrays.length > 0;
    }

    public static boolean isEmpty(Object[] arrays) {
        return !isNotEmpty(arrays);
    }
}