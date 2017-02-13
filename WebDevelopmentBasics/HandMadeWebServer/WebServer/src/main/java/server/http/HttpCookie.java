package server.http;

public interface HttpCookie {
    void addCookie(String key, String value);

    void removeCookie(String key);

    boolean contains(String key);

    String getCookie(String key);
}
