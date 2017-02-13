import app.MainApplication;
import server.Application;
import server.Server;
import server.ServerImpl;
import server.routing.AppRouteConfig;
import server.routing.AppRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {
        Application application = new MainApplication();
        AppRouteConfig appRouteConfig = new AppRouteConfigImpl();
        application.start(appRouteConfig);

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            Server server = new ServerImpl(serverSocket,appRouteConfig);
            server.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
