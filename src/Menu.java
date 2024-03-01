import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    Typewriter logger = new Typewriter(5);

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

    public byte showSubMenu(String[] items, String message) throws InterruptedException {
        clearScreen();
        logger.type(message);

        String result = " ";

        for (int i = 0; i < items.length; i++) {
            result = result + (i + 1) + ". " + items[i] + "\n ";
        }
        result = result.substring(0, result.length() - 2);
        logger.type(result);
        byte id = sc.nextByte();
        if (id - 1 >= items.length || id - 1 < 0) {
            showSubMenu(items, "Wrong choice . try again ...");
        }
        return id;


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

    private void start() throws InterruptedException {
        byte itemSelected = showSubMenu(new String[]{"easy", "medium", "hard"}, "Select difficulty");

        // should check if the user is logged in ...

        Tetris game = new Tetris();
        game.startGame(itemSelected);

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
