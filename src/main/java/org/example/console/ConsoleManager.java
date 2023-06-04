package org.example.console;


import org.example.prompt.MenuPrompt;
import org.example.prompt.OperationPrompt;
import org.example.prompt.PreviewPrompt;

import java.util.LinkedList;
import java.util.Objects;

/**
 * The ConsoleManager class manages the console interface and screen display for the CipherMachine application.
 * It handles the main menu, preview screen, and user input prompts.
 */
public class ConsoleManager {
    private static final int WIDTH = 110;
    private static final int HEIGHT = 24;
    private static final int SHIFT_X = 0;
    private static final int SHIFT_Y = 0;

    private final int menuShiftX = 2;
    private final int menuShiftY = 1;
    private final int menuWidth = 55;
    private final int menuHeight = HEIGHT - 2;

    private final int previewShiftX = menuWidth + 1 + menuShiftX;
    private final int previewShiftY = menuShiftY;
    private final int previewWidth = WIDTH - previewShiftX - menuShiftX;
    private final int previewHeight = menuHeight;

    private Screen mainScreen;

    private final Screen menu = new Screen(SHIFT_X, SHIFT_Y, menuWidth, menuHeight);
    private final Screen preview = new Screen(SHIFT_X, SHIFT_Y, previewWidth, previewHeight);

    private final LinkedList<Integer> pageOffSets = new LinkedList<>();
    private String textOnPreview;
    private String lastPrompt;


    public ConsoleManager() {
        initializeConsoleManager();
    }

    /**
     * Draws the mainScreen, which includes both the menu and preview screens.
     */
    public void drawScreen() {
        int newLine_count = 15;
        printNewLines(newLine_count);

        mainScreen.drawScreen();
        System.out.print(OperationPrompt.INPUT.getMessage());
    }

    /**
     * Clears the menu screen and displays the main menu.
     */
    public void callMenu() {
        menu.clear();
        mainScreen.mergeMatrix(menu, menuShiftX, menuShiftY);
        mainScreen.mergeMatrix(preview, previewShiftX, previewShiftY);
    }

    /**
     * Updates the menu and preview screens with the given input strings.
     *
     * @param menu    The string to update the menu screen with.
     * @param preview The string to update the preview screen with.
     */
    public void update(String menu, String preview) {

        updateMenu(menu);
        updatePreview(preview);
    }

    /**
     * Updates the menu screen with the given input strings.
     * The menu is updated only if the input line is different from the last prompt
     * or the last prompt equals the main menu prompt.
     *
     * @param textToBeDisplayed The strings to update the menu screen with.
     */
    public void updateMenu(String... textToBeDisplayed) {
        if (textToBeDisplayed == null) { return; }

        if (Objects.equals(textToBeDisplayed[0], MenuPrompt.MENU.getMessage())) {
            preview.updateMatrix(PreviewPrompt.INFO.getMessage());
            mainScreen.mergeMatrix(preview, previewShiftX, previewShiftY);
        }

        boolean isUpdated = false;
        for (String line : textToBeDisplayed) {
            if (line != null && (!line.equals(lastPrompt) || lastPrompt.equals(MenuPrompt.MENU.getMessage()))) {
                menu.updateMatrix(line);
                lastPrompt = line;
                isUpdated = true;
            }

        }
        if (isUpdated) {
            mainScreen.mergeMatrix(menu, menuShiftX, menuShiftY);
        }
    }

    /**
     * Updates the preview screen with the given input content string.
     * This method clears any previous content on the preview screen, updates the
     * textOnPreview variable, and calculates the page offsets to handle pagination.
     *
     * @param textToBeDisplayed The string to update the preview screen with.
     */
    public void updatePreview(String textToBeDisplayed) {

        this.textOnPreview = textToBeDisplayed;
        pageOffSets.clear();
        pageOffSets.add(0);

        int sizeOfContentToDisplay = Math.min(preview.getMatrixCapacity(), textOnPreview.length());
        String contentToDisplay = textOnPreview.substring(0, sizeOfContentToDisplay);
        int displayedCharsOffset = preview.updateMatrix(contentToDisplay);

        pageOffSets.add(displayedCharsOffset);
        mainScreen.mergeMatrix(preview, previewShiftX, previewShiftY);
    }

    /**
     * Updates the preview screen to display the next page of content, if available.
     */
    public void nextPreview() {

        if (textOnPreview == null) return;

        int sizeOfSourceFile = textOnPreview.length();
        int indexOfConsumeContent = pageOffSets.getLast();

        if (sizeOfSourceFile <= indexOfConsumeContent) {
            return;
        }

        int offset = indexOfConsumeContent;
        int remainContent = sizeOfSourceFile - offset;
        int indexToCut = Math.min(preview.getMatrixCapacity(), remainContent) + offset;

        String contentForNextPage = textOnPreview.substring(offset, indexToCut);

        offset += preview.updateMatrix(contentForNextPage); // return number of applied chars

        pageOffSets.add(offset);
        mainScreen.mergeMatrix(preview, previewShiftX, previewShiftY);
    }

    /**
     * Updates the preview screen to display the previous page of content, if available.
     */
    public void prevPreview() {

        if (pageOffSets.size() < 3) {
            return;
        }
        pageOffSets.removeLast();
        pageOffSets.removeLast();
        nextPreview();
    }

    /**
     * Clears the menu screen.
     */
    public void clearMenu() {
        menu.clear();
    }

    public void clearMenu(String header) {
        clearMenu();
        menu.updateMatrix(header);
    }

    /**
     * Clears the preview screen and dependency.
     */
    public void clearPreview() {
        pageOffSets.clear();
        textOnPreview = null;
        preview.clear();
    }

    /**
     * Initializes the ConsoleManager by setting up the mainScreen, menu, and preview screens.
     */
    private void initializeConsoleManager() {

        ScreenFormatter screenFormatter = new ScreenFormatter();

        mainScreen = new Screen(0, 0, WIDTH, HEIGHT);
        screenFormatter.createFrame(mainScreen);

        screenFormatter.createFrame(menu);
        screenFormatter.createFrame(preview);

        mainScreen.mergeMatrix(menu, menuShiftX, menuShiftY);
        mainScreen.mergeMatrix(preview, previewShiftX, previewShiftY);

        pageOffSets.add(0);
    }

    private void printNewLines(int lineCount) {
        for (int i = 0; i < lineCount; i++) {
            System.out.println();
        }
    }
}
