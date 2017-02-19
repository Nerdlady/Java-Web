package bookhut.controllers;

import bookhut.models.binding.AddBookModel;
import bookhut.models.view.ViewBookModel;
import bookhut.services.BookService;
import bookhut.servicesImpl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/shelves/edit/*")
public class EditBookController extends HttpServlet {
    private BookService bookService;

    public EditBookController() {
        this.bookService = new BookServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String edit = request.getParameter("edit");
        if(edit != null){
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            int pages = Integer.valueOf(request.getParameter("pages"));
            AddBookModel addBookModel = new AddBookModel(title, author, pages);
            this.bookService.saveBook(addBookModel);
            response.sendRedirect("/shelves");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] tokens = request.getRequestURI().split("/");
        String title = URLDecoder.decode(tokens[3],"UTF-8");
        ViewBookModel viewBookModel = this.bookService.findBookByTitle(title);
        if (viewBookModel != null){
            request.setAttribute("book",viewBookModel);
            request.getRequestDispatcher("/jsp/edit.jsp").forward(request,response);
        }
    }
}
