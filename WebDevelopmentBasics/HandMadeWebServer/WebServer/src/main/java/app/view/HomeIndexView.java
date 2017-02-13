package app.view;

import server.view.View;

public class HomeIndexView implements View {
    @Override
    public String view() {
        return "<html><body><h2>It works</h2></body></html>";
    }
}
