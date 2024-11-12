package ee.ivkhkdev.services;

import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.interfaces.Service;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.interfaces.FileRepository;

import java.util.List;

public class BookService implements Service<Book> {

    private final AppHelper<Book> appHelperBook;
    private final String fileName="books";
    private final FileRepository<Book>  storage;

    public BookService(AppHelper<Book> appHelperBook, FileRepository<Book> storage) {
        this.appHelperBook = appHelperBook;
        this.storage = storage;
    }

    @Override
    public boolean add() {
        try {
            Book book = appHelperBook.create();
            if(book == null) {return false;}
            storage.save(book,fileName);
            return true;
        }catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean edit() {
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
    public boolean print() {

        return appHelperBook.printList(this.list());
    }

    @Override
    public List<Book> list() {
        return storage.load(fileName);
    }
}
