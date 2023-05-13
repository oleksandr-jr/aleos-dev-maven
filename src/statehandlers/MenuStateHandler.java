package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;

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
