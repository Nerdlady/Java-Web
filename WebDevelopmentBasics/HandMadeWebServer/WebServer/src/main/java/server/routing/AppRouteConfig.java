package server.routing;

import server.handler.RequestHandler;
import server.http.HttpRequestType;

import java.util.Map;

public interface AppRouteConfig {
    AppRouteConfig addRoute(String path,RequestHandler handler);
    Iterable<Map.Entry<HttpRequestType, Map<String, RequestHandler>>> getRoutes();
}
