package org.example.services;

import org.example.App;
import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.model.Book;

public class BookService {
    private final Input input;
    private final BookProvider bookProvider;


    public BookService(Input input, BookProvider bookProvider) {
        this.input = input;
        this.bookProvider = bookProvider;
    }

    public void add() {
        Book book = bookProvider.create(input);
        for (int i = 0; i < App.books.length; i++) {
            if(i == 0 && App.books[i] == null) {
                App.books[i] = book;
                System.out.println("Книга добавлена!");
                break;
            }
        }
    }

    public void printListBooks() {
        System.out.println(bookProvider.getList());
    }
}

