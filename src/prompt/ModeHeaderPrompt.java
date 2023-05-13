package prompt;

public enum ModeHeaderPrompt {
    ENCRYPT_HEADER("___________________ENCRYPT MODE____________________\n"),
    DECRYPT_HEADER("___________________DECRYPT MODE____________________\n"),
    BRUTE_FORCE_HEADER("_________________BRUTE FORCE MODE__________________\n"),
    STATISTICAL_ANALYSIS("_____BRUTE FORCE MODE VIA STATISTICAL ANALYSIS_____\n");

    private final String message;

    ModeHeaderPrompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
