public class RandomShape {
    private final int difficulty; //1 => easy , 2 => medium , 3 => hard
    private final int maxWidth = 5;
    private final int maxHeight = 4;

    public RandomShape(int difficulty) {
        this.difficulty = difficulty;
    }

    public int[][] generateLShape() {
        int[][] shape = new int[maxHeight][maxWidth];
        if (difficulty == 1) {
            for (int i = 0; i < maxHeight; i++) {
                for (int j = 0; j < maxWidth; j++) {
                    if (Math.random() > .5)
                        shape[i][j] = 1;
                }
            }
        }
        return shape;
    }

}
