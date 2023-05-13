package prompt;

public enum OperationPrompt {
    INPUT("Input:-> "),

    ENTER_THE_KEY("\nEnter the cipher key to proceed operation:"),
    FOUND_KEY("\nFound key: %d, decrypted file saved to:"),

    FILE_IS_ENCRYPTED("\nFile has been encrypted: <KEY = %d>, path to file:"),
    FILE_IS_DECRYPTED("\nFile has been decrypted: <KEY = %d>, path to file:"),

    INVALID_KEY("%s - is invalid key:"),
    KEY_CANNOT_BE_ZERO("Key cannot be ZERO ->"),

    POSSIBLE_OPTIONS("Enter [M]- Menu [Q]- Exit or proceed ->"),

    OPERATION_COMPLETE("\nEnter [M] - main menu, [Q] - quit program");


    private final String message;

    OperationPrompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
