package statehandler;

import prompt.FilePrompt;
import prompt.MenuPrompt;
import prompt.OperationPrompt;

/**
 * Represents the states of the application.
 */
public enum State {
    MENU(MenuPrompt.MENU.getMessage()),

    ENCRYPT,
    DECRYPT,
    BRUTE_FORCE,
    STATISTICAL_ANALYSIS,
    GET_FILE(FilePrompt.ENTER_THE_PATH_TO_THE_FILE.getMessage()),
    GET_ENCRYPTED_FILE(FilePrompt.ENTER_THE_PATH_TO_THE_ENCRYPTED_FILE.getMessage()),
    GET_REFERENCE_FILE(FilePrompt.ENTER_THE_PATH_TO_THE_REFERENCE_FILE.getMessage()),
    GET_THE_KEY(OperationPrompt.ENTER_THE_KEY.getMessage()),
    SEARCH_THE_KEY(OperationPrompt.FOUND_KEY.getMessage(), false),
    OPERATION_COMPLETE(OperationPrompt.OPERATION_COMPLETE.getMessage()),
    CIPHER_PROCESS(false),
    SAVE_TO_FILE_PROCESS(false),
    SCROLL_PREVIEW_PAGE,
    PREV_PAGE_ON_PREVIEW_SCREEN,
    OFF,
    UNSUPPORTED_STATE;

    private final boolean isUserCommandRequired;
    private String prompt;

    State() {
        isUserCommandRequired = true;
    }

    State(boolean isUserCommandRequired) {
        this.isUserCommandRequired = isUserCommandRequired;
    }

    State(String prompt) {
        this();
        this.prompt = prompt;
    }

    State(String prompt, boolean isUserCommandRequired) {
        this.isUserCommandRequired = isUserCommandRequired;
        this.prompt = prompt;
    }

    /**
     * Checks if a user command is required for the state.
     *
     * @return true if a user command is required, false otherwise.
     */
    public boolean isUserCommandRequired() {
        return isUserCommandRequired;
    }

    /**
     * Gets the prompt associated with the state.
     *
     * @return the prompt.
     */
    public String getPrompt() {
        return prompt;
    }
}
