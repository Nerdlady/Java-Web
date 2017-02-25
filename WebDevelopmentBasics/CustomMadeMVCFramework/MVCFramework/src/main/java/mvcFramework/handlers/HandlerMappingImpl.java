package mvcFramework.handlers;

import mvcFramework.annotations.controller.Controller;
import mvcFramework.annotations.request.GetMapping;
import mvcFramework.annotations.request.PostMapping;
import mvcFramework.controller.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerMappingImpl implements HandlerMapping {
    @Override
    public ControllerActionPair findController(HttpServletRequest request) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String urlPath = request.getRequestURI();
        String projectPath = URLDecoder.decode(request.getServletContext().getResource("/WEB-INF/classes").getPath(), "UTF-8");
        List<Class> controllers = this.findAllControllers(projectPath);
        for (Class controller : controllers) {
            Method[] methods = controller.getDeclaredMethods();
            for (Method method : methods) {
                String methodPath = this.findMethodPath(request,method);
                if (methodPath == null) {
                    continue;
                }

                if (this.isPathMatching(urlPath,methodPath)) {
                    ControllerActionPair controllerActionPair = new ControllerActionPair(controller, method);
                    this.addPathVariables(controllerActionPair, urlPath, methodPath);
                    return controllerActionPair;
                }
            }
        }

        return null;
    }

    private void addPathVariables(ControllerActionPair controllerActionPair, String urlPath, String methodPath) {
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");
        for (int i = 0; i < uriTokens.length; i++) {
            if (this.isPathVariable(methodTokens[i])) {
                String key = methodTokens[i].replace("{","").replace("}","");
                String value = uriTokens[i];
                controllerActionPair.addPathVariable(key,value);
            }
        }
    }


    private boolean isPathMatching(String urlPath, String methodPath) {
        String[] uriTokens = urlPath.split("/");
        String[] methodTokens = methodPath.split("/");
        if (uriTokens.length != methodTokens.length) {
            return false;
        }

        for (int i = 0; i < uriTokens.length; i++) {
            if (this.isPathVariable(methodTokens[i])) {
                continue;
            }

            if (!uriTokens[i].endsWith(methodTokens[i])) {
                return false;
            }
        }

        return true;
    }

    private String findMethodPath(HttpServletRequest request, Method method) throws IllegalAccessException, InstantiationException {
        String value = null;
        switch (request.getMethod().toLowerCase()) {
            case "get":
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                if (getMapping != null) {
                    value = getMapping.value();
                }
                break;
            case "post":
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                if (postMapping != null) {
                    value = postMapping.value();
                }
                break;
        }
        return value;
    }

    private List<Class> findAllControllers(String projectDirectory) throws ClassNotFoundException, IOException {
        List<Class> controllerClasses = new ArrayList<>();
        File directory = new File(projectDirectory);
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                Class controller = this.getClass(file);
                if (controller != null) {
                    if (controller.isAnnotationPresent(Controller.class)) {
                        controllerClasses.add(controller);
                    }
                }
            } else if (file.isDirectory()) {
                controllerClasses.addAll(this.findAllControllers(file.getAbsolutePath()));
            }
        }

        return controllerClasses;
    }

    private Class getClass(File file) throws ClassNotFoundException {
        String absolutePath = file.getAbsolutePath();
        String classPattern = "^(.+classes\\\\)(.+)(.class)$";
        Pattern pattern = Pattern.compile(classPattern);
        Matcher matcher = pattern.matcher(absolutePath);
        Class currentClass = null;
        if (matcher.find()) {
            String className = matcher.group(2).replace("\\", ".");
            if (!className.endsWith("DispatcherServlet")) {
                currentClass = Class.forName(className);
            }
        }

        return currentClass;
    }

    private boolean isPathVariable(String s) {
        return s.startsWith("{") && s.endsWith("}");
    }

}
