package statehandlers;

import console.ConsoleManager;
import cryptoanalizer.Alphabet;
import cryptoanalizer.caesarcipher.CaesarCipher;
import filehandler.FileService;
import main.CipherMachine;
import prompt.FilePrompt;
import prompt.OperationPrompt;
import statehandler.State;
import statehandler.StateManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static statehandler.State.*;

public class ReadFileHandler implements StateHandler {
    private final CipherMachine cipherMachine;
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;
    private final CaesarCipher caesarCipher;
    private final FileService fileService = new FileService();

    public ReadFileHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
        this.caesarCipher = cipherMachine.getCipher();
        this.cipherMachine = cipherMachine;
    }

    /**
     * Read the specified file and update the console menu based on the current state.
     *
     * @param inputCommand The path to the file to be read.
     */
    @Override
    public void handle(String inputCommand) {

        Path path = Path.of(inputCommand);
        if (!Files.exists(path)) {
            consoleManager.updateMenu(
                    String.format(FilePrompt.FILE_NOT_FOUND.getMessage(), inputCommand),
                    OperationPrompt.POSSIBLE_OPTIONS.getMessage());

            return;
        }

        State currentState = stateManager.getCurrent();

        try {
            if (currentState == GET_REFERENCE_FILE) {
                cipherMachine.setReferenceFileText(fileService.readFile(path));
                consoleManager.updateMenu(path + "\n");
            } else {
                cipherMachine.setPathToFile(path);
                cipherMachine.setFileText(fileService.readFile(path));
                consoleManager.update(path + "\n", cipherMachine.getFileText());

                // alphabet determines on base of source file, not reference
                var alphabet = Alphabet.determineAlphabetFromText(cipherMachine.getFileText());
                caesarCipher.setAlphabet(alphabet);
            }

        } catch (IOException e) {
            consoleManager.updateMenu(
                    String.format(FilePrompt.ERROR_READING_FILE.getMessage(), inputCommand),
                    OperationPrompt.POSSIBLE_OPTIONS.getMessage());

            return;
        }

        State rootState = stateManager.getRoot();

        if (rootState == ENCRYPT || rootState == DECRYPT) {
            stateManager.setCurrent(GET_THE_KEY);
        } else if (rootState == BRUTE_FORCE) {
            stateManager.setCurrent(SEARCH_THE_KEY);
        } else if (rootState == STATICAL_ANALYSIS) {
            if (currentState == GET_ENCRYPTED_FILE) {
                stateManager.setCurrent(GET_REFERENCE_FILE);
            } else {
                stateManager.setCurrent(SEARCH_THE_KEY);
            }
        }
    }

}
