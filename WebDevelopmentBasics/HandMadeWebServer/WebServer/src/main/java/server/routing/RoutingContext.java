package server.routing;

import server.handler.RequestHandlerImpl;

import java.util.Map;

public interface RoutingContext {
    RequestHandlerImpl getHandler();
    Map<Integer,Class> getArgumentMapping();
    ControllerActionPair getActionPair();

}
