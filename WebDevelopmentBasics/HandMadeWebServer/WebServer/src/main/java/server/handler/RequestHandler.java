package server.handler;

import server.http.HttpContext;

import java.io.IOException;
import java.io.Writer;

public interface RequestHandler {
    void handle(HttpContext httpContext) throws IOException;

    void setWriter(Writer writer);
}
