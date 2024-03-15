public class AnalyzeBoard {
    public static int score;
    private final int maxHeight = 4; // sync with the Tetris class
    private final int extraGap = 2; // sync with the Tetris class
    Operation op = new Operation();


    public String analyze(int[][] board) throws InterruptedException {
        int currentScore = score;
        int scoreEarned = 0;
        String status = "";

        // check if any row is full
        for (int i = maxHeight + extraGap; i < board.length - 1; i++) {

            boolean isRowFull = true;
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j] == 0)
                    isRowFull = false;
            }
            if (isRowFull) {
                handleFullRow(board, i);
                scoreEarned = score - currentScore;
                status = "Nice, You earned " + scoreEarned + " points!";
            }
        }

        currentScore = score;
        // check if any col is complete
        for (int i = 1; i < board[0].length - 1; i++) {
            if (board[maxHeight + extraGap][i] == 1) {
                handleFullColumn(board, i);
            }
        }
        // handling the status message
        if (status.isEmpty() && currentScore != score) {
            status = "You lost " + (score - currentScore) + " points:(";
        } else if (!status.isEmpty() && currentScore != score) {
            status = "You got " + scoreEarned + " points\nBut also lost" + (score - currentScore) + "points :|";
        }

        if (score < 0) {
            Tetris.isGameOver = true;
        }

        return status;
    }


    public void handleFullRow(int[][] board, int row) throws InterruptedException {
        addScore(100);
        for (int j = 1; j < board[0].length - 1; j++) {
            board[row][j] = 0;
        }
        for (int i = 1; i < board[0].length - 1; i++) {
            int[][] col = new int[row][1];
            boolean shouldMoveDown = false;
            for (int j = 0; j < row; j++) {
                col[j][0] = board[j][i];
                if (board[j][i] == 1) {
                    shouldMoveDown = true;
                }
            }

            if (shouldMoveDown)
                op.fullMoveDown(board, col, 0, i, true);

        }
        Thread.sleep(100);
        analyze(board);

    }

    public void handleFullColumn(int[][] board, int colIndex) {
        for (int i = 0; i < board.length - 1; i++) {
            board[i][colIndex] = 0;
        }
        addScore(-10);
    }


    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        AnalyzeBoard.score += score;
    }
}
