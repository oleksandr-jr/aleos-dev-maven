package org.example;

import org.example.console.ConsoleManager;
import org.example.cryptoanalizer.caesarcipher.CaesarCipher;
import org.example.statehandler.State;
import org.example.statehandler.StateManager;
import org.example.statehandlers.*;

import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * The main class representing the Cipher Machine application.
 */
public class CipherMachine {

    private final ConsoleManager consoleManager;
    private final StateManager stateManager;
    private final CaesarCipher cipher;
    private final String[] args;

    private final Map<State, StateHandler> stateHandlers;

    private Scanner scanner;
    private String fileText;
    private String referenceFileText;
    private String processedTextOutput;
    private Path pathToFile;


    /**
     * Constructs a new instance of the CipherMachine class.
     */
    CipherMachine(String[] args) {
        this.args = args;
        this.consoleManager = new ConsoleManager();
        this.stateManager = new StateManager();
        this.cipher = new CaesarCipher();

        this.stateHandlers = new HashMap<>();
        initializeStateHandlers();
    }

    /**
     * Runs the Cipher Machine application.
     */
    public void run() {

        try (Scanner scanner = getScanner(args)) {
            this.scanner = scanner;

            while (stateManager.getCurrent() != State.OFF) {
                consoleManager.updateMenu(stateManager.getPromptOfCurrentState());
                consoleManager.drawScreen();
                execute(getUserInput());
            }
        }
    }

    /**
     * Executes the current state's handler.
     */
    private void execute(String userInput) {
        stateManager.switchToNewStateOrSkip(userInput);

        do {
            StateHandler handler = stateHandlers.get(stateManager.getCurrent());

            if (handler != null) {
                handler.handle(userInput);
            } else {
                System.out.println("Error: Invalid state.");
            }
        } while (!stateManager.getCurrent().isUserCommandRequired());
    }

    /**
     * Retrieves the ConsoleManager instance.
     *
     * @return The ConsoleManager instance.
     */
    public ConsoleManager getConsoleManager() {
        return consoleManager;
    }

    /**
     * Retrieves the StateManager instance.
     *
     * @return The StateManager instance.
     */
    public StateManager getStateManager() {
        return stateManager;
    }

    /**
     * Retrieves the CaesarCipher instance.
     *
     * @return The CaesarCipher instance.
     */
    public CaesarCipher getCipher() {
        return cipher;
    }

    /**
     * Retrieves the file text.
     *
     * @return The file text.
     */
    public String getFileText() {
        return fileText;
    }

    /**
     * Retrieves the reference file text.
     *
     * @return The reference file text.
     */
    public String getReferenceFileText() {
        return referenceFileText;
    }

    /**
     * Retrieves the processed text.
     *
     * @return The processed text.
     */
    public String getProcessedTextOutput() {
        return processedTextOutput;
    }

    /**
     * Retrieves the path to the file.
     *
     * @return The path to the file.
     */
    public Path getPathToFile() {
        return pathToFile;
    }

    /**
     * Sets the file text.
     *
     * @param fileText The file text to set.
     */
    public void setFileText(String fileText) {
        this.fileText = fileText;
    }

    /**
     * Sets the reference file text.
     *
     * @param referenceFileText The reference file text to set.
     */
    public void setReferenceFileText(String referenceFileText) {
        this.referenceFileText = referenceFileText;
    }

    /**
     * Sets the processed text.
     *
     * @param processedTextOutput The processed text to set.
     */
    public void setProcessedTextOutput(String processedTextOutput) {
        this.processedTextOutput = processedTextOutput;
    }

    /**
     * Sets the path to the file.
     *
     * @param fileLocation path to the file to set.
     */
    public void setPathToFile(Path fileLocation) {
        pathToFile = fileLocation;
    }

    /**
     * Retrieves the user input from the scanner.
     */
    private String getUserInput() {
        return scanner.nextLine();
    }


    /**
     * Sets the scanner based on input args.
     */
    private Scanner getScanner(String[] args) {
        if (args.length == 0) {
            this.scanner = new Scanner(System.in);
            return new Scanner(System.in);
        }

        // Redirect standard output to a null print stream
        System.setOut(new NullPrintStream());

        StringBuilder commands = new StringBuilder();
        for (String arg : args) {
            commands.append(arg).append("\n");
        }
        commands.append("exit").append("\n");

        return new Scanner(commands.toString());

    }

    /**
     * Initializes the state handlers for the Cipher Machine.
     */
    private void initializeStateHandlers() {
        stateHandlers.put(State.MENU, new MenuStateHandler(this));
        stateHandlers.put(State.ENCRYPT, new EncryptionHandler(this));
        stateHandlers.put(State.DECRYPT, new DecryptionHandler(this));
        stateHandlers.put(State.BRUTE_FORCE, new BruteForceHandler(this));
        stateHandlers.put(State.STATISTICAL_ANALYSIS, new StaticalAnalysisHandler(this));
        stateHandlers.put(State.GET_FILE, new ReadFileHandler(this));
        stateHandlers.put(State.GET_ENCRYPTED_FILE, new ReadFileHandler(this));
        stateHandlers.put(State.GET_REFERENCE_FILE, new ReadFileHandler(this));
        stateHandlers.put(State.GET_THE_KEY, new EnterKeyHandler(this));
        stateHandlers.put(State.SEARCH_THE_KEY, new SearchTheKeyHandler(this));
        stateHandlers.put(State.CIPHER_PROCESS, new CipherProcessHandler(this));
        stateHandlers.put(State.SAVE_TO_FILE_PROCESS, new SaveToFileHandler(this));
        stateHandlers.put(State.SCROLL_PREVIEW_PAGE, new ScrollPreviewPage(this));
        stateHandlers.put(State.OPERATION_COMPLETE, new OperationCompletionHandler(this));
        stateHandlers.put(State.OFF, new OffMachineHandler(this));
    }

    /**
     * Custom PrintStream class that discards the output.
     */
    static class NullPrintStream extends PrintStream {

        NullPrintStream() {
            super(new NullOutputStream());
        }

        private static class NullOutputStream extends OutputStream {

            @Override
            public void write(int b) {
                // Do nothing
            }
        }
    }
}
