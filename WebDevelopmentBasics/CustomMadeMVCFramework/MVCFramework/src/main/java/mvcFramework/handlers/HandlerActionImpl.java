package mvcFramework.handlers;

import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.controller.ControllerActionPair;
import mvcFramework.models.Model;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class HandlerActionImpl implements HandlerAction {

    private HashMap<Class, Function<String, Object>> typeConversions;

    public HandlerActionImpl() {
        this.fillTypeConversions();
    }

    @Override
    public String executeControllerAction(HttpServletRequest request, ControllerActionPair controllerActionPair) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Class controller = controllerActionPair.getControllerClass();
        Method method = controllerActionPair.getAction();
        Parameter[] parameters = method.getParameters();
        List<Object> objects = new ArrayList<>();

        for (Parameter parameter : parameters) {
            Object argument = null;
            if (parameter.isAnnotationPresent(PathVariable.class)) {
                PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
                argument = this.getPathVariableValue(parameter, pathVariable, controllerActionPair);
            }

            if (parameter.isAnnotationPresent(RequestParam.class)) {
                RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
                argument = this.getParameterValue(parameter, requestParam, request);
            }

            if (parameter.getType().isAssignableFrom(Model.class)) {
                Constructor constructor = parameter.getType().getConstructor(HttpServletRequest.class);
                argument = constructor.newInstance(request);
            }
            objects.add(argument);
        }

        return (String) method.invoke(controller.newInstance(), objects.toArray());
    }

    private <T> T getPathVariableValue(Parameter parameter, PathVariable pathVariableAnnotation, ControllerActionPair controllerActionPair) {
        String value = pathVariableAnnotation.value();
        String pathVariable = controllerActionPair.getPathVariable(value);
        return (T) this.typeConversions.get(parameter.getType()).apply(pathVariable);
    }

    private <T> T getParameterValue(Parameter parameter, RequestParam requestParamAnnotationClass, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        String value = requestParamAnnotationClass.value();
        String pathVariable = request.getParameter(value);
        return (T) this.typeConversions.get(parameter.getType()).apply(pathVariable);
    }


    private void fillTypeConversions() {
        this.typeConversions = new HashMap<Class, Function<String, Object>>() {{
            put(String.class, s -> s);
            put(Integer.class, Integer::parseInt);
            put(int.class, Integer::parseInt);
            put(double.class, Double::parseDouble);
            put(Double.class, Double::parseDouble);
            put(Long.class, Long::parseLong);
            put(long.class, Long::parseLong);
        }};
    }

}
