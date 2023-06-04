package org.example.statehandlers;

import org.example.CipherMachine;
import org.example.console.ConsoleManager;
import org.example.prompt.ModeHeaderPrompt;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.ENCRYPT;
import static org.example.statehandler.State.GET_FILE;


public class EncryptionHandler implements org.example.statehandlers.StateHandler {
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public EncryptionHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.clearMenu();
        consoleManager.updateMenu(ModeHeaderPrompt.ENCRYPT_HEADER.getMessage());

        stateManager.setRoot(ENCRYPT);
        stateManager.setCurrent(GET_FILE);
    }
}
