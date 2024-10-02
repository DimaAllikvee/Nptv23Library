package org.example.handlers;

import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Book;

public class BookHandler {
    private final InputProvider inputProvider;
    private final BookProvider bookProvider;


    public BookHandler(InputProvider inputProvider, BookProvider bookProvider) {
        this.inputProvider = inputProvider;
        this.bookProvider = bookProvider;
    }

    public void addBook() {
        Book book = bookProvider.createBook(inputProvider);
    }
}
