package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class AppTest {

    private final InputStream systemIn = System.in;  // Сохранение оригинального System.in

    @BeforeEach
    public void setUp() {
        // Восстановление стандартного System.in перед каждым тестом
        System.setIn(systemIn);
    }

    @AfterEach
    public void restoreSystemIn() {
        // Восстановление System.in после каждого теста
        System.setIn(systemIn);
    }

    @Test
    public void givenZeroInput_whenRun_thenExit() {
        String input = "0\n";  // Добавляем символ новой строки, чтобы завершить ввод
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        App app = new App();
        app.run();  // Запуск программы с вводом "0" для выхода
    }

    @Test
    public void givenInvalidTaskInput_whenRun_thenHandleErrorAndExit() {
        String input = "1\n0\n";  // Некорректный ввод "1", затем "0" для выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        App app = new App();
        app.run();  // Запуск программы с некорректным вводом и последующим выходом
    }
    @Test
    public void givenTwoInput_whenRun_thenHandleTask() {
        String input = "2\n0\n";  // Ввод "2", затем "0" для выхода
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        App app = new App();
        app.run();  // Запуск программы с вводом "2" и последующим выходом
    }
}


