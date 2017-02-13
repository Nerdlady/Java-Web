package server;

import server.constants.Constants;
import server.exeptions.BadRequestException;
import server.http.HttpContext;
import server.http.HttpContextImpl;
import server.http.HttpHandler;
import server.http.HttpSession;
import server.routing.ServerRouteConfig;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final PrintWriter printWriter;
    private final ServerRouteConfig serverRouteConfig;
    private final Map<String, HttpSession> sessions;

    public ConnectionHandler(Socket socket, ServerRouteConfig serverRouteConfig, Map<String, HttpSession> sessions) throws IOException {
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.printWriter = new PrintWriter(this.socket.getOutputStream());
        this.serverRouteConfig = serverRouteConfig;
        this.sessions = sessions;
    }

    @Override
    public void run() {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while (this.bufferedReader.ready() || stringBuilder.length() == 0) {
                stringBuilder.append((char) this.bufferedReader.read());
            }

            try {
                HttpContext httpContext = new HttpContextImpl(stringBuilder.toString(), this.sessions);
                new HttpHandler(this.serverRouteConfig, this.printWriter).handle(httpContext);

                HttpSession httpSession = httpContext.getHttpRequest().getSession();
                this.sessions.put(httpSession.getId(), httpSession);
            } catch (BadRequestException e) {
                this.printWriter.write(Constants.BAD_REQUEST);
            } catch (FileNotFoundException e) {
                this.printWriter.write(Constants.NOT_FOUND);
            } catch (Exception e) {
                this.printWriter.write(Constants.SERVER_ERROR);
            }

            this.printWriter.close();
            this.bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
