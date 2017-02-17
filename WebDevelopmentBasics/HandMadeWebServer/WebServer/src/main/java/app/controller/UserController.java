package app.controller;

import app.view.UserEditView;
import server.Model;
import server.http.response.HttpResponse;
import server.http.response.HttpResponseCode;
import server.http.response.ViewResponse;
import server.routing.annotations.Controller;
import server.routing.annotations.RequestMapping;
import server.routing.annotations.UriParameter;

@Controller
public class UserController {

    @RequestMapping("/welcome/{name}")
    public HttpResponse edit(@UriParameter("name") String name){
        Model model = new Model();
        model.add("username",name);
        return new ViewResponse(HttpResponseCode.OK,new UserEditView(model));
    }
}
