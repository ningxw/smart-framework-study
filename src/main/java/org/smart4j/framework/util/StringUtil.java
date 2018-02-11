package org.smart4j.framework.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }

        return StringUtils.isEmpty(str);
    }


    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String[] splitString(String source, String spliter) {
        if (isEmpty(source)) {
            return new String[0];
        } else {
            return source.split(spliter);
        }
    }
}
