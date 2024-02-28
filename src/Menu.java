import java.io.IOException;
import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);

    public void showMainMenu(String message) throws IOException {
        clearScreen();
        System.out.println(message);
        System.out.println(" 1. Start");
        System.out.println(" 2. Scoreboard");
        System.out.println(" 3. Exit the game");
        System.out.print("Select an option to continue\n=>");
        byte option = sc.nextByte();
        selectMenuOption(option,1);
    }

    public void selectMenuOption(int option, int stage) throws IOException {
     switch (stage){
         case 1:
             switch (option){
                 case 1:
                     start();
                     break;
                 case 2:
                     showScoreboard();
                     break;
                 case 3 :
                     break;
                 default:
                     showMainMenu("Wrong choice , try again ...");
                     break;
             }
             break;
         case 2:
             switch (option){
                 case 1 :
                     // login ...
                     break;
                 case 2 :
                     // sign up ...
                     break;
             }
     }
    }

    private void showScoreboard() {
        clearScreen();
        System.out.println("scoreboard");
    }

    private void start() {
    clearScreen();
        System.out.println("start");
    }

    public void clearScreen(){
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
