package mvcFramework.dispatcher;

import mvcFramework.controller.ControllerActionPair;

import javax.servlet.http.HttpServletRequest;

public interface Dispatcher {
    ControllerActionPair dispatchRequest(HttpServletRequest request);

    String dispatchAction(HttpServletRequest request, ControllerActionPair controllerActionPair);
}
