package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.prompt.ModeHeaderPrompt;
import org.example.cryptoanalizer.caesarcipher.CaesarCipher;
import org.example.CipherMachine;
import org.example.statehandler.State;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.*;

public class SearchTheKeyHandler implements StateHandler {
    private final CipherMachine cipherMachine;
    private final CaesarCipher caesarCipher;
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public SearchTheKeyHandler(CipherMachine cipherMachine) {
        this.cipherMachine = cipherMachine;
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
        this.caesarCipher = cipherMachine.getCipher();
    }

    /**
     * Searches for the encryption/decryption key using brute force or statical analysis
     * depending on the current state and updates the console menu with the result.
     *
     * @param inputCommand The input command from the user, if any.
     */
    @Override
    public void handle(String inputCommand) {
        State rootState = stateManager.getRoot();

        if (rootState == BRUTE_FORCE) {

            caesarCipher.bruteForce(cipherMachine.getFileText());
        } else if (rootState == STATISTICAL_ANALYSIS) {

            caesarCipher.bruteForceViaStatisticalAnalysis(cipherMachine.getFileText(), cipherMachine.getReferenceFileText());

            consoleManager.clearMenu(ModeHeaderPrompt.STATISTICAL_ANALYSIS.getMessage());
            consoleManager.updateMenu(caesarCipher.getMostFrequentCharacterStatistics());
        }

        consoleManager.updateMenu(String.format(stateManager.getPromptOfCurrentState(), caesarCipher.getKey()));
        stateManager.setCurrent(CIPHER_PROCESS);
    }


}
