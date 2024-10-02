package org.example.interfaces.impl;

import org.example.interfaces.InputProvider;

import java.util.Scanner;

public class ConsoleInput  implements InputProvider {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
