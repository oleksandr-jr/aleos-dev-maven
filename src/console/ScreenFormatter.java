package console;

/**
 * The ScreenFormatter class is responsible for formatting the screen matrix by creating a frame around it.
 * It updates the shiftX, shiftY, and cursor position of the screen after applying the frame.
 */
public class ScreenFormatter {

    /**
     * Create a frame around the screen matrix and update the cursor position.
     *
     * @param screen The screen object to create a frame for.
     */
    public void createFrame(Screen screen) {
        int WIDTH = screen.getWidth();
        int HEIGHT = screen.getHeight();

        char[][] matrix = screen.getMatrix();

        // Draw the top and bottom borders
        for (int column = 0; column < WIDTH; column++) {
            matrix[column][0] = '-';
            matrix[column][HEIGHT - 1] = '-';
        }

        // Draw the left and right borders
        for (int row = 1; row < HEIGHT - 1; row++) {
            matrix[0][row] = '|';
            matrix[WIDTH - 1][row] = '|';
        }

        // Update the screen's shiftX, shiftY, and cursor position
        screen.setShiftX(screen.getShiftX() + 2);
        screen.setShiftY(screen.getShiftY() + 1);
        screen.setCursorPosition(screen.getRowCapacity() * screen.getShiftY() + screen.getShiftX());
    }
}
