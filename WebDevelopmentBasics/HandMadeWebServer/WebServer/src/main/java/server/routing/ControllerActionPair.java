package server.routing;

import java.lang.reflect.Method;
import java.util.Map;

public class ControllerActionPair {
    private final Method action;
    private final Object controller;
    private final Map<Integer,Class> argumentMapping;

    public ControllerActionPair(Method action, Object controller, Map<Integer, Class> argumentMapping) {
        this.action = action;
        this.controller = controller;
        this.argumentMapping = argumentMapping;
    }

    public Method getAction() {
        return action;
    }

    public Object getController() {
        return controller;
    }

    public Map<Integer, Class> getArgumentMapping() {
        return argumentMapping;
    }
}
