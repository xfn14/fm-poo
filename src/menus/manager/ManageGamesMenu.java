package menus.manager;

import menus.ManagerMenu;
import objects.game.GameManager;
import objects.game.GameSim;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageGamesMenu {
    private GameManager gameManager;
    private final int maxPage;

    public static final int ITEMS_PER_PAGE = 10;

    public ManageGamesMenu(){
        this.gameManager = new GameManager();
        this.maxPage = 1;
    }

    public ManageGamesMenu(GameManager gameManager){
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getGameList().size() / ITEMS_PER_PAGE);
    }

    public ManageGamesMenu(ManageGamesMenu manageGamesMenu){
        this.gameManager = manageGamesMenu.getGameManager();
        this.maxPage = manageGamesMenu.getMaxPage();
    }

    public void gameListLoop(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        int page = 1;
        printPage(page);

        do{
            try{
                int option = scanner.nextInt();
                if(option == 0) {
                    ManagerMenu managerMenu = new ManagerMenu(this.gameManager);
                    managerMenu.manageGamesLoop();
                    quit = true;
                }else if(option == 1) {
                    if(page <= 1) System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                    else printPage(--page);
                }else if(option == 2) {
                    if(page >= maxPage) System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                    else printPage(++page);
                }else{
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }catch(InputMismatchException e){
                System.out.println(TextUtils.INPUT_NOT_NUMBER);
                scanner.next();
            }
        } while (!quit);
        scanner.close();
    }

    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[1] - Previous Page; [2] - Next Page; [0] - Go Back" + ColorUtils.RESET);
    }

    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int gameListLen = this.gameManager.getGameList().size();
        for(int i = fstIndex; i < Math.min(gameListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(gameSimpleInfo(this.gameManager.getGameList().get(i)));
        printControls();
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
