package cryptoanalizer.caesarcipher;

import cryptoanalizer.Alphabet;

/**
 * The CaesarCipher class represents a Caesar cipher encryption and decryption algorithm.
 * It allows for encrypting and decrypting text using a specified key and an alphabet.
 */
public class CaesarCipher {

    private Alphabet alphabet;
    private int key;
    private CaesarCipherBruteForce bruteForce;

    /**
     * Creates a CaesarCipher object with the default English alphabet.
     */
    public CaesarCipher() {
        this.alphabet = Alphabet.ENGLISH;
    }

    /**
     * Encrypts the given text using the Caesar Cipher with the current key.
     *
     * @param text The text to be encrypted as a char array.
     */
    public void encrypt(char[] text) {
        shiftCharacters(text, key);
    }

    /**
     * Decrypts the given text using the Caesar Cipher with the current key.
     *
     * @param text The text to be decrypted as a char array.
     */
    public void decrypt(char[] text) {
        shiftCharacters(text, -key);
    }

    /**
     * Attempts to find the encryption key using brute force and sets the key.
     *
     * @param encryptedText The encrypted text to analyze as a char array.
     */
    public void bruteForce(String encryptedText) {
        var bruteForce = new CaesarCipherBruteForce(encryptedText.toCharArray(), this);
        key = bruteForce.findEncryptionKey();
    }

    /**
     * Attempts to find the encryption key using statistical analysis and sets the key.
     *
     * @param encryptedText The encrypted text to analyze as a String.
     * @param referenceText The reference text used for statistical analysis as a String.
     */
    public void bruteForceViaStatisticalAnalysis(String encryptedText, String referenceText) {
        bruteForce = new CaesarCipherBruteForce(encryptedText.toCharArray(), this);
        key = bruteForce.findEncryptionKeyUsingStaticalAnalysis(referenceText.toCharArray());
    }

    /**
     * Retrieves the character frequency statistics from the most recent brute force analysis.
     * Returns null if no brute force analysis has been performed.
     *
     * @return The character frequency statistics as a formatted String.
     */
    public String getMostFrequentCharacterStatistics() {
        if (bruteForce == null) {
            return null;
        }
        String map1 = bruteForce.getTopTenFrequentCharsAtSourceText().toString();
        String map2 = bruteForce.getTopTenFrequentCharsAtReferenceText().toString();
        int maxWordLength = bruteForce.getMaxWordLength();
        int accuracy = bruteForce.getAccuracy();

        return "Most frequent chars in decrypted text: \n" +
                map1 + "\n" +
                "Most frequent chars in reference text: \n" +
                map2 + "\n" +
                "Accuracy based on a reference text = " + accuracy + "%\n" +
                "Max word length = " + maxWordLength + "\n";
    }

    /**
     * Retrieves the current encryption/decryption key.
     *
     * @return The current key value.
     */
    public int getKey() {
        return key;
    }

    /**
     * Retrieves the alphabet used for encryption and decryption.
     *
     * @return The alphabet object.
     */
    public Alphabet getAlphabet() {
        return alphabet;
    }

    /**
     * Sets the encryption/decryption key.
     *
     * @param key The key value to be set.
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Sets the alphabet used for encryption and decryption.
     *
     * @param alphabet The alphabet object to be set.
     */
    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }


    /**
     * Shifts the characters in the given text by the specified key value.
     *
     * @param text The text to be shifted as a char array.
     * @param key  The key value representing the amount of shifting.
     */
    private void shiftCharacters(char[] text, int key) {
        for (int i = 0; i < text.length; i++) {
            char ch = text[i];
            int index = alphabet.indexOf(ch);

            if (index >= 0) {
                int shiftedIndex = Math.floorMod(index + key, alphabet.length());
                text[i] = alphabet.charAt(shiftedIndex);
            }
        }
    }
}
