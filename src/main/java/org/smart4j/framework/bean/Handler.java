package org.smart4j.framework.bean;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class Handler {
    private Class<?> controllerClass;
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }
}