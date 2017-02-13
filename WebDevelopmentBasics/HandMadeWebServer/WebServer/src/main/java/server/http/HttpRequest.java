package server.http;

import java.util.Map;

public interface HttpRequest {
    String getURL();
    String getPath();
    HttpRequestType getRequestType();
    String getHeader(String name);
    Map<String, String> getURLParameters();
    Map<String,String> getQueryParameters();
    Map<String, String> getFormData();
    void addParameter(String name, String value);
    void setSession(HttpSession httpSession);
    HttpSession getSession();
    HttpCookie getCookie();
}
