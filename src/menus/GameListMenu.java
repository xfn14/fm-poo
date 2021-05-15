package menus;

import objects.game.GameManager;
import objects.game.GameSim;
import utils.ColorUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameListMenu {
    private GameManager gameManager;
    private final int maxPage;

    public static final int ITEMS_PER_PAGE = 10;

    public GameListMenu(){
        this.gameManager = new GameManager();
        this.maxPage = 1;
    }

    public GameListMenu(GameManager gameManager){
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getGameList().size() / ITEMS_PER_PAGE);
    }

    public GameListMenu(GameListMenu gameListMenu){
        this.gameManager = gameListMenu.getGameManager();
        this.maxPage = gameListMenu.getMaxPage();
    }

    public void gameListLoop(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        int page = 1;
        printPage(page);
        System.out.println(ColorUtils.BLUE + "[0] - Previous Page; [1] - Next Page; [-1] - Go Back" + ColorUtils.RESET);

        do{
            try{
                int option = scanner.nextInt();
                if(option == -1) {
                    StartMenu startMenu = new StartMenu(gameManager);
                    startMenu.startMenuLoop();
                    quit = true;
                }else if(option == 0) {
                    if(page <= 1)
                        System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                    else
                        printPage(--page);
                }else if(option == 1) {
                    if(page >= maxPage)
                        System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                    else
                        printPage(++page);
                }else{
                    System.out.println(ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET);
                }
            }catch(InputMismatchException e){
                System.out.println(ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET);
                scanner.next();
            }
        } while (!quit);
        scanner.close();
    }

    public void printPage(int page){
        for(int i = (page-1)*ITEMS_PER_PAGE;
            i < Math.min(this.gameManager.getGameList().size(), (page-1)*ITEMS_PER_PAGE + ITEMS_PER_PAGE);
            i++){
            System.out.println(gameSimpleInfo(this.gameManager.getGameList().get(i)));
        }
    }

    public String gameSimpleInfo(GameSim gameSim){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.RED_BACKGROUND_BRIGHT).append("[").append(gameSim.getId()).append("]");
        sb.append(ColorUtils.WHITE).append(" - ").append(ColorUtils.GREEN).append(" ");
        sb.append(gameSim.getHomeTeam().getName()).append(" ").append(gameSim.getGoals().getX());
        sb.append(" - ");
        sb.append(gameSim.getGoals().getY()).append(" ").append(gameSim.getAwayTeam().getName());
        return sb.toString();
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }

    public int getMaxPage() {
        return this.maxPage;
    }
}
