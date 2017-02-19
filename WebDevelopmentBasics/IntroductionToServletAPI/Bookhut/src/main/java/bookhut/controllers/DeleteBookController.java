package bookhut.controllers;

import bookhut.services.BookService;
import bookhut.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/shelves/delete/*")
public class DeleteBookController extends HttpServlet {
    private BookService bookService;

    public DeleteBookController() {
        this.bookService = new BookServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tokens[] = request.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[3], "UTF-8");
        this.bookService.deleteBookByTitle(title);
        response.sendRedirect("/shelves");
    }
}
