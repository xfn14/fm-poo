package menus;

import exceptions.FileInvalidLineException;
import objects.game.GameManager;
import utils.ColorUtils;
import utils.FileUtils;
import utils.TextUtils;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartMenu {
    /**
     * Input Scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * GameManager instance
     */
    private GameManager gameManager;

    /**
     * Instantiates a StartMenu
     */
    public StartMenu(){
        this.gameManager = new GameManager();
    }

    /**
     * Instantiates a StartMenu with respective attributes
     * @param gameManager GameManager's object
     */
    public StartMenu(GameManager gameManager){
        this.gameManager = gameManager.clone();
    }

    /**
     * Instantiates a StartMenu from a StartMenu's object
     * @param startMenu
     */
    public StartMenu(StartMenu startMenu){
        this.gameManager = startMenu.getGameManager();
    }

    /**
     * Start Menu to interact with user
     */
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
                }else if(option == 2 && !this.gameManager.getTeamMap().isEmpty()){
                    try{
                        NewGameMenu newGameMenu = new NewGameMenu(this.gameManager);
                        newGameMenu.newGameMenu();
                        this.gameManager = newGameMenu.getGameManager();
                    }catch (InterruptedException e){
                        System.err.format("InterruptedException: %s%n", e);
                    }
                }else if(option == 3 && !this.gameManager.getGameList().isEmpty()){
                    ManagerMenu managerMenu = new ManagerMenu(gameManager);
                    managerMenu.manageGamesLoop();
                }else{
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }catch(InputMismatchException e){
                System.out.println(TextUtils.INPUT_NOT_NUMBER);
                scanner.next();
            }catch (FileInvalidLineException e){
                System.out.println(e.getLocalizedMessage());
                quit = true;
            }catch (FileNotFoundException e){
                System.out.println(e.getLocalizedMessage());
            }
        } while (!quit);
    }

    /**
     * Print Start Menu
     */
    public void printStartMenu(){
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load Log" + ColorUtils.RESET);
        if(!this.gameManager.getTeamMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "New Game " + ColorUtils.WHITE_UNDERLINED + "(In progress)" + ColorUtils.RESET);
        if(!this.gameManager.getGameList().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[3]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manager Options" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Quit Game" + ColorUtils.RESET);
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
