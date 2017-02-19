package bookhut.servicesImpl;

import bookhut.entities.Book;
import bookhut.models.binding.AddBookModel;
import bookhut.models.view.ViewBookModel;
import bookhut.repositories.BookRepository;
import bookhut.repositoriesImpl.BookRepositoryImpl;
import bookhut.services.BookService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl() {
        this.bookRepository = BookRepositoryImpl.getInstance();
    }

    @Override
    public void saveBook(AddBookModel addBookModel) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(addBookModel,Book.class);
        this.bookRepository.saveBook(book);
    }

    @Override
    public List<ViewBookModel> getAllBooks() {
        List<Book> books = this.bookRepository.getAllBooks();
        ModelMapper modelMapper = new ModelMapper();
        List<ViewBookModel> viewBookModels = new ArrayList<>();
        for (Book book : books) {
            ViewBookModel viewBookModel = modelMapper.map(book, ViewBookModel.class);
            viewBookModels.add(viewBookModel);
        }

        return viewBookModels;
    }

    @Override
    public ViewBookModel findBookByTitle(String title) {
        Book book = this.bookRepository.findBookByTitle(title);
        ModelMapper modelMapper = new ModelMapper();
        ViewBookModel viewBookModel = null;
        if (book != null){
            viewBookModel = modelMapper.map(book,ViewBookModel.class);
        }
        return viewBookModel;
    }

    @Override
    public void deleteBookByTitle(String title) {
        this.bookRepository.deleteBookByTitle(title);
    }
}
