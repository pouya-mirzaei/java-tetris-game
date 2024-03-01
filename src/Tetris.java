import java.io.IOException;

public class Tetris {
    private final int rows = 20;
    private final int columns = 15;
    private final int maxHeight = 4; // sync with the RandomShape class
    private final int extraGap = 1;
    private final int totalRows = rows + maxHeight + extraGap;
    private final int[][] board = new int[totalRows][columns];


    public Tetris() throws InterruptedException {
        // filling the board with the initial values
        for (int i = totalRows - rows; i < totalRows; i++) {
            board[i][0] = 1;
            board[i][columns - 1] = 1;
            if (i == rows - 1) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = 1;
                }
            }
        }

    }

    public void startGame(byte difficulty) throws InterruptedException {
        RandomShape randomShape = new RandomShape(difficulty);

        while (true) {
            addNewShape(randomShape.generateShape());

            displayBoard(this.board);
            Thread.sleep(500);
        }


    }

    public void addNewShape(int[][] shape) {
        //clearing top part the board
        for (int i = 0; i < maxHeight + extraGap; i++) {
            for (int j = 0; j < columns; j++) {
                this.board[i][j] = 0;
            }
        }
        // inserting the new shape
        byte startingIndex = (columns - 2) / 2;
        for (int i = 0; i < shape.length; i++) {
            for (int j = startingIndex; j < shape[0].length + startingIndex; j++)
                this.board[i][j] = shape[i][j - startingIndex];
        }
    }

    public void displayBoard(int[][] board) {
        this.clearScreen();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }

            // Now your console is cleared
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
