package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.FileRepository;

import java.util.List;

public class BookService implements Service<Book>, FileRepository {

    private final AppHelper<Book> appHelperBook;
    private final String fileName="books";

    public BookService( AppHelper<Book> appHelperBook) {
        this.appHelperBook = appHelperBook;
    }

    @Override
    public boolean add() {
        try {
            Book book = appHelperBook.create();
            if(book == null) {return false;}
            save(book,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit(Book book) {
        return false;
    }

    @Override
    public boolean remove(Book book) {
        return false;
    }

    @Override
    public void print() {
        appHelperBook.printList(this.list());
    }

    @Override
    public List<Book> list() {
        return load(fileName);
    }
}
