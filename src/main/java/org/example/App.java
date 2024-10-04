package org.example;

import org.example.handlers.BookHandler;
import org.example.interfaces.InputProvider;
import org.example.model.Book;



public class App {

    public static Book[] books = new Book[100];
    private final BookHandler bookHandler;
    private final InputProvider inputProvider;

    public App(BookHandler bookHandler, InputProvider inputProvider) {
        this.bookHandler = bookHandler;
        this.inputProvider = inputProvider;
    }

    public void run() {
        System.out.println("-------Библиотека группы NPTV23--------");
        System.out.println("---------------------------------------");
        boolean repeat = true;
        do {
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(inputProvider.getInput());
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
            System.out.println("---------------------------------------");
        } while (repeat);
    }

}

