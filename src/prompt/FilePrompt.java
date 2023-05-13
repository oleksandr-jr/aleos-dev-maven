package prompt;

public enum FilePrompt {

    ENTER_THE_PATH_TO_THE_FILE("\nEnter the path to the file: "),
    ENTER_THE_PATH_TO_THE_ENCRYPTED_FILE("\nEnter the path to the encrypted file: "),
    ENTER_THE_PATH_TO_THE_REFERENCE_FILE("\nEnter the path to the reference file: "),

    ENCRYPTED_MARK("[ENCRYPTED]"),
    DECRYPTED_MARK("[DECRYPTED]"),


    FILE_NOT_FOUND("File not found: %s"),
    ERROR_READING_FILE("Error reading file: %s"),
    ERROR_WRITING_FILE("Error writing file. Please try again.");


    private final String message;

    FilePrompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
