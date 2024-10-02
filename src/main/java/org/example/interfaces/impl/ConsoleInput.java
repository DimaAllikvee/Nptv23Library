package org.example.interfaces.impl;

import org.example.interfaces.InputProvider;

public class ConsoleInput  implements InputProvider {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public String getInput() {
        return scanner.nextLine();
    }
}
