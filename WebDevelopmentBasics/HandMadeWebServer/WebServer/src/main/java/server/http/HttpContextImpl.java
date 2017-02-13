package server.http;

import server.exeptions.BadRequestException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HttpContextImpl implements HttpContext {

    private final HttpRequestImpl httpRequest;

    public HttpContextImpl(String request, Map<String, HttpSession> sessions) throws BadRequestException, UnsupportedEncodingException {
        this.httpRequest = new HttpRequestImpl(request,sessions);
    }

    @Override
    public HttpRequest getHttpRequest() {
        return this.httpRequest;
    }
}
