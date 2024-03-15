import java.io.IOException;
import java.util.InputMismatchException;

public class HowToPlay {

    public void start() throws InterruptedException, IOException {
        Typewriter logger = new Typewriter(20);
        Menu menu = new Menu();
        Menu.clearScreen();


        logger.type("Hey there, Tetris Titan! Ready to conquer the falling blocks?\nSharpen your skills and stack 'em high. Let the Tetris fun begin! Enjoy and stack on!\"", true);

        Thread.sleep(3000);
        Menu.clearScreen();

        logger.type("To control the shapes in this game, use these 5 keys:", true);
        Thread.sleep(100);
        logger.type("w: Rotate the shape", true);
        Thread.sleep(100);
        logger.type("a: Move the shape one step to the left", true);
        Thread.sleep(100);
        logger.type("d: Move the shape one step to the right", true);
        Thread.sleep(100);
        logger.type("s: Move the shape one step down", true);
        Thread.sleep(100);
        logger.type("m: Move the shape all the way down", true);

        Thread.sleep(200);
        System.out.println();
        logger.type("Here are the rules:", true);
        Thread.sleep(200);
        logger.type("Complete a row to earn 100 points", true);
        logger.type("If a column is full, you will lose 50 points", true);
        logger.type("If your score becomes negative, you lose the game", true);
        Thread.sleep(2000);


        logger.type("press any key to continue...", true);


        try {
            String n = Main.sc.next();
        } catch (InputMismatchException imt) {

        }
        menu.showMainMenu("Tetris game :)");


    }
}
