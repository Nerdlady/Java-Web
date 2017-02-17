package server.parser;

import java.util.Map;

public interface AnnotationParser<RequestType,RouteInfo> {
    void parse(Map<RequestType,RouteInfo> routes) throws IllegalAccessException, InstantiationException;
}
