package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;
import prompt.ModeHeaderPrompt;
import statehandler.StateManager;

import static statehandler.State.DECRYPT;
import static statehandler.State.GET_ENCRYPTED_FILE;

public class DecryptionHandler implements StateHandler {
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public DecryptionHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.clearMenu();
        consoleManager.updateMenu(ModeHeaderPrompt.DECRYPT_HEADER.getMessage());

        stateManager.setRoot(DECRYPT);
        stateManager.setCurrent(GET_ENCRYPTED_FILE);
    }
}
