package org.smart4j.framework.helper;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;
import org.smart4j.framework.util.ReflectionUtil;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class ControllerHelper {
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();

        if (CollectionUtil.isNotEmpty(controllerClassSet) && CollectionUtil.isNotEmpty(beanMap)) {
            for (Class<?> cls : controllerClassSet) {
                Object controller = beanMap.get(cls);

                if (controller != null) {
                    //填充Service对象
                    Field[] fields = cls.getDeclaredFields();
                    if (ArrayUtil.isNotEmpty(fields)) {
                        for (Field field : fields) {
                            Class<?> fieldClass = field.getType();
                            ReflectionUtil.setField(controller, field, beanMap.get(fieldClass));
                        }
                    }

                    //解析路径和请求方法
                    Method[] methods = cls.getMethods();
                    if (ArrayUtil.isNotEmpty(methods)) {
                        for (Method method : methods) {
                            if (method.isAnnotationPresent(Action.class)) {
                                String mapping = method.getDeclaredAnnotation(Action.class).value();
                                if (mapping.matches("\\w+:/\\w+")) {
                                    String[] array = mapping.split(":");
                                    if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                        Request request = new Request(array[0], array[1]);
                                        Handler handler = new Handler(cls, method);
                                        ACTION_MAP.put(request, handler);
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}