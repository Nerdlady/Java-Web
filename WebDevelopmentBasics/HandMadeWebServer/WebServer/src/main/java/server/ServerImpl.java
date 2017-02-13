package server;

import server.constants.Constants;
import server.http.HttpSession;
import server.routing.AppRouteConfig;
import server.routing.ServerRouteConfigImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;

public class ServerImpl implements Server {
    private final ServerRouteConfigImpl serverRouteConfig;
    private boolean isRunning;
    private ServerSocket serverSocket;
    private Map<String,HttpSession> sessions;

    public ServerImpl(ServerSocket serverSocket, AppRouteConfig appRouteConfig) {
        this.serverSocket = serverSocket;
        this.serverRouteConfig = new ServerRouteConfigImpl(appRouteConfig);
        this.sessions = new HashMap<>();
    }

    @Override
    public void runServer() throws SocketException {
        this.isRunning = true;
        this.serverSocket.setSoTimeout(Constants.TIMEOUT);
        this.acceptRequest();
    }

    private void acceptRequest() {
        while (isRunning){
            try (Socket clientSocket = this.serverSocket.accept()){
                clientSocket.setSoTimeout(Constants.TIMEOUT);
                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket,this.serverRouteConfig,this.sessions);
                FutureTask<?> futureTask = new FutureTask<>(connectionHandler,null);
                futureTask.run();
            } catch (IOException ignore) {

            }
        }
    }
}
