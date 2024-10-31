package ee.ivkhkdev;


import ee.ivkhkdev.interfaces.AppHelper;
import ee.ivkhkdev.apphelpers.AuthorAppHelper;
import ee.ivkhkdev.apphelpers.BookAppHelper;
import ee.ivkhkdev.model.Author;
import ee.ivkhkdev.model.Book;
import ee.ivkhkdev.services.AuthorService;
import ee.ivkhkdev.services.BookService;
import ee.ivkhkdev.interfaces.Service;

import java.util.ArrayList;
import java.util.List;


public class NPTV23Library {

    public static void main(String[] args) {
        AppHelper<Author> appHelperAuthor = new AuthorAppHelper();
        Service<Author> authorService = new AuthorService(appHelperAuthor);
        AppHelper<Book> appHelperBook = new BookAppHelper(authorService);
        Service<Book> bookService = new BookService(appHelperBook);
        App app = new App(bookService, authorService);
        app.run();
    }

}