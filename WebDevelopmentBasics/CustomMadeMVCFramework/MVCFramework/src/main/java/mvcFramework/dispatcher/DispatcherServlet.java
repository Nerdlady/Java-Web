package mvcFramework.dispatcher;

import mvcFramework.controller.ControllerActionPair;
import mvcFramework.handlers.HandlerAction;
import mvcFramework.handlers.HandlerActionImpl;
import mvcFramework.handlers.HandlerMapping;
import mvcFramework.handlers.HandlerMappingImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet implements Dispatcher {
    private HandlerMapping handlerMapping;
    private HandlerAction handlerAction;

    public DispatcherServlet() {
        this.handlerMapping = new HandlerMappingImpl();
        this.handlerAction = new HandlerActionImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isResource(req)) {
            this.sendResourceResponse(req, resp);
            return;
        }

        this.handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.handleRequest(req,resp);
    }

    @Override
    public ControllerActionPair dispatchRequest(HttpServletRequest request) {
        ControllerActionPair controllerActionPair = null;
        try {
            controllerActionPair = this.handlerMapping.findController(request);
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return controllerActionPair;
    }

    @Override
    public String dispatchAction(HttpServletRequest request, ControllerActionPair controllerActionPair) {
        String view = null;
        try {
            view = this.handlerAction.executeControllerAction(request, controllerActionPair);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ControllerActionPair controllerActionPair = this.dispatchRequest(request);
        if (controllerActionPair != null){
            String view = this.dispatchAction(request, controllerActionPair);
            try {
                if (view.startsWith("redirect:")) {
                    String redirectPath = view.replace("redirect:", "");
                    response.sendRedirect(redirectPath);
                } else {
                    request.getRequestDispatcher("/" + view + ".jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendResourceResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = request.getRequestURI();
        String directory = URLDecoder.decode(request.getServletContext().getResource("/").getPath(), "UTF-8");
        File file = new File(directory + url);
        try (
                BufferedReader bfr = new BufferedReader(new FileReader(file))
        ) {
            String line;
            while ((line = bfr.readLine()) != null) {
                response.getWriter().print(line);
            }
        }
    }

    private boolean isResource(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.contains(".");
    }
}
