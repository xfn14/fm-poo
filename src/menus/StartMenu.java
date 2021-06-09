package menus;

import exceptions.FileIOException;
import objects.game.GameManager;
import objects.player.Defender;
import objects.player.Player;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.FileUtils;
import utils.TextUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StartMenu {
    private final Scanner scanner = new Scanner(System.in);
    private GameManager gameManager;

    public StartMenu(){
        this.gameManager = new GameManager();
    }

    public StartMenu(GameManager gameManager){
        this.gameManager = gameManager.clone();
    }

    public StartMenu(StartMenu startMenu){
        this.gameManager = startMenu.getGameManager();
    }

    public void startMenuLoop(){
        boolean quit = false;

        do{
            printStartMenu();
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    System.out.println(ColorUtils.GREEN_UNDERLINED + "Thank you for playing!" + ColorUtils.RESET);
                    quit = true;
                }else if(option == 1) {
                    // TODO: 6/5/2021 Ask for file location 
                    GameManager tempGameManager = FileUtils.loadLogFile("resources/logs.txt");
                    tempGameManager.setGameList(FileUtils.loadGameSimFile("resources/gameSims.txt", tempGameManager.getGameList()));
                    // TODO: 6/8/2021 pass team as argument
                    tempGameManager.updateTeamVictoriesHistory();
                    this.gameManager = tempGameManager.clone();
                    System.out.println(ColorUtils.GREEN_BOLD + "Finished loading log file!" + ColorUtils.RESET);
                }else if(!this.gameManager.getTeamMap().isEmpty() && option == 2){
                    NewGameMenu newGameMenu = new NewGameMenu(this.gameManager);
                    newGameMenu.newGameMenu();
                    this.gameManager = newGameMenu.getGameManager();
                }else if(!this.gameManager.getGameList().isEmpty() && option == 3){
                    ManagerMenu managerMenu = new ManagerMenu(gameManager);
                    managerMenu.manageGamesLoop();
                }else{
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }catch(InputMismatchException e){
                System.out.println(TextUtils.INPUT_NOT_NUMBER);
                scanner.next();
            }catch (FileIOException e){
                System.out.println(e.getLocalizedMessage());
                quit = true;
            }catch (FileNotFoundException e){
                System.out.println(e.getLocalizedMessage());
            }
        } while (!quit);
    }

    public void printStartMenu(){
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load Log" + ColorUtils.RESET);
        if(!this.gameManager.getTeamMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "New Game " + ColorUtils.WHITE_UNDERLINED + "(In progress)" + ColorUtils.RESET);
        if(!this.gameManager.getGameList().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[3]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manager Options" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Quit Game" + ColorUtils.RESET);
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }
}
