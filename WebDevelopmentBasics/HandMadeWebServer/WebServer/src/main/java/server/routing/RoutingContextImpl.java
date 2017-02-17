package server.routing;

import server.handler.RequestHandlerImpl;

import java.util.Map;

public class RoutingContextImpl implements RoutingContext {
    private Map<Integer, Class> argumentMapping;
    private ControllerActionPair actionPair;
    private RequestHandlerImpl handler;


    public RoutingContextImpl(RequestHandlerImpl requestHandler, ControllerActionPair actionPair, Map<Integer, Class> argumentMapping) {
        this.handler = requestHandler;
        this.actionPair = actionPair;
        this.argumentMapping = argumentMapping;
    }

    @Override
    public RequestHandlerImpl getHandler() {
        return this.handler;
    }


    @Override
    public Map<Integer, Class> getArgumentMapping() {
        return this.argumentMapping;
    }

    @Override
    public ControllerActionPair getActionPair() {
        return this.actionPair;
    }
}
