public class Tetris {
    private final int rows = 15;
    private final int columns = 10;
    private final int[][] board = new int[rows][columns];

    public Tetris() {
        // filling the board with the initial values
        for (int i = 0; i < rows; i++) {
            board[i][0] = 1;
            board[i][columns - 1] = 1;
            if (i == rows - 1) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = 1;
                }
            }
        }
    }

    public void startGame() {
        displayBoard();

        // trash code ...
        int[][] randShape = new RandomShape(1).generateLShape();

        addNewShape(randShape);
        System.out.println();
        displayBoard();
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
