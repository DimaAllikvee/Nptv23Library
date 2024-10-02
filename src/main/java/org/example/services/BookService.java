package org.example.services;

import org.example.model.Author;
import org.example.model.Book;

public class BookService {
    public Book createBook() {
        Author author = new Author("Лев", "Толстой");
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setPublishedYear(2000);
        book.getAuthors()[0] = (author);
        return book;

    }
}
