package app.view;

import server.Model;
import server.view.View;

public class UserEditView implements View {

    private Model model;

    public UserEditView(Model model) {
        this.model = model;
    }

    @Override
    public String view() {
        return String.format("<html><body><h3>Edit user %s</h3></body></html>",model.get("username"));
    }
}
