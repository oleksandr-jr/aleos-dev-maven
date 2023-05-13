package statehandlers;

import console.ConsoleManager;
import cryptoanalizer.caesarcipher.CaesarCipher;
import main.CipherMachine;
import statehandler.State;
import statehandler.StateManager;

import static statehandler.State.ENCRYPT;
import static statehandler.State.SAVE_TO_FILE_PROCESS;

public class CipherProcessHandler implements StateHandler {

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
