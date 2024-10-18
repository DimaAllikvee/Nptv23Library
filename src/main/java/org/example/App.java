package org.example;

import org.example.model.User;
import org.example.services.BookService;
import org.example.interfaces.Input;
import org.example.model.Book;




public class App {

    public static Book[] books = new Book[100];
    public static User[] users = new User[100];
    private final BookService bookService;
    private final org.example.UserService userService;
    private final Input input;

    public App(BookService bookService, UserService userService, Input input) {
        this.bookService = bookService;
        this.userService = userService;
        this.input = input;
    }

    public void run() {
        System.out.println("-------Библиотека группы NPTV23--------");
        System.out.println("---------------------------------------");
        boolean repeat = true;
        do {
            System.out.println("Список задач: ");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список книг");
            System.out.println("3. Добавить пользователя");
            System.out.print("Введите номер задачи: ");
            int task = Integer.parseInt(input.getString());
            switch (task) {
                case 0:
                    System.out.println("Программа завершена");
                    repeat = false;
                    break;
                case 1:
                    System.out.println("------Добавление книги-----");
                    bookService.add();
                    break;
                case 2:
                    System.out.println("------Список книг-----");
                    System.out.println(bookService);
                    bookService.printListBooks();
                    break;

                case 3:
                    System.out.println("------Добавление пользователя-----");
                    if (userService.add(input)) {
                        System.out.println("Пользователь добавлен!");
                    } else {
                        System.out.println("Ошибка добавления пользователя");
                    }
                    break;
                default:
                    System.out.println("Выберите существующую задачу:");
            }
            System.out.println("---------------------------------------");
        } while (repeat);
    }

}

