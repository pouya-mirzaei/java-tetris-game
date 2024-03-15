import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    static Scanner sc = Main.sc;
    Typewriter logger = new Typewriter(5);

    public static void clearScreen() {
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

    public static int getInput(String message, int result) {
        System.out.println(message);
        try {
            result = sc.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("Wrong input , try again ...");
            result = getInput(message, result);
        }

        return result;
    }

    public static String getInput(String message, String result) {
        System.out.println(message);
        try {
            result = sc.next();
        } catch (InputMismatchException ime) {
            System.out.println("Wrong input , try again ...");
            result = getInput(message, result);
        }

        return result;
    }

    public void showMainMenu(String message) throws IOException, InterruptedException {
        clearScreen();
        logger.type(message, true);

        logger.type("""
                 1. Start
                 2. How to play
                 3. Exit the game
                Select an option to continue=>""", true
        );
        boolean isInputValid = false;
        byte option = 0;
        do {

            try {
                option = sc.nextByte();
                isInputValid = true;

            } catch (InputMismatchException imp) {
                System.err.println("Wrong answer , try again ...");
                sc.next();
            }
        } while (!isInputValid);

        selectMenuOption(option, 1);
    }

    public byte showSubMenu(String[] items, String message) throws InterruptedException {
        clearScreen();
        logger.type(message, true);

        String result = " ";

        for (int i = 0; i < items.length; i++) {
            result = result + (i + 1) + ". " + items[i] + "\n ";
        }
        result = result.substring(0, result.length() - 2);
        logger.type(result, true);
        byte id = sc.nextByte();
        if (id - 1 >= items.length || id - 1 < 0) {
            return showSubMenu(items, "Wrong choice . try again ...");
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
                        howToPlay();
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

    private void howToPlay() throws InterruptedException, IOException {
        new HowToPlay().start();
    }

    private void start() throws InterruptedException, IOException {
        byte itemSelected = showSubMenu(new String[]{"easy", "medium", "hard"}, "Select difficulty");

        // should check if the user is logged in ...

        Tetris game = new Tetris();
        game.startGame(itemSelected);

    }

}
