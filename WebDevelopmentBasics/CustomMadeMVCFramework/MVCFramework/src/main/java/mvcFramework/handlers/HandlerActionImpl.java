package mvcFramework.handlers;

import mvcFramework.annotations.parameters.GetCookie;
import mvcFramework.annotations.parameters.ModelAttribute;
import mvcFramework.annotations.parameters.PathVariable;
import mvcFramework.annotations.parameters.RequestParam;
import mvcFramework.controller.ControllerActionPair;
import mvcFramework.models.Model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.*;
import java.math.BigDecimal;
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
    public String executeControllerAction(HttpServletRequest request,HttpServletResponse response, ControllerActionPair controllerActionPair) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, NamingException {

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

            if (parameter.isAnnotationPresent(ModelAttribute.class)){
                argument = this.getModelAttributeValue(parameter,request);
            }

            if(parameter.getType().isAssignableFrom(HttpSession.class)){
                argument = request.getSession();
            }

            if(parameter.getType().isAssignableFrom(Cookie[].class)){
                argument = request.getCookies();
            }

            if (parameter.isAnnotationPresent(GetCookie.class)){
                GetCookie cookie = parameter.getAnnotation(GetCookie.class);
                argument = this.findCookie(cookie,request.getCookies());
            }

            if(parameter.getType().isAssignableFrom(HttpServletResponse.class)){
                argument = response;
            }

            objects.add(argument);
        }

        Context context = new InitialContext();
        Object controllerInstance = context.lookup("java:global/" + controller.getSimpleName());

        return (String) method.invoke(controllerInstance, objects.toArray());
    }

    private Object findCookie(GetCookie cookie, Cookie[] cookies) {
        Cookie findCookie = null;
        for (Cookie cookie1 : cookies) {
            if (cookie1.getName().equals(cookie.value())){
                findCookie = cookie1;
                break;
            }
        }
        return findCookie;
    }

    private Object getModelAttributeValue(Parameter parameter, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        Class bindingModelClass = parameter.getType();
        Object bindingModelObject = bindingModelClass.newInstance();
        Field[] fields = bindingModelClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String fieldValue = request.getParameter(fieldName);
            if (fieldValue != null){
                if (field.getType().isEnum()){
                    field.set(bindingModelObject, Enum.valueOf((Class<Enum>) field.getType(), fieldValue.toUpperCase()));
                } else{
                    field.set(bindingModelObject,this.typeConversions.get(field.getType()).apply(fieldValue));
                }
            }
        }
        return bindingModelObject;
    }

    private Object getPathVariableValue(Parameter parameter, PathVariable pathVariableAnnotation, ControllerActionPair controllerActionPair) {
        String value = pathVariableAnnotation.value();
        String pathVariable = controllerActionPair.getPathVariable(value);
        return this.typeConversions.get(parameter.getType()).apply(pathVariable);
    }

    private Object getParameterValue(Parameter parameter, RequestParam requestParamAnnotationClass, HttpServletRequest request) throws IllegalAccessException, InstantiationException {
        String value = requestParamAnnotationClass.value();
        String pathVariable = request.getParameter(value);
        return  this.typeConversions.get(parameter.getType()).apply(pathVariable);
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
            put(BigDecimal.class,BigDecimal::new);
        }};
    }

}
