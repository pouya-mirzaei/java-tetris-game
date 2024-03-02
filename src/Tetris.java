import javax.sql.rowset.serial.SerialArray;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class Tetris {
    private final int rows = 15;
    private final int columns = 10;
    private final int maxHeight = 4; // sync with the RandomShape class
    private final int extraGap = 1;
    private final int totalRows = rows + maxHeight + extraGap;
    private final int[][] board = new int[totalRows][columns];
    Operation op = new Operation();
    Scanner sc = new Scanner(System.in);
    AnalyzeBoard boardCheck = new AnalyzeBoard();

    public Tetris() {
        // filling the board with the initial values
        for (int i = 0; i < totalRows; i++) {
            board[i][0] = 1;
            board[i][columns - 1] = 1;
            if (i == totalRows - 1) {
                for (int j = 0; j < columns; j++) {
                    board[i][j] = 1;
                }
            }
        }

    }

    public void startGame(byte difficulty) throws InterruptedException {
        RandomShape randomShape = new RandomShape(difficulty);

        while (true) {
            int[][] newShape = randomShape.generateShape();

            byte startingRowIndex = 0;
            byte startingColIndex = (columns - 2) / 2;
            addNewShape(newShape, board);

            boardCheck.analyze(board);
            displayBoard(board);
            label:
            while (true) {
                String move = sc.next();

                switch (move) {
                    case "m":
                        while (op.moveDown(board, newShape, startingRowIndex, startingColIndex, true)[0][0] != -1) {

                            startingRowIndex++;
                            displayBoard(board);
                            Thread.sleep(10);
                        }
                        break label;
                    case "a":
                        if (op.moveLeft(board, newShape, startingRowIndex, startingColIndex)[0][0] == -1) {
                            continue;
                        }
                        startingColIndex--;
                        displayBoard(board);
                        if (!op.isMoveAvailable(board, "m", newShape, startingRowIndex, startingColIndex))
                            break label;
                        break;
                    case "s":
                        if (op.moveDown(board, newShape, startingRowIndex, startingColIndex, true)[0][0] == -1) {
                            break label;
                        }
                        displayBoard(board);
                        startingRowIndex++;
                        if (!op.isMoveAvailable(board, "m", newShape, startingRowIndex, startingColIndex))
                            break label;

                        break;
                    case "d":
                        if (op.moveRight(board, newShape, startingRowIndex, startingColIndex)[0][0] == -1) {
                            break label;
                        }
                        startingColIndex++;


                        if (!op.isMoveAvailable(board, "m", newShape, startingRowIndex, startingColIndex))
                            break label;
                        displayBoard(board);

                        break;
                    case "w":
                        newShape = op.rotate(board, newShape, startingRowIndex, startingColIndex);
                        if (newShape[0][0] == -1) {
                            break label;
                        }

                        if (op.isMoveAvailable(board, "m", newShape, startingRowIndex, startingColIndex)) {
                            op.moveDown(board, newShape, startingRowIndex, startingColIndex, true);
                            startingRowIndex++;
                        }

                        if (!op.isMoveAvailable(board, "m", newShape, startingRowIndex, startingColIndex))
                            break label;

                        displayBoard(board);
                        break;
                }
            }
        }
    }

    public void addNewShape(int[][] shape, int[][] board) {
        //clearing top part the board
        for (int i = 0; i < maxHeight + extraGap; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = 0;
            }
        }
        // inserting the new shape
        byte startingIndex = (columns - 2) / 2;
        for (int i = 0; i < shape.length; i++) {
            for (int j = startingIndex; j < shape[0].length + startingIndex; j++)
                board[i][j] = shape[i][j - startingIndex];
        }
    }

    public void displayBoard(int[][] board) {
        this.clearScreen();
        for (int i = 0; i < totalRows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println("Score :" + AnalyzeBoard.score);
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
