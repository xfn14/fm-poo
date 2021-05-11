package menus;

import exceptions.FileIOException;
import objects.game.GameSim;
import utils.ColorUtils;
import utils.FileUtils;
import utils.Parser;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class StartMenu {
    public static void startMenuLoop(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        printStartMenu();

        do{
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    System.out.println(ColorUtils.GREEN_UNDERLINED + "Thank you for playing!" + ColorUtils.RESET);
                    quit = true;
                }else if(option == 1){
                    List<GameSim> gameSims = Parser.loadLogFile("resources/logs.txt");
                    System.out.println(gameSims.get(0));
                    quit = true;
                }else{
                    System.out.println(ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET);
                }
            }catch(InputMismatchException e){
                System.out.println(ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET);
                scanner.next();
            }catch (FileIOException e){
                System.out.println(e.getMessage());
                quit = true;
            }
        } while (!quit);
            scanner.close();
    }

    public static void printStartMenu(){
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load Log" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Quit Game" + ColorUtils.RESET);
    }
}
