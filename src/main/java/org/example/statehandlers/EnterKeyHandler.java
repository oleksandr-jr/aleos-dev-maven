package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.cryptoanalizer.caesarcipher.CaesarCipher;
import org.example.CipherMachine;
import org.example.prompt.OperationPrompt;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.*;

public class EnterKeyHandler implements StateHandler {
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;
    private final CaesarCipher caesarCipher;

    public EnterKeyHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
        this.caesarCipher = cipherMachine.getCipher();
    }

    @Override
    public void handle(String inputCommand) {
        if (!isValidInteger(inputCommand)) {
            consoleManager.updateMenu(
                    String.format(OperationPrompt.INVALID_KEY.getMessage(), inputCommand),
                    OperationPrompt.POSSIBLE_OPTIONS.getMessage());

            return;
        }

        int key = Integer.parseInt(inputCommand);
        if (key == 0) {
            consoleManager.updateMenu(OperationPrompt.KEY_CANNOT_BE_ZERO.getMessage());
            return;
        }

        consoleManager.updateMenu(inputCommand);

        if (stateManager.getRoot() == ENCRYPT) {
            consoleManager.updateMenu(String.format(OperationPrompt.FILE_IS_ENCRYPTED.getMessage(), key));
        } else if (stateManager.getRoot() == DECRYPT) {
            consoleManager.updateMenu(String.format(OperationPrompt.FILE_IS_DECRYPTED.getMessage(), key));
        }

        caesarCipher.setKey(Math.abs(key));

        stateManager.setCurrent(CIPHER_PROCESS);
    }

    private boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
