package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;
import prompt.ModeHeaderPrompt;
import statehandler.StateManager;

import static statehandler.State.BRUTE_FORCE;
import static statehandler.State.GET_ENCRYPTED_FILE;

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
