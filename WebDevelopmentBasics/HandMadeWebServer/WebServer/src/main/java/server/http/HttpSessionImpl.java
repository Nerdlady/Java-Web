package server.http;

import java.util.HashMap;
import java.util.Map;

public class HttpSessionImpl implements HttpSession {
    private String id;
    private Map<String,String> parameters;

    public HttpSessionImpl(String id) {
        this.id = id;
        this.parameters = new HashMap<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void clear() {
        this.parameters.clear();
    }

    @Override
    public void add(String key, String value) {
        this.parameters.put(key,value);
    }

    @Override
    public String get(String key) {
        return this.parameters.get(key);
    }

    @Override
    public boolean isAuthenticated() {
        return this.parameters.size() > 0;
    }
}
