package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;
import prompt.ModeHeaderPrompt;
import statehandler.StateManager;

import static statehandler.State.GET_ENCRYPTED_FILE;
import static statehandler.State.STATICAL_ANALYSIS;

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
        consoleManager.updateMenu(ModeHeaderPrompt.STATICAL_ANALYSIS.getMessage());

        stateManager.setRoot(STATICAL_ANALYSIS);
        stateManager.setCurrent(GET_ENCRYPTED_FILE);
    }

}
