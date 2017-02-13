package server.http.response;

public enum HttpResponseCode {
    OK(200, "OK"), MovedPermanently(301, "Moved Permanently"), Created(201, "Created"), Found(302, "Found"), Unauthorized(401, "Unauthorized");

    private final int value;
    private final String text;

    HttpResponseCode(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
