package bookhut.services;

import bookhut.models.binding.AddBookModel;
import bookhut.models.view.ViewBookModel;

import java.util.List;

public interface BookService {

    void saveBook(AddBookModel addBookModel);

    List<ViewBookModel> getAllBooks();

    ViewBookModel findBookByTitle(String title);

    void deleteBookByTitle(String title);

}
