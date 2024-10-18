package org.example.interfaces.impl;

import org.example.interfaces.Input;

import java.util.Scanner;

public class ConsoleInput  implements Input {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String getString() {
        return scanner.nextLine();
    }
}
