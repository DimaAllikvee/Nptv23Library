package org.example.interfaces.impl;

import org.example.interfaces.BookProvider;
import org.example.model.Book;

public class InputBook implements BookProvider {
    @Override
    public Book createBook() {
        Book book = new Book();
        book.setTitle("Война и мир");
        return book;
    }
}
