package console;


import java.util.Arrays;

/**
 * The Screen class represents a screen display in the console.
 * It manages a matrix of characters that make up the screen content.
 * The class provides methods for updating, clearing, and drawing the screen.
 */
public class Screen {
    private final char[][] matrix;
    private int shiftX;
    private int shiftY;
    private final int width;
    private final int height;
    private int cursorPosition;
    private int rowCapacity;

    /**
     * Constructor: Initialize screen with specified dimensions and spaces.
     */
    public Screen(int leftX, int topY, int width, int height) {
        this.shiftX = leftX;
        this.shiftY = topY;
        this.width = width;
        this.height = height;
        matrix = new char[this.width][this.height];
        rowCapacity = this.width;
        clear();
    }

    /**
     * Update the given text into the screen matrix and the cursor position.
     * The method clears the screen if there's not enough space for the new text.
     *
     * @param text The text to be inserted into the screen matrix.
     * @return The index of the last character inserted in the screen matrix.
     */
    public int updateMatrix(String text) {
        int textLength = text.length();
        int screenCapacity = (height - shiftY) * (rowCapacity - shiftX);

        // Clear the screen if the text overflows the available screen capacity
        if (cursorPosition + textLength + rowCapacity > screenCapacity) {
            clear();
        }

        int row = cursorPosition / rowCapacity;
        int column = cursorPosition % rowCapacity;
        int start = 0;
        int end;

        // Loop through the text and add it to the matrix
        while (start < textLength) {

            // Calculate the end index for the current line of text
            end = getNextLineIndex(start, text);

            // Update the matrix with the current line of text
            for (int i = start; i < end; i++) {
                matrix[column][row] = text.charAt(i);
                column++;
            }

            // Move to the next row and reset the column
            row++;
            column = shiftX;

            // Break the loop if the end of the text is reached or the row exceeds the screen height
            if (end == textLength || row >= height - shiftY) {
                break;
            }

            // Update the start index for the next iteration, skipping spaces or newline characters
            char lastChar = text.charAt(end);
            start = (lastChar == '\n' || lastChar == ' ') ? end + 1 : end;
        }

        // Update the cursor position
        cursorPosition = row * rowCapacity + shiftX;
        return start;
    }


    /**
     * Get the index of the next line break or suitable word wrap position in the text.
     *
     * @param start The starting index for searching the line break or wrap position.
     * @param text  The text to search in.
     * @return The index of the next line break or word wrap position.
     */
    private int getNextLineIndex(int start, String text) {
        int textLength = text.length();

        int end = Math.min(start + rowCapacity, textLength);

        String currentLine = text.substring(start, end);
        int newLineIndex = currentLine.indexOf('\n');

        if (newLineIndex != -1) {
            return start + newLineIndex;
        }

        int lastSpaceIndex = currentLine.lastIndexOf(' ');

        if (lastSpaceIndex == -1 || textLength > end && (text.charAt(end) == ' ' || text.charAt(end) == '\n')) {
            return end;
        }

        return textLength == end ? end : start + lastSpaceIndex;
    }


    /**
     * Clear the screen matrix, filling it with empty spaces.
     */
    public void clear() {
        for (int column = shiftX; column < width - shiftX; column++) {
            Arrays.fill(matrix[column], shiftY, height - 1, ' ');
        }
        cursorPosition = rowCapacity * shiftY + shiftX;
    }

    /**
     * Draw the current screen matrix to the console.
     */
    public void drawScreen() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                System.out.print(matrix[column][row]);
            }
            System.out.println();
        }
    }


    /**
     * Merge the given screen's matrix into the current screen matrix at the specified position.
     *
     * @param screen The screen whose matrix needs to be merged.
     * @param shiftX The x-axis shift for merging the matrix.
     * @param shiftY The y-axis shift for merging the matrix.
     */
    public void mergeMatrix(Screen screen, int shiftX, int shiftY) {
        for (int row = 0; row < screen.matrix[0].length; row++) {
            for (int column = 0; column < screen.matrix.length; column++) {
                this.matrix[column + shiftX][row + shiftY] = screen.matrix[column][row];
            }
        }
    }


    /**
     * Get the total capacity of the screen matrix (number of characters that can be displayed).
     *
     * @return The capacity of the screen matrix.
     */
    public int getMatrixCapacity() {
        return rowCapacity * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public int getShiftX() {
        return shiftX;
    }

    public int getShiftY() {
        return shiftY;
    }

    public void setShiftX(int shift) {
        shiftX = shift;
        rowCapacity = width - 2 * shiftX;
    }

    public void setShiftY(int shift) {
        shiftY = shift;
    }

    public void setCursorPosition(int position) {
        cursorPosition = position;
    }

    public int getRowCapacity() {
        return rowCapacity;
    }
}
