package server.routing;

import server.handler.RequestHandler;
import server.http.HttpRequestType;

import java.util.HashMap;
import java.util.Map;

public class AppRouteConfigImpl implements AppRouteConfig {
    private Map<HttpRequestType, Map<String, RequestHandler>> routes;

    public AppRouteConfigImpl() {
        this.routes = new HashMap<>();
        for (HttpRequestType httpRequestType : HttpRequestType.values()) {
            this.routes.put(httpRequestType, new HashMap<>());
        }
    }

    @Override
    public AppRouteConfig addRoute(String path, RequestHandler handler) {
        if (handler.getClass().toString().toLowerCase().contains("get")) {
            this.routes.get(HttpRequestType.GET).put(path, handler);
        } else if (handler.getClass().toString().toLowerCase().contains("post")) {
            this.routes.get(HttpRequestType.POST).put(path, handler);
        }
        return this;
    }

    @Override
    public Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandler>>> getRoutes() {
        return this.routes.entrySet();
    }
}
