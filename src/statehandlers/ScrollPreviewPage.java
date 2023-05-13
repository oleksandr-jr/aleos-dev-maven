package statehandlers;

import console.ConsoleManager;
import main.CipherMachine;
import statehandler.StateManager;

public class ScrollPreviewPage implements StateHandler {
    private final ConsoleManager consoleManager;
    private final StateManager stateManager;

    public ScrollPreviewPage(CipherMachine cipherMachine) {
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {

        if ("n".equalsIgnoreCase(inputCommand)) {
            consoleManager.nextPreview();
        } else if ("p".equalsIgnoreCase(inputCommand)) {
            consoleManager.prevPreview();
        }
        stateManager.setCurrent(stateManager.getPrevious());
    }
}
