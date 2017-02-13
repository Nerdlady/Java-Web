package server.http;

import java.util.HashMap;
import java.util.Map;

public class HttpCookieImpl implements HttpCookie {
    private Map<String, String> cookies;

    public HttpCookieImpl() {
        this.cookies = new HashMap<>();
    }

    @Override
    public void addCookie(String key, String value) {
        this.cookies.put(key, value);
    }

    @Override
    public void removeCookie(String key) {
        if (this.contains(key)) {
            this.cookies.remove(key);
        }
    }

    @Override
    public boolean contains(String key) {
        return this.cookies.containsKey(key);
    }

    @Override
    public String getCookie(String key) {
        return this.cookies.get(key);
    }
}
