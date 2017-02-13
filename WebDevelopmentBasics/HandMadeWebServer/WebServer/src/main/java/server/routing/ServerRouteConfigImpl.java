package server.routing;

import server.handler.RequestHandler;
import server.http.HttpRequestType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerRouteConfigImpl implements ServerRouteConfig {
    private Map<HttpRequestType, Map<String, RoutingContext>> routes;


    public ServerRouteConfigImpl(AppRouteConfig appRouteConfig) {
        this.routes = new HashMap<>();
        for (HttpRequestType httpRequestType : HttpRequestType.values()) {
            this.routes.put(httpRequestType, new HashMap<>());
        }

        this.initializeServerConfig(appRouteConfig);
    }

    private void initializeServerConfig(AppRouteConfig appRouteConfig) {
        for (Map.Entry<HttpRequestType, Map<String, RequestHandler>> appRoutes : appRouteConfig.getRoutes()) {
            for (Map.Entry<String, RequestHandler> routeData : appRoutes.getValue().entrySet()) {
                List<String> params = new ArrayList<>();
                String newPatter = this.parseRoute(routeData.getKey(), params);

                RoutingContext routingContext = new RoutingContextImpl(routeData.getValue(),params);

                this.routes.get(appRoutes.getKey()).put(newPatter,routingContext);
            }
        }
    }

    private String parseRoute(String key, List<String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("^");

        while (true) {
            int startIndexOfRegex = key.indexOf("{");
            int endIndexOfRegex = key.indexOf("}");

            if (startIndexOfRegex < 0) {
                break;
            }

            String regex = key.substring(startIndexOfRegex + 1, endIndexOfRegex);

            Pattern pattern = Pattern.compile("<\\w+>");
            Matcher matcher = pattern.matcher(regex);

            if (matcher.find()) {
                String paramName = matcher.group(0).substring(1, matcher.group(0).length() - 1);
                params.add(paramName);
            }

            key = key.substring(0, startIndexOfRegex) + regex + key.substring(endIndexOfRegex + 1, key.length());

        }

        stringBuilder.append(key);
        stringBuilder.append("$");

        return stringBuilder.toString();

    }

    @Override
    public Map<HttpRequestType, Map<String, RoutingContext>> getRoutes() {
        return this.routes;
    }
}
