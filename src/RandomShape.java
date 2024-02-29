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
                break;
            case MEDIUM:
                int mediumId = (int) (Math.random() * 4 + 2);
                switch (mediumId) {
                    case 2:
                        return ZShape();
                    case 3:
                        return square();
                    case 4:
                        return baton();
                    case 5:
                        return rectangle();
                }
                break;
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

        int width = (int) (Math.random() * maxWidth - 1) + 1;

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

     * */
    private int[][] ZShape() {
        System.out.println("ZShape");
        return new int[1][];
    }

    private int[][] square() {
        System.out.println("square");
        return new int[1][];
    }

    private int[][] baton() {
        System.out.println("baton");
        return new int[1][];
    }

    private int[][] rectangle() {
        System.out.println("rectangle");
        return new int[1][];
    }

    /*  hard
        random
    */
    private int[][] fullyRandomShape() {
        System.out.println("fully random");
        return new int[2][1];
    }

}
