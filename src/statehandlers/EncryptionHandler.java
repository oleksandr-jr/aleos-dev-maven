package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;
import prompt.ModeHeaderPrompt;
import statehandler.StateManager;

import static statehandler.State.ENCRYPT;
import static statehandler.State.GET_FILE;

public class EncryptionHandler implements StateHandler {
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
