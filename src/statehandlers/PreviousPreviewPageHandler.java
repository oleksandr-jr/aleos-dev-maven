package statehandlers;

import console.ConsoleManager;
import statehandler.StateManager;

public class PreviousPreviewPageHandler implements StateHandler {
    private ConsoleManager consoleManager;
    private StateManager stateManager;

    public PreviousPreviewPageHandler(ConsoleManager consoleManager, StateManager stateManager) {
        this.consoleManager = consoleManager;
        this.stateManager = stateManager;
    }

    @Override
    public void handle(String inputCommand) {
        consoleManager.prevPreview();
        stateManager.setCurrent(stateManager.getPrevious());
    }

}
