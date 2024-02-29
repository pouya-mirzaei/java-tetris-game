import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Typewriter logger = new Typewriter(20);

    public void showMainMenu(String message) throws IOException, InterruptedException {
        clearScreen();
        logger.type(message);

        logger.type("""
                 1. Start
                 2. Scoreboard
                 3. Exit the game
                Select an option to continue=>"""
        );
        byte option = sc.nextByte();
        selectMenuOption(option, 1);
    }

    public void selectMenuOption(int option, int stage) throws IOException, InterruptedException {
        switch (stage) {
            case 1:
                switch (option) {
                    case 1:
                        start();
                        break;
                    case 2:
                        showScoreboard();
                        break;
                    case 3:
                        break;
                    default:
                        showMainMenu("Wrong choice , try again ...");
                        break;
                }
                break;
            case 2:
                switch (option) {
                    case 1:
                        // login ...
                        break;
                    case 2:
                        // sign up ...
                        break;
                }
        }
    }

    private void showScoreboard() throws InterruptedException {
        clearScreen();
        logger.type("scoreboard");
    }

    private void start() {
        clearScreen();

        // should check if the user is logged in ...

        Tetris game = new Tetris();
        game.startGame();

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
