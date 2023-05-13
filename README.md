# CipherMachine
CipherMachine is a Java-based application that provides cryptographic analysis and operations using the Caesar cipher. This implementation provides both encryption and decryption capabilities, as well as statistical analysis for key finding using brute force and reference text.

## Table of Contents
- [Overview](#overview)
- [How to Use JAR File](#how-to-use-jar-file)
- [Usage](#usage)
  - [Encrypting](#encrypting)
  - [Decrypting](#decrypting)
  - [Brute Force](#brute-force)
  - [Statistical Analysis](#statistical-analysis)
- [Contributing](#contributing)

Features:
- Encryption: Encrypt plain texts using the Caesar cipher with a specified encryption key.
- Decryption: Decrypt encrypted texts using the Caesar cipher with the correct decryption key.
- Brute-force Attack: Attempt to break the encryption by trying all possible encryption keys.
- Statistical Analysis: Analyze encrypted texts and perform statistical analysis based on a reference text to determine the most likely encryption key.
- Console Interface: Interact with the application through a user-friendly console interface.
- Preview: Preview the processed text as operation proceeded, and navigate on the preview screen using "n" or "p" commands.
- Menu System: Navigate through different modes and options using the menu system.
- State Management: Manage the state of the application and handle state transitions.


## Overview

This cipher machine implementation relies on a state-driven workflow. The program's behavior depends on its current state, and it transitions between states based on user input. The program stops and waits for user input at a single point. If a process requires further instructions from the user, it returns to this waiting point before proceeding.

In this state-driven design, user input plays a vital role in guiding the program through its various states, ensuring a smooth and interactive experience for the user.

This implementation includes the following main classes:

- `CaesarCipher`: Handles encryption and decryption using the Caesar cipher.
- `StateManager`: Manages the application states and state transitions.
- `ConsoleManager`: Manages console input and output, and provides functionality for rendering the user interface.
- `CaesarCipherBruteForce`: Performs a brute-force approach to find the encryption key.
- `Alphabet`: Represents an alphabet for the Caesar cipher.
- `StateHandler`: Handles input commands for different states of the application.
- `Screen`: Represents a screen in the console-based user interface.
- Various other utility classes.

## How to Use JAR File

1. Make sure you have Java installed on your system. If not, download and install the Java Runtime Environment (JRE) from [Oracle's website](https://www.oracle.com/java/technologies/downloads/#java17).

2. Download the CipherMachine JAR file from the GitHub repository or build it using a build tool like Maven or Gradle.

3. Open the terminal (Command Prompt on Windows) and navigate to the directory where the JAR file is located.

4. To run the program, use the following command:

```bash
java -jar CipherMachine.jar
```

This will start the program and display the menu, where you can choose the desired action, such as encrypting, decrypting, or analyzing a text file using the CipherMachine.

5. Follow the on-screen prompts to provide the required information, such as file paths and encryption keys.

6. The program will process the input and display the results, allowing you to preview, save, or perform other actions as needed.


#### Using JAR File with Command-line Arguments

You can also use the  cipher machine JAR file with command-line arguments to perform various operations without entering the interactive mode. Here are the available commands:

1. Encrypt a source file:

```bash
java -jar CipherMachine.jar encrypt pathToSourceFile key
java -jar CipherMachine.jar e pathToSourceFile key
```

2. Decrypt an encrypted file:

```bash
java -jar CipherMachine.jar decrypt pathToEncryptedFile key
java -jar CipherMachine.jar d pathToEncryptedFile key
```

3. Brute force an encrypted file:

```bash
java -jar CipherMachine.jar bruteforce pathToEncryptedFile
java -jar CipherMachine.jar bf pathToEncryptedFile
```

4. Perform statistical analysis on an encrypted file using a reference file:

```bash
java -jar CipherMachine.jar statisticalanalysis pathToEncryptedFile pathToReferenceFile
java -jar CipherMachine.jar sa pathToEncryptedFile pathToReferenceFile
```

## Usage

Before running the CipherMachine CLI interactive console:

```bash
java -jar CipherMachine.jar
```

make sure your terminal or command prompt window is set to a minimum width of 110 characters for correct visualization.

To start the interactive console, run the JAR file as explained in the "How to Use JAR File" section. The console will provide a user-friendly menu to guide you through the available options, such as encrypting, decrypting, or analyzing a text file using the Caesar cipher. You can provide the necessary information like file paths and encryption keys by following the on-screen prompts.

### Encrypting


### Decrypting


### Brute Force


### Statistical Analysis


### Navigation

Use "N" or "P" command to navigate through the file content.



## Contributing

Contributions to this project are welcome! To contribute, please follow these steps:

1. Fork the repository.
2. Create a new branch with a descriptive name, e.g., `fix-decryption-bug` or `add-new-feature`.
3. Make your changes in the new
