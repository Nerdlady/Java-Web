package server.http;

import server.handler.RequestHandler;
import server.routing.RoutingContext;
import server.routing.ServerRouteConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHandler implements RequestHandler {
    private Writer writer;
    private ServerRouteConfig serverRouteConfig;

    public HttpHandler(ServerRouteConfig serverRouteConfig, PrintWriter printWriter) {
        this.serverRouteConfig = serverRouteConfig;
        this.setWriter(printWriter);
    }

    @Override
    public void handle(HttpContext httpContext) throws IOException {
        HttpRequestType httpRequestType = httpContext.getHttpRequest().getRequestType();

        for (Map.Entry<String, RoutingContext> data : this.serverRouteConfig.getRoutes().get(httpRequestType).entrySet()) {
            Pattern pattern = Pattern.compile(data.getKey());
            Matcher matcher = pattern.matcher(httpContext.getHttpRequest().getPath());

            if (!matcher.find()){
                continue;
            }

            for (String paramName : data.getValue().getParamNames()) {
                httpContext.getHttpRequest().addParameter(paramName,matcher.group(paramName));
            }

            data.getValue().getHandler().setWriter(this.writer);
            data.getValue().getHandler().handle(httpContext);

            return;
        }

        throw new FileNotFoundException();
    }

    @Override
    public void setWriter(Writer writer) {
        this.writer = writer;
    }
}
