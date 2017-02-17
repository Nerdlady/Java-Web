package app.controller;

import app.view.HomeIndexView;
import server.http.response.HttpResponse;
import server.http.response.HttpResponseCode;
import server.http.response.ViewResponse;
import server.routing.annotations.Controller;
import server.routing.annotations.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public HttpResponse index(){
        return new ViewResponse(HttpResponseCode.OK,new HomeIndexView());
    }
}
