import java.util.Arrays;
import java.util.Random;

public class RandomShape {
    private final int difficulty; //1 => easy , 2 => medium , 3 => hard
    private final int maxWidth = 5;
    private final int maxHeight = 4;
    private final int EASY = 1;
    private final int MEDIUM = 2;
    private final int HARD = 3;

    public RandomShape(int difficulty) {
        this.difficulty = difficulty;
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
        System.out.println("lineShape");

        int width = (int) (Math.random() * (maxWidth + 1) - 1) + 1;

        int[][] line = new int[1][width];
        Arrays.fill(line[0], 1);

        return line;
    }

    private int[][] LShape() {
        System.out.println("LShape");

        int width = (int) (Math.random() * maxWidth - 2) + 2;
        int height = (int) (Math.random() * maxHeight - 1) + 1;

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
        System.out.println("ZShape");
        return new int[][]{
                {0, 1, 1},
                {1, 1, 0}
        };
    }

    private int[][] square() {
        System.out.println("square");

        int width = (int) (Math.random() * 3 - 2) + 3;

        int[][] square = new int[width][width];
        for (int i = 0; i < width; i++) {
            Arrays.fill(square[i], 1);
        }

        return square;
    }

    private int[][] baton() {
        System.out.println("baton");
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
        System.out.println("rectangle");
        int width = (int) (Math.random() * maxWidth - 2) + 2;
        int height = (int) (Math.random() * maxHeight - 2) + 2;

        int[][] rectangle = new int[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(rectangle[i], 1);
        }

        return rectangle;
    }

    public int[][] bowl() {
        System.out.println("Bowl");
        return new int[][]{
                {1, 0, 1},
                {1, 1, 1},
        };
    }

    /*  hard
        random
    */
    private int[][] fullyRandomShape() {
        System.out.println("fully random");
        return new int[2][1];
    }

}
