import java.util.Arrays;

public class Tetris {
    private final int rows = 20;
    private final int columns = 15;
    private final int maxHeight = 4; // sync with the RandomShape class
    private final int extraGap = 0;
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
        for (int i = 0; i < 35; i++) {
            int[][] shape = new RandomShape(3).generateShape();
            for (int j = 0; j < shape.length; j++) {
                Thread.sleep(250);
                for (int k = 0; k < shape[0].length; k++) {
                    if (shape[j][k] == 1)
                        System.out.print("*");
                    else
                        System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    public void startGame() {
//        displayBoard();

    }

    public void addNewShape(int[][] shape) {
        for (int i = 0; i < shape.length; i++) {
            if (shape[0].length - 2 >= 0) System.arraycopy(shape[i], 0, board[i], 2, shape[0].length - 2);
        }
    }

    public void displayBoard() {

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


}
