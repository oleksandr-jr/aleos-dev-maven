package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.cryptoanalizer.caesarcipher.CaesarCipher;
import org.example.CipherMachine;
import org.example.statehandler.State;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.ENCRYPT;
import static org.example.statehandler.State.SAVE_TO_FILE_PROCESS;

public class CipherProcessHandler implements org.example.statehandlers.StateHandler {

    private final CipherMachine cipherMachine;
    private final CaesarCipher caesarCipher;
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public CipherProcessHandler(CipherMachine cipherMachine) {
        this.cipherMachine = cipherMachine;
        this.caesarCipher = cipherMachine.getCipher();
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();

    }

    @Override
    public void handle(String inputCommand) {
        char[] textOfFileAsChars = cipherMachine.getFileText().toCharArray();

        State initialState = stateManager.getRoot();

        if (initialState == ENCRYPT) {
            caesarCipher.encrypt(textOfFileAsChars);
        } else {
            caesarCipher.decrypt(textOfFileAsChars);
        }

        String processedTextOfFile = String.valueOf(textOfFileAsChars);

        cipherMachine.setProcessedTextOutput(processedTextOfFile);
        consoleManager.updatePreview(processedTextOfFile);

        stateManager.setCurrent(SAVE_TO_FILE_PROCESS);
    }
}
