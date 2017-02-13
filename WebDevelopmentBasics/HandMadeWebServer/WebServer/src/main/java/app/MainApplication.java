package app;

import app.controller.HomeController;
import app.controller.UserController;
import server.Application;
import server.handler.GetHandler;
import server.routing.AppRouteConfig;

public class MainApplication implements Application {
    @Override
    public void start(AppRouteConfig appRouteConfig) {
        appRouteConfig.addRoute("/", new GetHandler((httpContext -> new HomeController().index())));

        appRouteConfig.addRoute("/users/{(?<name>[a-z]+)}/edit",new GetHandler(httpContext ->
                new UserController().edit(httpContext.getHttpRequest().getURLParameters().get("name"))));
    }
}
