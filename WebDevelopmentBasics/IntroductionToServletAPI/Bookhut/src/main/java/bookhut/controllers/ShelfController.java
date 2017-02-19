package bookhut.controllers;

import bookhut.models.view.ViewBookModel;
import bookhut.services.BookService;
import bookhut.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/shelves")
public class ShelfController extends HttpServlet {
    private BookService bookService;

    public ShelfController() {
        this.bookService = new BookServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ViewBookModel> viewBookModels = this.bookService.getAllBooks();
        request.setAttribute("books",viewBookModels);
        request.getRequestDispatcher("jsp/shelves.jsp").forward(request,response);
    }
}
