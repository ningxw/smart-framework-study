package org.smart4j.chapter3.service;

import org.junit.Test;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.util.ClassUtil;

public class ClassUtilTest {
    @Test
    public void test_getClassSet() {
        String packageName = "com";
        //ClassUtil.getClassSet(packageName);
        //ClassHelper.getServiceClassSet();
        System.out.println("boot:" + System.getProperty("sun.boot.class.path"));
         System.out.println("ext:" + System.getProperty("java.ext.dirs"));
         System.out.println("classpath:" + System.getProperty("java.class.path"));
    }
}