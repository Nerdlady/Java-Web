package server.handler;

import server.http.HttpContext;
import server.http.HttpSession;
import server.http.HttpSessionImpl;
import server.http.response.HttpResponse;
import server.util.SessionCreator;

import java.io.IOException;
import java.io.Writer;
import java.util.function.Function;

public abstract class RequestHandlerImpl implements RequestHandler{

    private Function<HttpContext,HttpResponse> handler;
    private Writer writer;

    RequestHandlerImpl(Function<HttpContext, HttpResponse> function) {
        this.handler = function;
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        HttpResponse httpResponse = this.handler.apply(httpContext);
        httpResponse.addResponseHeader("Content-Type","text/html");

        this.setSession(httpContext,httpResponse);

        this.writer.write(httpResponse.getResponse());
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    private void setSession(HttpContext httpContext, HttpResponse httpResponse){
        HttpSession httpSession = httpContext.getHttpRequest().getSession();

        if (httpSession == null){
            String sessionId = SessionCreator.getInstance().generateSessionId();

            httpResponse.addResponseHeader("Set-Cookie", String.format("sid=%s; HttpOnly",sessionId));
            httpSession = new HttpSessionImpl(sessionId);
            httpContext.getHttpRequest().setSession(httpSession);
        }
    }
}
