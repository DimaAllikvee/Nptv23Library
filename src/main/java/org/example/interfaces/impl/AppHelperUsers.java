package org.example.interfaces.impl;

import org.example.App;
import org.example.interfaces.UserProvider; // Убедитесь, что у вас есть этот интерфейс
import org.example.interfaces.Input;
import org.example.model.User;

public class AppHelperUsers implements UserProvider {
    @Override
    public User create(Input input) {
        try {
            User user = new User(); // Создаем пользователя
            System.out.print("Имя: ");
            user.setFirstname(input.getString()); // Используйте соответствующие методы
            System.out.print("Фамилия: ");
            user.setLastname(input.getString());
            System.out.print("Телефон: ");
            user.setPhone(input.getString());
            return user; // Возвращаем пользователя
        } catch (Exception e) {
            System.out.println("Ошибка создания пользователя");
            return null;
        }

    }

    @Override
    public String getList(){
        return null;
    }

}
