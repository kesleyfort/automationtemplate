package br.com.template.context;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TestContext {

    private Map<String, Object> resourceMap;

    public TestContext() {
        resourceMap = new HashMap<>();
    }

    public void add(String key, Object object) {
        resourceMap.put(key, object);
    }

    public Object remove(String key) {
        return resourceMap.get(key);
    }

    public void clearContext() {
        resourceMap.clear();
    }
}
