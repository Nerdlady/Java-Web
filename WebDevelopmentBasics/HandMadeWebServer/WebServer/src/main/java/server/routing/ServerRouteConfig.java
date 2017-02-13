package server.routing;

import server.http.HttpRequestType;

import java.util.Map;

public interface ServerRouteConfig {
    Map<HttpRequestType,Map<String, RoutingContext>> getRoutes();
}
