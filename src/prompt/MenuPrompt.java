package prompt;

public enum MenuPrompt {
    MENU("""
            _______Caesar Cipher Machine Menu_______
            1 - [E]ncrypt a file
            2 - [D]ecrypt a file
            3 - Cryptoanalysis via [B]rute force
            4 - Cryptoanalysis via [S]tatistical analysis
        """);

    private final String message;

    MenuPrompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}