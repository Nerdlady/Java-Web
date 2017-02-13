package server.routing;

import server.handler.RequestHandler;

import java.util.List;

public class RoutingContextImpl implements RoutingContext  {
    private RequestHandler handler;
    private List<String> paramNames;

    public RoutingContextImpl(RequestHandler handler, List<String> paramNames) {
        this.handler = handler;
        this.paramNames = paramNames;
    }

    @Override
    public RequestHandler getHandler() {
        return this.handler;
    }

    @Override
    public Iterable<String> getParamNames() {
        return this.paramNames;
    }
}
