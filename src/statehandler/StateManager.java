package statehandler;

import static statehandler.State.*;

/**
 * Manages the state of the application and handles state transitions.
 */
public class StateManager {

    private State current = MENU;

    private State previous = MENU;

    private State root = MENU;



    /**
     * Retrieves the prompt associated with the current state.
     *
     * @return the prompt of the current state.
     */
    public String getPromptOfCurrentState() {
        return current.getPrompt();
    }


    /**
     * Switches to a new state or skips the state transition based on the input.
     *
     * @param input the input representing the desired state transition.
     */
    public void switchToNewStateOrSkip(String input) {

        State newState = valueOf(input);

        if (newState == OFF) {
            setCurrent(OFF);
        } else if (newState == SCROLL_PREVIEW_PAGE) {

            setCurrent(SCROLL_PREVIEW_PAGE);
        } else if (newState == MENU && current != MENU) {

            setCurrent(MENU);
        } else if (root == MENU) {

            setCurrent(newState);
        }


    }

    /**
     * Retrieves the current state.
     *
     * @return the current state.
     */
    public State getCurrent() {
        return current;
    }

    /**
     * Retrieves the previous state.
     *
     * @return the previous state.
     */
    public State getPrevious() {
        return previous;
    }

    /**
     * Retrieves the root state.
     *
     * @return the root state.
     */
    public State getRoot() {
        return root;
    }

    /**
     * Sets the current state.
     *
     * @param state the new current state.
     */
    public void setCurrent(State state) {
        if (state == MENU) {
            root = MENU;
        }
        previous = current;
        current = state;
    }

    /**
     * Sets the root state.
     *
     * @param root the new root state.
     */
    public void setRoot(State root) {
        this.root = root;
    }


    /**
     * Converts the input string to a corresponding state.
     *
     * @param input the input string representing the desired state.
     * @return the corresponding state.
     */
    private State valueOf(String input) {
        return switch (input.toLowerCase()) {
            case "m", "menu" -> MENU;
            case "n", "p" -> SCROLL_PREVIEW_PAGE;
            case "1", "e", "encrypt" -> ENCRYPT;
            case "2", "d", "decrypt" -> DECRYPT;
            case "3", "b", "bf", "brute force", "bruteforce" -> BRUTE_FORCE;
            case "4", "s", "sa", "statistical analysis", "statisticalanalysis" -> STATISTICAL_ANALYSIS;
            case "q", "exit" -> OFF;

            default -> UNSUPPORTED_STATE;
        };
    }

}
