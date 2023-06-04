package org.example;

public class Main {

    /**
     * The entry point of the Cipher Machine application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        CipherMachine enigma = new CipherMachine(args);
        enigma.run();
    }
}
