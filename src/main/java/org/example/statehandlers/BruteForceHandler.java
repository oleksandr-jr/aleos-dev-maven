package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.CipherMachine;
import org.example.prompt.ModeHeaderPrompt;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.BRUTE_FORCE;
import static org.example.statehandler.State.GET_ENCRYPTED_FILE;

public class BruteForceHandler implements StateHandler {
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public BruteForceHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.clearMenu();
        consoleManager.updateMenu(ModeHeaderPrompt.BRUTE_FORCE_HEADER.getMessage());

        stateManager.setRoot(BRUTE_FORCE);
        stateManager.setCurrent(GET_ENCRYPTED_FILE);
    }
}
