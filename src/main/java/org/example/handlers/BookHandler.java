package org.example.handlers;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.model.Book;

import java.util.Arrays;

public class BookHandler {
    private final InputProvider inputProvider;
    private final BookProvider bookProvider;


    public BookHandler(InputProvider inputProvider, BookProvider bookProvider) {
        this.inputProvider = inputProvider;
        this.bookProvider = bookProvider;
    }

    public void addBook() {
        Book book = bookProvider.createBook(inputProvider);
        for (int i = 0; i < App.books.length; i++) {
            if(i == 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена!");
                break;
            }else if (i > 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена!");
                break;
            }
        }
    }

    public void printListBooks() {
        bookProvider.getList();
    }
}

