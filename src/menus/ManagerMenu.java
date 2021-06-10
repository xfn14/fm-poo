package menus;

import menus.manager.ManageGamesMenu;
import menus.manager.ManagePlayersMenu;
import menus.manager.ManageTeamsMenu;
import objects.game.GameManager;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagerMenu {
    /**
     * Input Scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * GameManager instance
     */
    private GameManager gameManager;

    /**
     * Instantiates a ManagerMenu
     */
    public ManagerMenu(){
        this.gameManager = new GameManager();
    }

    /**
     * Instantiates a ManagerMenu with respective attributes
     * @param gameManager GameManager's object
     */
    public ManagerMenu(GameManager gameManager){
        setGameManager(gameManager);
    }

    /**
     * Instantiates a ManagerMenu from a StartMenu's object
     * @param managerMenu
     */
    public ManagerMenu(ManagerMenu managerMenu){
        setGameManager(managerMenu.getGameManager());
    }

    /**
     * Manage Games Menu to interact with user
     */
    public void manageGamesLoop(){
        boolean quit = false;

        do{
            printManageGames();
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    quit = true;
                }else if(option == 1){
                    ManagePlayersMenu managePlayersMenu = new ManagePlayersMenu(this.gameManager);
                    managePlayersMenu.playerListLoop();
                    this.gameManager = managePlayersMenu.getGameManager().clone();
                }else if(option == 2){
                    ManageTeamsMenu manageTeamsMenu = new ManageTeamsMenu(this.gameManager);
                    manageTeamsMenu.teamListLoop();
                    this.gameManager = manageTeamsMenu.getGameManager().clone();
                }else if(option == 3){
                    ManageGamesMenu manageGamesMenu = new ManageGamesMenu(this.gameManager);
                    manageGamesMenu.gameListLoop();
                    this.gameManager = manageGamesMenu.getGameManager().clone();
                }else{
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }catch (InputMismatchException e){
                System.out.println(TextUtils.INPUT_NOT_NUMBER);
                scanner.next();
            }
        }while (!quit);
    }

    /**
     * Print Manage Games Menu
     */
    public void printManageGames(){
        if(!this.gameManager.getPlayerMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manage Players" + ColorUtils.RESET);
        if(!this.gameManager.getTeamMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manage Teams" + ColorUtils.RESET);
        if(!this.gameManager.getGameList().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[3]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manage Games" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Go Back" + ColorUtils.RESET);
    }

    /**
     * Get GameManager
     * @return GameManager's object
     */
    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    /**
     * Set GameManager
     * @param gameManager GameManager's object
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }
}
