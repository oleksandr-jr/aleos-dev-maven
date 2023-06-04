package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.CipherMachine;
import org.example.prompt.ModeHeaderPrompt;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.GET_ENCRYPTED_FILE;
import static org.example.statehandler.State.STATISTICAL_ANALYSIS;

public class StaticalAnalysisHandler implements StateHandler {

    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public StaticalAnalysisHandler(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.clearMenu();
        consoleManager.updateMenu(ModeHeaderPrompt.STATISTICAL_ANALYSIS.getMessage());

        stateManager.setRoot(STATISTICAL_ANALYSIS);
        stateManager.setCurrent(GET_ENCRYPTED_FILE);
    }

}
