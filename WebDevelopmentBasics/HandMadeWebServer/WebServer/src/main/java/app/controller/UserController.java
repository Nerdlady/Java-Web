package app.controller;

import app.view.UserEditView;
import server.Model;
import server.http.response.HttpResponse;
import server.http.response.HttpResponseCode;
import server.http.response.ViewResponse;

public class UserController {
    public HttpResponse edit(String name){
        Model model = new Model();
        model.add("username",name);
        return new ViewResponse(HttpResponseCode.OK,new UserEditView(model));
    }
}
