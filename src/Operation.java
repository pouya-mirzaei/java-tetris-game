public class Operation {

    public static void main(String[] args) {
        int[][] board = {
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1},
        };
        int[][] shape = {
                {1, 1},
                {1, 0},
                {1, 0},
        };
        System.out.println(isMoveAvailable(board, "r", shape, 1, 1));
    }

    public static boolean isMoveAvailable(int[][] board, String move, int[][] shape, int rowIndex, int colIndex) {
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
                        } else if (board[j][i] == 1) {
                            return false;
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
                        } else if (board[j][i] == 1) {
                            return false;
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
                        } else if (board[i][j] == 1) {
                            return false;
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

    public int[][] rotate(int[][] board, int[][] shape, int rowIndex, int colIndex) {
        if (!isMoveAvailable(board, "o", shape, rowIndex, colIndex)) {
            return new int[][]{{-1}};
        }

        int[][] rotatedShape = new int[shape[0].length][shape.length];
        byte iCounter = 0;
        byte jCounter = 0;

        for (int i = colIndex + shape[0].length - 1; i >= colIndex; i--) {
            jCounter = 0;
            for (int j = rowIndex; j < rowIndex + shape.length; j++) {
                rotatedShape[iCounter][jCounter] = board[j][i];
                board[j][i] = 0;
                jCounter++;
            }
            iCounter++;
        }


        for (int i = rowIndex; i < rotatedShape.length + rowIndex; i++) {
            for (int j = colIndex; j < rotatedShape[0].length + colIndex; j++) {
                board[i][j] = rotatedShape[i - rowIndex][j - colIndex];
            }
        }

        return rotatedShape;
    }


}
