package server.http;

public interface HttpSession {
    String getId();

    void clear();

    void add(String key, String value);
    String get(String key);

    boolean isAuthenticated();
}
