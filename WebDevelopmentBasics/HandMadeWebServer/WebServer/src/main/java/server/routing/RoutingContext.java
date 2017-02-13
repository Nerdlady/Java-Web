package server.routing;

import server.handler.RequestHandler;

public interface RoutingContext {
    RequestHandler getHandler();
    Iterable<String> getParamNames();
}
