package org.example.cryptoanalizer.caesarcipher;

import org.example.cryptoanalizer.Alphabet;

import java.util.*;

/**
 * The CaesarCipherBruteForce class represents a brute-force approach to finding the encryption key
 * for a Caesar cipher by analyzing the encrypted text and performing statistical analysis on a reference text.
 */
public class CaesarCipherBruteForce {
    private final Alphabet alphabet;
    private final CaesarCipher cipher;
    private final int maxSampleSize = 1_111;
    private final char[] text;

    private int maxWordLengthThreshold = 32;
    private int maxWordLength;
    private int accuracyThreshold;
    private int accuracy;

    private TreeSet<Character> topFrequentCharsInExample;
    private TreeSet<Character> topFrequentCharsInSourceText;

    /**
     * Constructs a CaesarCipherBruteForce object with the specified encrypted text and CaesarCipher instance.
     *
     * @param text         The encrypted text as a character array.
     * @param caesarCipher The CaesarCipher instance used for decryption.
     */
    public CaesarCipherBruteForce(char[] text, CaesarCipher caesarCipher) {
        this.cipher = caesarCipher;
        this.alphabet = caesarCipher.getAlphabet();
        this.topFrequentCharsInExample = alphabet.getTopTenFrequentChars();
        this.accuracyThreshold = (int) (topFrequentCharsInExample.size() * 0.75);

        int sampleSize = Math.min(text.length, maxSampleSize);
        this.text = new char[sampleSize];

        System.arraycopy(text, 0, this.text, 0, sampleSize);
    }

    /**
     * Finds the encryption key using statistical analysis on the reference text.
     *
     * @param referenceText The reference text as a character array for statistical analysis.
     * @return The encryption key.
     */
    public int findEncryptionKeyUsingStaticalAnalysis(char[] referenceText) {

        analyzeReferenceText(referenceText);

        return findEncryptionKey();
    }

    /**
     * Finds the encryption key by iterating through all possible shift values and checking the accuracy.
     *
     * @return The encryption key.
     */
    public int findEncryptionKey() {
        for (int shift = 1; shift <= alphabet.length(); shift++) {
            cipher.setKey(1);
            cipher.decrypt(text);

            if (isValidDecryptedText(text, maxWordLengthThreshold) && calculateAccuracy(text) > accuracyThreshold) {
                return shift;
            }
        }

        return 0;
    }

    /**
     * Retrieves the top ten frequent characters in the reference text.
     *
     * @return A TreeSet containing the top ten frequent characters in the reference text.
     */
    public TreeSet<Character> getTopTenFrequentCharsAtReferenceText() {
        return new TreeSet<>(topFrequentCharsInExample);
    }

    /**
     * Retrieves the top ten frequent characters in the source text.
     *
     * @return A TreeSet containing the top ten frequent characters in the source text.
     */
    public TreeSet<Character> getTopTenFrequentCharsAtSourceText() {
        return new TreeSet<>(topFrequentCharsInSourceText);
    }

    /**
     * Retrieves the max word length of the source text.
     *
     * @return The number of max word length.
     */
    public int getMaxWordLength() {
        return maxWordLength;
    }

    /**
     * Retrieves the accuracy of the decryption.
     *
     * @return The accuracy as a percentage.
     */
    public int getAccuracy() {
        return accuracy * 100 / topFrequentCharsInExample.size();
    }

    /**
     * Calculates the accuracy of the decrypted text by comparing its character frequencies
     * to the top frequent characters in the example text.
     *
     * @param text The decrypted text as a character array.
     * @return The accuracy of the decrypted text.
     */
    private int calculateAccuracy(char[] text) {
        TreeMap<Character, Double> charFrequencyMap = computeCharacterFrequencies(text);
        topFrequentCharsInSourceText = new TreeSet<>();

        int accuracy = 0;
        var iterator = charFrequencyMap.keySet().iterator();
        for (int iteration = 0; iteration < topFrequentCharsInExample.size() && iterator.hasNext(); iteration++) {

            char ch = iterator.next();
            if (topFrequentCharsInExample.contains(Character.toLowerCase(ch))) {
                accuracy++;
            }
        }

        if (accuracy > accuracyThreshold) {

            iterator = charFrequencyMap.keySet().iterator();
            for (int charCount = 0; charCount < topFrequentCharsInExample.size() && iterator.hasNext(); charCount++) {
                topFrequentCharsInSourceText.add(iterator.next());
            }
            this.accuracy = accuracy;

            return this.accuracy;
        }

        return 0;
    }

    /**
     * Checks if the decrypted text is valid based on its word length.
     *
     * @param text          The decrypted text as a character array.
     * @param maxWordLength The maximum allowed word length.
     * @return {@code true} if the decrypted text is valid, {@code false} otherwise.
     */
    private boolean isValidDecryptedText(char[] text, int maxWordLength) {
        int wordLength = 0;
        for (char ch : text) {
            if (Character.isWhitespace(ch) || isLineBreak(ch)) {
                if (wordLength > maxWordLength) {
                    return false;
                }
                wordLength = 0;
            } else {
                wordLength++;
            }
        }

        return wordLength <= maxWordLength;
    }

    /**
     * Analyzes the reference text to determine the maximum word length and top frequent characters.
     *
     * @param referenceText The reference text as a character array.
     */
    private void analyzeReferenceText(char[] referenceText) {
        int sampleSize = Math.min(referenceText.length, maxSampleSize);
        char[] sampleText = new char[sampleSize];
        System.arraycopy(referenceText, 0, sampleText, 0, sampleSize);

        maxWordLengthThreshold = Math.max(maxWordLengthThreshold, (int) (getMaxWordLength(sampleText) * 1.2));

        TreeMap<Character, Double> charsFrequencyOfReferenceText = computeCharacterFrequencies(sampleText);

        topFrequentCharsInExample = getTopFrequentChars(charsFrequencyOfReferenceText);
        accuracyThreshold = (int) (topFrequentCharsInExample.size() * 0.75);

    }

    /**
     * Retrieves the top frequent characters from the character frequency map.
     *
     * @param charFrequencyMap The character frequency map.
     * @return A TreeSet containing the top frequent characters.
     */
    private TreeSet<Character> getTopFrequentChars(TreeMap<Character, Double> charFrequencyMap) {
        TreeSet<Character> result = new TreeSet<>();

        Iterator<Character> iterator = charFrequencyMap.keySet().iterator();
        for (int i = 0; i < 10 && iterator.hasNext(); i++) {
            result.add(iterator.next());
        }

        return result;
    }

    /**
     * Returns the maximum length of a word in the given text.
     *
     * @param text The text as a String.
     * @return The maximum length of a word in the text.
     */
    private int getMaxWordLength(char[] text) {
        int maxWordLength = 0;
        int wordLength = 0;
        for (char ch : text) {
            if (Character.isWhitespace(ch) || isLineBreak(ch)) {
                if (maxWordLength < wordLength) {
                    maxWordLength = wordLength;
                }
                wordLength = 0;
            } else {
                wordLength++;
            }
        }
        this.maxWordLength = maxWordLength;

        return this.maxWordLength;
    }

    /**
     * Checks if the given character represents a line break.
     *
     * @param ch The character to check.
     * @return {@code true} if the character is a line break, {@code false} otherwise.
     */
    private boolean isLineBreak(char ch) {
        return ch == '\n';
    }

    /**
     * Calculates the character frequencies for the decrypted text.
     * Note that it just simple count of character not percentage
     *
     * @param text The decrypted text as a character array.
     * @return A TreeMap containing the character frequencies sorted in descending order.
     */
    private TreeMap<Character, Double> computeCharacterFrequencies(char[] text) {
        final Map<Character, Double> charFrequencies = new HashMap<>();
        for (char ch : text) {
            if (Character.isAlphabetic(ch)) {
                charFrequencies.merge(Character.toLowerCase(ch), 1.0, Double::sum);
            }
        }
        return sortFrequencyMapDescendingByValue(charFrequencies);
    }

    /**
     * Sorts the character frequency map in descending order by value.
     *
     * @param map The character frequency map.
     * @return A TreeMap containing the character frequencies sorted in descending order.
     */
    private TreeMap<Character, Double> sortFrequencyMapDescendingByValue(Map<Character, Double> map) {
        // Create a TreeMap with a custom Comparator to sort the entries by value
        TreeMap<Character, Double> treeMap = new TreeMap<>((c1, c2) -> {
            double freq1 = map.get(c1);
            double freq2 = map.get(c2);
            return Double.compare(freq2, freq1);
        });

        // Add all entries from the original map to the sorted map
        treeMap.putAll(map);

        return treeMap;
    }

}
