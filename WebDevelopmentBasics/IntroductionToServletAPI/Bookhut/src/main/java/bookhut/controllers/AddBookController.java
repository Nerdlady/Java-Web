package bookhut.controllers;

import bookhut.models.binding.AddBookModel;
import bookhut.services.BookService;
import bookhut.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddBookController extends HttpServlet {
    private BookService bookService;

    public AddBookController() {
        this.bookService = new BookServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String add = request.getParameter("add");
        if (add != null){
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            Integer pages = Integer.parseInt(request.getParameter("pages"));
            AddBookModel addBookModel = new AddBookModel(title,author,pages);
            this.bookService.saveBook(addBookModel);
            response.sendRedirect("/shelves");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("jsp/add.jsp").forward(request,response);
    }
}
