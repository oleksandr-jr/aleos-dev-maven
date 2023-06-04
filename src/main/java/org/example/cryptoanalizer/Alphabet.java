package org.example.cryptoanalizer;

import java.util.*;

/**
 * The Alphabet enum represents different alphabets and provides methods to work with them.
 */
public enum Alphabet {
    ENGLISH("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.,\":-!?" + " "),
    RUSSIAN("абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ.,\":-!?" + " "),
    UKRAINIAN("абвгґдеєжзиіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ.,\":-!?" + " "),
    WHO_KNOWS("");

    private final String values;
    private final TreeSet<Character> topTenFrequentChars = new TreeSet<>();
    private final Map<Character, Double> charsFrequency = new HashMap<>();

    static {
        initializeEnglishFrequencyData();
        initializeRussianFrequencyData();
        initializeUkrainianFrequencyData();
    }

    Alphabet(String values) {
        this.values = values;
    }

    /**
     * Determines the alphabet of the input text by analyzing the characters.
     *
     * @param text The input text to analyze.
     * @return The Alphabet instance representing the identified alphabet.
     */
    public static Alphabet determineAlphabetFromText(String text) {
        int maxSampleSize = Math.min(1_000, text.length());
        String sampleText = text.substring(0, maxSampleSize);

        for (var alphabet : values()) {

            int matchingChars = 0;
            int charactersCount = 0;

            for (var ch : sampleText.toCharArray()) {
                if (Character.isAlphabetic(ch)) {
                    charactersCount++;
                    if (alphabet.indexOf(ch) >= 0) {
                        matchingChars++;
                    }
                }
            }

            if (matchingChars > (charactersCount / 2)) {
                if (alphabet == Alphabet.RUSSIAN && (sampleText.contains("і") || sampleText.contains("є"))) {
                    return Alphabet.UKRAINIAN;
                }
                return alphabet;
            }
        }

        return WHO_KNOWS;
    }


    /**
     * Returns a copy of the top ten frequent characters for the alphabet.
     *
     * @return A TreeSet containing the top ten frequent characters.
     */
    public TreeSet<Character> getTopTenFrequentChars() {
        return new TreeSet<>(topTenFrequentChars);
    }

    public int length() {
        return values.length();
    }

    public int indexOf(char ch) {
        return values.indexOf(ch);
    }

    public char charAt(int index) {
        return values.charAt(index);
    }

    public Map<Character, Double> getCharsFrequency() {
        return new HashMap<>(this.charsFrequency);
    }


    private static void initializeEnglishFrequencyData() {
        Map<Character, Double> english = new HashMap<>();
        String englishFrequencies = "a:8.2,b:1.5,c:2.8,d:4.3,e:12.7,f:2.2,g:2.0,h:6.1,i:7.0,j:0.15,k:0.77,l:4.0,m:2.4,n:6.7,o:7.5,p:1.9,q:0.095,r:6.0,s:6.3,t:9.1,u:2.8,v:0.98,w:2.4,x:0.15,y:2.0,z:0.074, .:4.6,\" :0.67,-:0.53,!:0.33,?:0.56";
        english.put(' ', 16.6);
        english.put(',', 6.2);

        for (String entry : englishFrequencies.split(",")) {
            String[] keyValue = entry.split(":");
            english.put(keyValue[0].charAt(0), Double.parseDouble(keyValue[1]));
        }


        ENGLISH.charsFrequency.putAll(english);

        Collections.addAll(ENGLISH.topTenFrequentChars, 'e', 't', 'a', 'i', 'n', 'r', 'o', 's', 'h', 'd');
    }

    private static void initializeRussianFrequencyData() {
        Map<Character, Double> russian = new HashMap<>();
        String russianFrequencies = "а:8.01,б:1.59,в:4.54,г:1.70,д:2.98,е:8.45,ё:0.04,ж:0.94,з:1.65,и:7.35,й:1.21,к:3.49,л:4.40,м:3.21,н:6.70,о:10.97,п:2.81,р:4.73,с:5.47,т:6.26,у:2.62,ф:0.26,х:0.97,ц:0.48,ч:1.44,ш:1.00,щ:0.36,ъ:0.04,ы:1.90,ь:1.74,э:0.32,ю:0.64,я:2.01";
        russian.put(' ', 14.5);

        for (String entry : russianFrequencies.split(",")) {
            String[] keyValue = entry.split(":");
            russian.put(keyValue[0].charAt(0), Double.parseDouble(keyValue[1]));
        }

        RUSSIAN.charsFrequency.putAll(russian);
        Collections.addAll(RUSSIAN.topTenFrequentChars, 'с', 'е', 'н', 'о', 'в', 'а', 'л', 'и', 'т', 'р');
    }

    private static void initializeUkrainianFrequencyData() {
        Map<Character, Double> ukrainian = new HashMap<>();

        String ukrainianFrequencies = "а:7.6,б:1.7,в:5.5,г:1.6,ґ:0.6,д:3.3,е:1.1,є:0.7,ж:0.9,з:3.7,и:5.7,і:6.3,ї:1.1,й:1.4,к:3.5,л:3.9,м:3.1,н:7.2,о:9.6,п:3.4,р:4.5,с:4.6,т:5.6,у:3.9,ф:0.2,х:1.3,ц:0.9,ч:1.4,ш:1.8,щ:0.5,ь:1.8,ю:0.5,я:4.5";
        ukrainian.put(' ', 14.5);

        for (String entry : ukrainianFrequencies.split(",")) {
            String[] keyValue = entry.split(":");
            ukrainian.put(keyValue[0].charAt(0), Double.parseDouble(keyValue[1]));
        }

        UKRAINIAN.charsFrequency.putAll(ukrainian);
        Collections.addAll(UKRAINIAN.topTenFrequentChars, 'о', 'а', 'н', 'і', 'е', 'и', 'т', 'р', 'с', 'в');
    }
}
