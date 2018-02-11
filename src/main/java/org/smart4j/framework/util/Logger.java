package org.smart4j.framework.util;

import org.slf4j.LoggerFactory;

public class Logger {
    public static void error(Object obj, String message, Exception e) {
        Class targetClass = obj instanceof Class ? (Class)obj : obj.getClass();
        org.slf4j.Logger logger = LoggerFactory.getLogger(targetClass);
        if (logger.isErrorEnabled()) {
            logger.error(message, e);
        }
    }

    public static void info(Object obj, String message) {
        Class targetClass = obj instanceof Class ? (Class)obj : obj.getClass();
        org.slf4j.Logger logger = LoggerFactory.getLogger(targetClass);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }
}
