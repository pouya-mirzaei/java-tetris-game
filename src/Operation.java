public class Operation {

    public boolean isMoveAvailable(int[][] board, String move, int[][] shape, int rowIndex, int colIndex) {
        switch (move) {
            case "r": {
                boolean lastColToCheck = false;
                for (int i = colIndex + shape[0].length - 1; i >= colIndex; i--) {
                    for (int j = rowIndex; j < rowIndex + shape.length; j++) {
                        if (shape[j - rowIndex][i - colIndex] == 1) {
                            lastColToCheck = true;
                            if (board[j][i + 1] == 1) {
                                return false;
                            }
                        }
                    }
                    if (lastColToCheck) {
                        return true;
                    }
                }
                break;
            }
            case "l": {
                boolean lastColToCheck = false;
                for (int i = colIndex; i < shape[0].length + colIndex; i++) {
                    for (int j = rowIndex; j < shape.length + rowIndex; j++) {
                        if (shape[j - rowIndex][i - colIndex] == 1) {
                            lastColToCheck = true;
                            if (board[j][i - 1] == 1) {
                                return false;
                            }
                        }
                    }
                    if (lastColToCheck) {
                        return true;
                    }
                }
                break;
            }
            case "m": {
                boolean lastRowToCheck = false;
                for (int i = rowIndex + shape.length - 1; i >= rowIndex; i--) {
                    for (int j = colIndex + shape[0].length - 1; j >= colIndex; j--) {
                        if (shape[i - rowIndex][j - colIndex] == 1) {
                            lastRowToCheck = true;
                            if (board[i + 1][j] == 1) {
                                return false;
                            }
                        }
                    }
                    if (lastRowToCheck) {
                        return true;
                    }
                }
            }
            break;

        }
        return true;
    }

    public int[][] moveDown(int[][] board, int[][] shape, int rowIndex, int colIndex) {
        if (!isMoveAvailable(board, "m", shape, rowIndex, colIndex)) {
            return new int[][]{{-1}};
        }

        for (int i = rowIndex + shape.length - 1; i >= rowIndex; i--) {
            for (int j = colIndex; j < shape[0].length + colIndex; j++) {
                if (i >= board.length || board[i][j] == 0) {
                    continue;
                }
                board[i + 1][j] = board[i][j];
                if (i != 0) {
                    board[i][j] = board[i - 1][j];

                } else {
                    board[i][j] = 0;
                }

            }
        }

        return board;
    }

    public int[][] moveLeft(int[][] board, int[][] shape, int rowIndex, int colIndex) {
        if (!isMoveAvailable(board, "l", shape, rowIndex, colIndex)) {
            return new int[][]{{-1}};
        }

        for (int i = colIndex; i < colIndex + shape[0].length; i++) {
            for (int j = rowIndex; j < rowIndex + shape.length; j++) {
                board[j][i - 1] = board[j][i];
                board[j][i] = board[j][i + 1];
            }
        }

        return board;
    }

    public int[][] moveRight(int[][] board, int[][] shape, int rowIndex, int colIndex) {
        if (!isMoveAvailable(board, "r", shape, rowIndex, colIndex)) {
            return new int[][]{{-1}};
        }

        for (int i = colIndex + shape[0].length - 1; i >= colIndex; i--) {
            for (int j = rowIndex; j < rowIndex + shape.length; j++) {
                board[j][i + 1] = board[j][i];
                board[j][i] = board[j][i - 1];
            }
        }

        return board;
    }


}
