package org.smart4j.framework.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class View {
    private String path;

    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        this.model = new HashMap<>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }
}
