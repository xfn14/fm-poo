package menus;

import exceptions.FileInvalidLineException;
import objects.game.GameManager;
import utils.ColorUtils;
import utils.FileUtils;
import utils.TextUtils;

import java.io.File;
import java.io.IOException;
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
        this.gameManager = null;
    }

    /**
     * Start Menu to interact with user after loading files
     */
    public void startMenuLoop(){
        boolean quit = false;

        do{
            printStartMenu();
            String option = scanner.nextLine();
            try{
                if(option.equalsIgnoreCase("0")){
                    System.out.println(ColorUtils.GREEN_UNDERLINED + "Thank you for playing!" + ColorUtils.RESET);
                    quit = true;
                }else if(option.equalsIgnoreCase("1")) {
                    try{
                        this.gameManager = loadMenu();
                    }catch (IOException e){
                        System.out.println(ColorUtils.RED + "Invalid file location" + ColorUtils.RESET);
                    }
                    System.out.println(ColorUtils.GREEN_BOLD + "Finished loading!" + ColorUtils.RESET);
                }else if(option.equalsIgnoreCase("2") && this.gameManager != null){
                    System.out.println(ColorUtils.GREEN + "Where do you want to save it? " + ColorUtils.YELLOW + "(Ex: resources/gameOut.dat)" + ColorUtils.RESET);
                    String where = scanner.nextLine();
                    try{
                        FileUtils.saveGameManager(where, this.gameManager);
                        System.out.println(ColorUtils.GREEN + "Saved successfully" + ColorUtils.RESET);
                    }catch (IOException e){
                        e.printStackTrace();
                        System.out.println(ColorUtils.RED + "Failed to save game manager" + ColorUtils.RESET);
                    }
                }else if(option.equalsIgnoreCase("3") && this.gameManager != null && !this.gameManager.getTeamMap().isEmpty()){
                    try{
                        NewGameMenu newGameMenu = new NewGameMenu(this.gameManager);
                        newGameMenu.newGameMenu();
                        this.gameManager = newGameMenu.getGameManager();
                    }catch (InterruptedException e){
                        System.err.format("InterruptedException: %s%n", e);
                    }
                }else if(option.equalsIgnoreCase("4") && this.gameManager != null && !this.gameManager.getGameList().isEmpty()){
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
            }catch (ClassNotFoundException e){
                System.out.println(e.getLocalizedMessage());
            }
        } while (!quit);
    }

    /**
     * Interaction menu to get path of file(s) to load the game
     * @return GameManager's object
     * @throws IOException
     * @throws FileInvalidLineException
     * @throws ClassNotFoundException
     */
    public GameManager loadMenu() throws IOException, FileInvalidLineException, ClassNotFoundException {
        GameManager gameManager = null;
        boolean quit = false;
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load from log" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load from object" + ColorUtils.RESET);
        do{
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("1")){
                System.out.println(ColorUtils.GREEN + "Insert log file location: " + ColorUtils.RESET);
                String logLocation = requestFileLocation();
                System.out.println(ColorUtils.GREEN + "Insert gameSims file location: " + ColorUtils.RESET);
                String gameSimsLocation = requestFileLocation();
                GameManager tempGameManager = FileUtils.loadLogFile(logLocation);
                tempGameManager.setGameList(FileUtils.loadGameSimFile(gameSimsLocation, tempGameManager.getGameList()));
                tempGameManager.updateTeamVictoriesHistory();
                gameManager = tempGameManager;
                quit = true;
            }else if(option.equalsIgnoreCase("2")){
                System.out.println(ColorUtils.GREEN + "Insert file location: " + ColorUtils.RESET);
                String path = requestFileLocation();
                try{
                    GameManager temp = FileUtils.loadGameManager(path);
                    if(temp != null) gameManager = temp;
                    quit = true;
                }catch (IOException e){
                    System.out.println(ColorUtils.RED + "Invalid file format" + ColorUtils.RESET);
                }
            }else{
                System.out.println(TextUtils.INVALID_MENU_OPTION);
            }
        }while(!quit);
        return gameManager;
    }

    /**
     * Get an existing path from user's input
     * @return Path
     */
    public String requestFileLocation(){
        String location = "";
        while(location.isBlank()){
            String crt = scanner.nextLine();
            File file = new File(crt);
            if(file.exists())
                location = crt;
            else System.out.println(ColorUtils.RED + "Invalid file location" + ColorUtils.RESET);
        }
        return location;
    }

    /**
     * Print Start Menu
     */
    public void printStartMenu(){
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load Info" + ColorUtils.RESET);
        if(this.gameManager != null)
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Save Info" + ColorUtils.RESET);
        if(this.gameManager != null && !this.gameManager.getTeamMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[3]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "New Game " + ColorUtils.RESET);
        if(this.gameManager != null && !this.gameManager.getGameList().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[4]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Manager Options" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Quit Game" + ColorUtils.RESET);
    }
}
