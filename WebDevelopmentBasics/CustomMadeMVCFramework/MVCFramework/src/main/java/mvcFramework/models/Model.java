package mvcFramework.models;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Model {
    private HttpServletRequest request;

    private Map<String, Object> attributes;

    public Model(HttpServletRequest request) {
        this.request = request;
        this.attributes = new HashMap<>();
    }

    public void addAttribute(String key, Object value) {
        this.attributes.put(key,value);
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            this.request.setAttribute(entry.getKey(),entry.getValue());
        }
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

}
