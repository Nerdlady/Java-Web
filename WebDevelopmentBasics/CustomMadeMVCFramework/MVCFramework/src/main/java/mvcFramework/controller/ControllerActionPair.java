package mvcFramework.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerActionPair {
    private Class controllerClass;
    private Method action;
    private Map<String,String>  pathVariables;

    public ControllerActionPair(Class controllerClass, Method action) {
        this.controllerClass = controllerClass;
        this.action = action;
        this.pathVariables = new HashMap<>();
    }

    public Class getControllerClass() {
        return controllerClass;
    }

    public Method getAction() {
        return action;
    }

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public void addPathVariable(String key, String value) {
        this.pathVariables.put(key,value);
    }

    public String getPathVariable(String key) {
        return this.pathVariables.get(key);
    }
}
