package org.example;

import org.example.handlers.BookHandler;
import org.example.interfaces.BookProvider;
import org.example.interfaces.InputProvider;
import org.example.interfaces.impl.ConsoleInput;
import org.example.interfaces.impl.InputBook;

public class Nptv23Library {
    public static void main(String[] args) {

        InputProvider inputProvider = new ConsoleInput();
        BookProvider bookProvider = new InputBook();
        BookHandler bookHandler = new BookHandler(inputProvider, bookProvider);

        System.out.println("-------Nptv23Library-------");
        App app = new App(bookHandler, inputProvider);
        app.run();
    }
}