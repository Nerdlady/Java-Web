package server.handler;

import server.http.HttpContext;
import server.http.response.HttpResponse;

import java.util.function.Function;

public class GetHandler extends RequestHandlerImpl {
    public GetHandler() {
    }

    public GetHandler(Function<HttpContext, HttpResponse> function) {
        super(function);
    }
}
