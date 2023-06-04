package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.CipherMachine;

public class MenuStateHandler implements StateHandler {
    private final ConsoleManager consoleManager;

    public MenuStateHandler(CipherMachine machine) {
        this.consoleManager = machine.getConsoleManager();
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.clearPreview();
        consoleManager.callMenu();
    }
}
