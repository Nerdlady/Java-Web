package server.http;

import server.constants.Constants;
import server.exeptions.BadRequestException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestImpl implements HttpRequest {
    private final Map<String, String> headers;
    private final Map<String, String> urlParameters;
    private final Map<String, String> queryParameters;
    private final Map<String, String> formData;
    private HttpRequestType httpRequestType;
    private String url;
    private String path;
    private HttpSession session;
    private HttpCookie cookie;

    public HttpRequestImpl(String request, Map<String, HttpSession> sessions) throws BadRequestException, UnsupportedEncodingException {
        this.headers = new HashMap<>();
        this.urlParameters = new HashMap<>();
        this.queryParameters = new HashMap<>();
        this.formData = new HashMap<>();

        this.parseRequest(request, sessions);
    }

    @Override
    public String getURL() {
        return this.url;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public HttpRequestType getRequestType() {
        return this.httpRequestType;
    }

    @Override
    public String getHeader(String name) {
        return this.headers.get(name);
    }

    @Override
    public Map<String, String> getURLParameters() {
        return this.urlParameters;
    }

    @Override
    public Map<String, String> getQueryParameters() {
        return this.queryParameters;
    }

    @Override
    public Map<String, String> getFormData() {
        return this.formData;
    }

    @Override
    public void addParameter(String name, String value) {
        this.urlParameters.put(name, value);
    }

    @Override
    public void setSession(HttpSession httpSession) {
        this.session = httpSession;
    }

    @Override
    public HttpSession getSession() {
        return this.session;
    }

    @Override
    public HttpCookie getCookie() {
        return this.cookie;
    }

    private void parseRequest(String request, Map<String, HttpSession> sessions) throws BadRequestException, UnsupportedEncodingException {
        String[] requestLines = request.split("\n");
        String[] requestFirstLineInfo = requestLines[0].trim().split("\\s+");

        if (requestFirstLineInfo.length != 3 || !requestFirstLineInfo[2].toLowerCase().equals("http/1.1")) {
            throw new BadRequestException(Constants.INVALID_REQUEST_LINE);
        }

        this.httpRequestType = this.parseRequestType(requestFirstLineInfo[0]);
        this.url = requestFirstLineInfo[1];
        this.path = this.url.split("\\?")[0];

        this.parseHeaders(requestLines);
        this.parseParameters();

        if (this.httpRequestType == HttpRequestType.POST) {
            this.parseQuery(requestLines[requestLines.length - 1], this.formData);
        }

        this.parseCookie();
        this.setSession(sessions);
    }

    private void setSession(Map<String, HttpSession> sessions) {
        this.session =  sessions.get(this.cookie.getCookie("sid").trim());
    }

    private void parseCookie() {
        String allCookies = this.headers.get("Cookie");
        this.cookie = new HttpCookieImpl();
        if (allCookies == null) {
            return;
        }

        String[] cookies = allCookies.split(";\\s*");

        for (String cookie : cookies) {
            String[] cookieArgs = cookie.split("=");
            this.cookie.addCookie(cookieArgs[0], cookieArgs[1]);
        }
    }

    private void parseParameters() throws UnsupportedEncodingException {
        if (!this.url.contains("?")) {
            return;
        }

        String query = this.url.split("\\?")[1];
        this.parseQuery(query, this.queryParameters);
    }

    private void parseQuery(String query, Map<String, String> map) throws UnsupportedEncodingException {
        if (!query.contains("=")) {
            return;
        }
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            String[] params = pair.split("=");
            map.put(URLDecoder.decode(params[0], "UTF-8"), URLDecoder.decode(params[1], "UTF-8"));
        }
    }

    private void parseHeaders(String[] requestLines) throws BadRequestException {
        int endIndex = Arrays.asList(requestLines).indexOf("\r");
        for (int i = 1; i < endIndex; i++) {
            String[] requestLineData = requestLines[i].split(":\\s+");
            this.headers.put(requestLineData[0], requestLineData[1]);
        }

        if (!this.headers.containsKey("Host")) {
            throw new BadRequestException(Constants.INVALID_REQUEST_LINE);
        }
    }

    private HttpRequestType parseRequestType(String requestType) throws BadRequestException {
        switch (requestType.toLowerCase()) {
            case "get":
                return HttpRequestType.GET;
            case "post":
                return HttpRequestType.POST;
            default:
                throw new BadRequestException(Constants.INVALID_REQUEST_LINE);
        }
    }
}
