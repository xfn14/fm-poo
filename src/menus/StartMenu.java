package menus;

import exceptions.FileIOException;
import objects.game.GameManager;
import utils.ColorUtils;
import utils.FileUtils;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class StartMenu {
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
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        printStartMenu();

        do{
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    System.out.println(ColorUtils.GREEN_UNDERLINED + "Thank you for playing!" + ColorUtils.RESET);
                    quit = true;
                }else if(option == 1) {
                    GameManager tempGameManager = FileUtils.loadLogFile("resources/logs.txt");
                    tempGameManager.setGameList(FileUtils.loadGameSimFile("resources/gameSims.txt", tempGameManager.getGameList()));
                    tempGameManager.updateTeamVictoriesHistory();
                    this.gameManager = tempGameManager.clone();
                    System.out.println(ColorUtils.GREEN_BOLD + "Finished loading log file!" + ColorUtils.RESET);
                    printStartMenu();
                }else if(!this.gameManager.getGameList().isEmpty() && option == 3){
                    GameListMenu gameListMenu = new GameListMenu(gameManager);
                    gameListMenu.gameListLoop();
                    quit = true;
                }else{
                    System.out.println(ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET);
                }
            }catch(InputMismatchException e){
                System.out.println(ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET);
                scanner.next();
            }catch (FileIOException e){
                System.out.println(e.getLocalizedMessage());
                quit = true;
            }
        } while (!quit);
        scanner.close();
    }

    public void printStartMenu(){
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[1]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Load Log" + ColorUtils.RESET);
        if(!this.gameManager.getTeamMap().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[2]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "New Game" + ColorUtils.RESET);
        if(!this.gameManager.getGameList().isEmpty())
            System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[3]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Game List" + ColorUtils.RESET);
        System.out.println(ColorUtils.BLACK_BACKGROUND_BRIGHT + "[0]" + ColorUtils.WHITE + " - " + ColorUtils.GREEN + "Quit Game" + ColorUtils.RESET);
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StartMenu{");
        sb.append("gameManager=").append(gameManager);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StartMenu startMenu = (StartMenu) o;
        return Objects.equals(gameManager, startMenu.gameManager);
    }

    public StartMenu clone(){
        return new StartMenu(this);
    }
}
