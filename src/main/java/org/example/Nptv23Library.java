package org.example;

import org.example.interfaces.UserProvider;
import org.example.interfaces.impl.AppHelperUsers;
import org.example.services.BookService;
import org.example.interfaces.BookProvider;
import org.example.interfaces.Input;
import org.example.interfaces.impl.ConsoleInput;
import org.example.interfaces.impl.AppHelperBooks;

public class Nptv23Library {
    public static void main(String[] args) {
        // Инициализируем ввод
        Input input = new ConsoleInput();

        // Создаем провайдер книг
        BookProvider bookProvider = new AppHelperBooks();
        BookService bookService = new BookService(input, bookProvider);

        // Создаем провайдер пользователей
        UserProvider userProvider = new AppHelperUsers(); // Убедитесь, что у вас есть реализация UserProvider
        UserService userService = new UserService(userProvider);

        // Создаем и запускаем приложение
        App app = new App(bookService, userService, input);
        app.run();
    }
}
