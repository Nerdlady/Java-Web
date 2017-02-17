import server.Server;
import server.ServerImpl;
import server.provider.ClassProvider;
import server.provider.ClassProviderImpl;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) {

        try {
            ClassProvider classProvider = new ClassProviderImpl();
            ServerSocket serverSocket = new ServerSocket(8080);
            Server server = new ServerImpl(serverSocket,classProvider);
            server.runServer();
        } catch (IOException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
