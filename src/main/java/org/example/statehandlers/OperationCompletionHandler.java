package org.example.statehandlers;

import org.example.CipherMachine;
import org.example.statehandler.StateManager;

import static org.example.statehandler.State.MENU;
import static org.example.statehandler.State.OFF;

public class OperationCompletionHandler implements StateHandler {
    private final StateManager stateManager;

    public OperationCompletionHandler(CipherMachine cipherMachine) {
        this.stateManager = cipherMachine.getStateManager();
    }

    @Override
    public void handle(String inputCommand) {
        if ("q".equalsIgnoreCase(inputCommand) || "exit".equalsIgnoreCase(inputCommand)) {
            stateManager.setCurrent(OFF);
        } else if ("m".equalsIgnoreCase(inputCommand) || "menu".equalsIgnoreCase(inputCommand)) {
            stateManager.setCurrent(MENU);
        }
    }
}
