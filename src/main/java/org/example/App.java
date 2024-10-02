package org.example;

import org.example.handlers.BookHandler;
import org.example.interfaces.InputProvider;
import org.example.services.BookService;

import java.util.Scanner;

public class App {

    private final BookHandler bookHandler;
    private final InputProvider inputProvider;

    public App(BookHandler bookHandler, InputProvider inputProvider) {
        this.bookHandler = bookHandler;
        this.inputProvider = inputProvider;
    }

    public void run() {
        boolean repeat = true;
        do {
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.print("Введите номер задачи: ");
            int task = ;
            switch (task) {
                case 0:
                    System.out.println("Программа завершена");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("------Добавление книги-----");
                    bookHandler.addBook();
                    break;
                default:
                    System.out.println("Выберите существующую задачу:");

            }
        } while (repeat);
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
