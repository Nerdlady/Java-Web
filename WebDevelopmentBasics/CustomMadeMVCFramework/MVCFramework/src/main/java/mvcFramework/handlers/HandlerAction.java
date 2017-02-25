package mvcFramework.handlers;

import mvcFramework.controller.ControllerActionPair;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface HandlerAction {
    String executeControllerAction(HttpServletRequest request, HttpServletResponse response, ControllerActionPair controllerActionPair)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, NamingException;
}
