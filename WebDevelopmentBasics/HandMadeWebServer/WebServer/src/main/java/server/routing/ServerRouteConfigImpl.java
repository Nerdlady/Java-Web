package server.routing;

import server.handler.GetHandler;
import server.handler.PostHandler;
import server.handler.RequestHandlerImpl;
import server.http.HttpRequestType;
import server.parser.ControllerAnnotationParser;
import server.provider.ClassProvider;

import java.util.HashMap;
import java.util.Map;

public class ServerRouteConfigImpl implements ServerRouteConfig {
    private final ClassProvider classProvider;
    private Map<HttpRequestType, Map<String, RoutingContext>> routes;


    public ServerRouteConfigImpl(ClassProvider classProvider) throws IllegalAccessException, InstantiationException {
        this.routes = new HashMap<>();
        this.classProvider = classProvider;
        for (HttpRequestType httpRequestType : HttpRequestType.values()) {
            this.routes.put(httpRequestType, new HashMap<>());
        }

        this.initializeServerConfig();

    }

    @Override
    public Map<HttpRequestType, Map<String, RoutingContext>> getRoutes() {
        return this.routes;
    }

    private void initializeServerConfig() throws InstantiationException, IllegalAccessException {
        Map<HttpRequestType,Map<String,ControllerActionPair>> annotationsRoutes = new HashMap<>();

        ControllerAnnotationParser annotationPair = new ControllerAnnotationParser(this.classProvider);

        annotationPair.parse(annotationsRoutes);

        for (Map.Entry<HttpRequestType, Map<String, ControllerActionPair>> mapEntry : annotationsRoutes.entrySet()) {
            for (Map.Entry<String,ControllerActionPair> actionPairEntry : mapEntry.getValue().entrySet()) {
                RequestHandlerImpl requestHandler;
                if (mapEntry.getKey() == HttpRequestType.GET){
                    requestHandler = new GetHandler();
                } else {
                    requestHandler = new PostHandler();
                }

                Map<Integer,Class> args = actionPairEntry.getValue().getArgumentMapping();
                ControllerActionPair actionPair  = actionPairEntry.getValue();

                RoutingContext routingContext = new RoutingContextImpl(requestHandler,actionPair,args);

                this.routes.get(mapEntry.getKey()).put(actionPairEntry.getKey(),routingContext);
            }
        }
    }
}
