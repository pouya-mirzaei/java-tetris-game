import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomShape {
    private final int difficulty; //1 => easy , 2 => medium , 3 => hard
    private final int maxWidth = 5;
    private final int maxHeight = Tetris.maxHeight;
    private final int EASY = 1;
    private final int MEDIUM = 2;
    private final int HARD = 3;
    private final int maxStars = 7;

    public RandomShape(int difficulty) {
        this.difficulty = difficulty;
    }

    private static boolean allZeros(int[] row) {
        for (int element : row) {
            if (element != 0) {
                return false;
            }
        }
        return true;
    }

    public int[][] generateShape() {
        switch (difficulty) {
            case EASY:
                int easyId = (int) (Math.random() * 2);
                switch (easyId) {
                    case 0:
                        return lineShape();
                    case 1:
                        return LShape();
                }

            case MEDIUM:
                int mediumId = (int) (Math.random() * 7);

                switch (mediumId) {
                    case 0:
                        return lineShape();
                    case 1:
                        return LShape();
                    case 2:
                        return ZShape();
                    case 3:
                        return square();
                    case 4:
                        return baton();
                    case 5:
                        return rectangle();
                    case 6:
                        return bowl();
                }
            case HARD:
                return fullyRandomShape();
        }
        return new int[1][];

    }

    /* easy
        lineShape -> id = 0
        LShape -> id = 1
    */
    private int[][] lineShape() {

        int width = (int) (Math.random() * (maxWidth - 1)) + 1;

        int[][] line = new int[1][width];
        Arrays.fill(line[0], 1);

        return line;
    }

    private int[][] LShape() {

        int width = (int) (Math.random() * (maxWidth - 2)) + 2;
        int height = (int) (Math.random() * (maxHeight - 1)) + 1;

        int[][] shape = new int[height][width];

        for (int i = 0; i < height; i++) {
            shape[i][0] = 1;
        }

        for (int i = 0; i < width; i++) {
            shape[0][i] = 1;
        }

        return shape;
    }

    /* medium
         ZShape -> id = 2
         square -> id = 3
         baton -> id = 4
         rectangle -> id = 5
         bowl -> id = 6

     * */
    private int[][] ZShape() {
        return new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        };
    }

    private int[][] square() {

        int width = (int) (Math.random() * 3 - 2) + 3;

        int[][] square = new int[width][width];
        for (int i = 0; i < width; i++) {
            Arrays.fill(square[i], 1);
        }

        return square;
    }

    private int[][] baton() {
        int width = Math.random() > .5 ? 3 : 5;

        int height;
        if (width == 3) {
            height = Math.random() > .5 ? 2 : 3;
        } else {
            height = 2;
        }

        int[][] baton = new int[height][width];
        for (int i = 0; i < width; i++) {
            baton[height - 1][i] = 1;
        }
        for (int i = 0; i < height; i++) {
            baton[i][width / 2] = 1;
        }

        return baton;
    }

    private int[][] rectangle() {
        int width = (int) (Math.random() * (maxWidth - 2)) + 1;
        int height = (int) (Math.random() * (maxHeight - 2)) + 1;

        int[][] rectangle = new int[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(rectangle[i], 1);
        }

        return rectangle;
    }

    public int[][] bowl() {
        return new int[][]{
                {1, 0, 1},
                {1, 1, 1},
        };
    }

    /*  hard
        random
    */
    private int[][] fullyRandomShape() {
        int width = (int) (Math.random() * (maxWidth - 2)) + 3;
        int height = (int) (Math.random() * (maxHeight - 1)) + 2;

        int[][] shape = new int[height][width];
        int starsCount = (int) (Math.random() * (maxStars - 2)) + 3;

        byte currentStars = 1;
        shape[0][0] = 1; // first
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (currentStars >= starsCount) {
                    break;
                }
                // jumping from the first item
                if (shape[i][j] == 1) continue;

                boolean shouldPlaceStart = Math.random() > .25;
                if (!shouldPlaceStart) continue;


                if (shape[i][Math.max(j - 1, 0)] == 1 || shape[Math.max(i - 1, 0)][j] == 1) {
                    shape[i][j] = 1;
                    currentStars++;
                }

            }
        }


        return clearMatrix(shape);
    }

    public int[][] clearMatrix(int[][] matrix) {
        List<Integer> rowsToRemove = new ArrayList<>();
        List<Integer> colsToRemove = new ArrayList<>();

        // Find rows to remove
        for (int i = 0; i < matrix.length; i++) {
            if (allZeros(matrix[i])) {
                rowsToRemove.add(i);
            }
        }

        // Find columns to remove
        for (int j = 0; j < matrix[0].length; j++) {
            if (allZerosInColumn(matrix, j)) {
                colsToRemove.add(j);
            }
        }

        // Remove rows and columns from the matrix
        return removeRowsAndColumns(matrix, rowsToRemove, colsToRemove);
    }

    private boolean allZerosInColumn(int[][] matrix, int colIndex) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][colIndex] != 0) {
                return false;
            }
        }
        return true;
    }

    private int[][] removeRowsAndColumns(int[][] matrix, List<Integer> rowsToRemove, List<Integer> colsToRemove) {
        int rowsRemaining = matrix.length - rowsToRemove.size();
        int colsRemaining = matrix[0].length - colsToRemove.size();
        int[][] clearedMatrix = new int[rowsRemaining][colsRemaining];

        int clearedRowIndex = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (!rowsToRemove.contains(i)) {
                int clearedColIndex = 0;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (!colsToRemove.contains(j)) {
                        clearedMatrix[clearedRowIndex][clearedColIndex] = matrix[i][j];
                        clearedColIndex++;
                    }
                }
                clearedRowIndex++;
            }
        }
        return clearedMatrix;
    }

}
