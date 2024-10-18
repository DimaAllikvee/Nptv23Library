package org.example;

import org.example.interfaces.Input;
import org.example.interfaces.UserProvider;
import org.example.model.User;


public class UserService {
    private final UserProvider userProvider;

    // Конструктор для инициализации UserProvider
    public UserService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    // Метод для добавления пользователя
    public boolean add(Input input) {
        try {
        User user = userProvider.create(input); // Создаем пользователя через UserProvider
            if (user == null) {
                return false;
            }
        // Ищем первое пустое место в массиве пользователей
        for (int i = 0; i < App.users.length; i++) {
            if (App.users[i] == null) { // Проверяем на null, чтобы найти пустое место
                App.users[i] = user; // Добавляем пользователя
                System.out.println("Пользователь добавлен!");
                break;
            }
        }
    } catch (Exception e) {
        System.out.println("Ошибка добавления пользователя");
        return false;
    }
        return true;
    }
}

