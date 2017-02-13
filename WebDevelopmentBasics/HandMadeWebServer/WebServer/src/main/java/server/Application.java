package server;

import server.routing.AppRouteConfig;

public interface Application {
    void start(AppRouteConfig appRouteConfig);
}
