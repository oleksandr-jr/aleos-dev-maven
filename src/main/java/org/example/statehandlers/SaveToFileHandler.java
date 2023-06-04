package org.example.statehandlers;

import org.example.console.ConsoleManager;
import org.example.filehandler.FileService;
import org.example.CipherMachine;
import org.example.prompt.FilePrompt;
import org.example.statehandler.StateManager;

import java.io.IOException;
import java.nio.file.Path;

import static org.example.statehandler.State.OPERATION_COMPLETE;

public class SaveToFileHandler implements StateHandler {

    private final CipherMachine cipherMachine;

    private final ConsoleManager consoleManager;
    private final StateManager stateManager;
    private final FileService fileService;

    public SaveToFileHandler(CipherMachine cipherMachine) {
        this.cipherMachine = cipherMachine;
        this.consoleManager = cipherMachine.getConsoleManager();
        this.stateManager = cipherMachine.getStateManager();
        this.fileService = new FileService();
    }

    @Override
    public void handle(String inputCommand) {

        try {
            Path outputFilePath = generateOutputFilePathOnBaseOf(cipherMachine.getPathToFile());

            consoleManager.updateMenu(outputFilePath + "\n");

            fileService.writeToFile(outputFilePath, cipherMachine.getProcessedTextOutput());

            stateManager.setCurrent(OPERATION_COMPLETE);
        } catch (IOException e) {
            consoleManager.updateMenu(
                    String.format(FilePrompt.ERROR_WRITING_FILE.getMessage(), inputCommand));
        }

    }

    /**
     * Generate an output file path based on the source file path. This method appends the
     * appropriate encrypted or decrypted mark to the file name, depending on the current state.
     *
     * @param sourceFile The path to the source file.
     * @return A new Path instance representing the output file path.
     */
    private Path generateOutputFilePathOnBaseOf(Path sourceFile) {

        String fileName = sourceFile.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        String nameWithoutExtension = (dotIndex != -1) ? fileName.substring(0, dotIndex) : fileName;
        String extension = (dotIndex != -1) ? fileName.substring(dotIndex) : "";

        String encryptedMark = FilePrompt.ENCRYPTED_MARK.getMessage();
        String decryptedMark = FilePrompt.DECRYPTED_MARK.getMessage();

        if (nameWithoutExtension.contains(decryptedMark)) {
            nameWithoutExtension = nameWithoutExtension.replace(decryptedMark, encryptedMark);
        } else if (nameWithoutExtension.contains(encryptedMark)) {
            nameWithoutExtension = nameWithoutExtension.replace(encryptedMark, decryptedMark);
        } else {
            nameWithoutExtension += encryptedMark;
        }

        return sourceFile.toAbsolutePath().getParent().resolve(nameWithoutExtension + extension);

    }
}
