public class AnalyzeBoard {
    public static int score;
    private final int maxHeight = 4; // sync with the Tetris class
    private final int extraGap = 2; // sync with the Tetris class
    Operation op = new Operation();


    public void analyze(int[][] board) {
        for (int i = maxHeight + extraGap - 1; i < board.length - 1; i++) {


            boolean isRowFull = true;
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j] == 0)
                    isRowFull = false;
            }
            if (isRowFull) {
                handleFullRow(board, i);
            }
        }

    }


    public void handleFullRow(int[][] board, int row) {
        addScore(100);
        for (int j = 1; j < board[0].length - 1; j++) {
            board[row][j] = 0;
        }

        int[][] upperRow = new int[row][board[0].length - 2];
        for (int j = 0; j < row; j++) {
            for (int k = 0; k < board[0].length - 2; k++) {
                upperRow[j][k] = board[j][k];
            }
        }

        op.moveDown(board, upperRow, 0, 1, false);

    }


    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        AnalyzeBoard.score += score;
    }
}
