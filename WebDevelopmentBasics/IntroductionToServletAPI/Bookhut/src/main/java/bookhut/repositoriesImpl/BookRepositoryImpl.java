package bookhut.repositoriesImpl;

import bookhut.entities.Book;
import bookhut.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static BookRepositoryImpl bookRepository;
    private List<Book> books;

    private BookRepositoryImpl() {
        this.books = new ArrayList<>();
    }

    public static BookRepository getInstance() {
        if (bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }

        return bookRepository;
    }

    @Override
    public void saveBook(Book book) {
        Book currentBook = this.books
                .stream()
                .filter(b -> b.getTitle().equals(book.getTitle()))
                .findFirst()
                .orElse(null);
        if (currentBook != null) {
            this.books.remove(currentBook);
        }

        this.books.add(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(this.books);
    }

    @Override
    public void deleteBookByTitle(String title) {
        Book book = this.findBookByTitle(title);
        this.books.remove(book);

    }

    @Override
    public Book findBookByTitle(String title) {
        Book book = this.books
                .stream()
                .filter(b -> b.getTitle().equals(title)).findFirst()
                .orElse(null);
        return book;
    }
}
