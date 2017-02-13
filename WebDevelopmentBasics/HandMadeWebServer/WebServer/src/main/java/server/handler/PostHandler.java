package server.handler;

import server.http.HttpContext;
import server.http.response.HttpResponse;

import java.util.function.Function;

public class PostHandler extends RequestHandlerImpl {
    public PostHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
