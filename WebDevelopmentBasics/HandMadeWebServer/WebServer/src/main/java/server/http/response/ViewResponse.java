package server.http.response;

import server.view.View;

public class ViewResponse extends HttpResponseImpl {
    public ViewResponse(HttpResponseCode responseCode, View view) {
        super(responseCode, view);
    }
}
