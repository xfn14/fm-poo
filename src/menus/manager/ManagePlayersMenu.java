package menus.manager;

import menus.ManagerMenu;
import objects.game.GameManager;
import objects.player.*;
import utils.ColorUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ManagePlayersMenu {
    private GameManager gameManager;
    private final int maxPage;

    public static final int ITEMS_PER_PAGE = 10;

    public ManagePlayersMenu(){
        this.gameManager = new GameManager();
        this.maxPage = 1;
    }

    public ManagePlayersMenu(GameManager gameManager) {
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getPlayerMap().size() / ITEMS_PER_PAGE);
    }

    public ManagePlayersMenu(GameManager gameManager, int maxPage) {
        setGameManager(gameManager);
        this.maxPage = maxPage;
    }

    public ManagePlayersMenu(ManagePlayersMenu managePlayersMenu){
        this.gameManager = managePlayersMenu.getGameManager();
        this.maxPage = managePlayersMenu.getMaxPage();
    }

    public void playerListLoop(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        int page = 1;
        printPage(page);

        do{
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    ManagerMenu managerMenu = new ManagerMenu(this.gameManager);
                    managerMenu.manageGamesLoop();
                    quit = true;
                }else if(option == 1){
                    if(page <= 1) System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                    else printPage(--page);
                }else if(option == 2) {
                    if (page >= maxPage) System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                    else printPage(++page);
                }else{
                    System.out.println(ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET);
                }
            }catch (InputMismatchException e){
                System.out.println(ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET);
                scanner.next();
            }
        }while (!quit);
        scanner.close();
    }

    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[1] - Previous Page; [2] - Next Page; [3] - New Player; [0] - Go Back" + ColorUtils.RESET);
    }

    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int playerListLen = this.gameManager.getPlayerMap().size();
        for(int i = fstIndex; i < Math.min(playerListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(playerSimpleInfo(this.gameManager.getPlayerMap().get(i)));
        printControls();
    }

    public String playerSimpleInfo(Player player){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.RED_BACKGROUND_BRIGHT).append("[").append(player.getId()).append("]");
        sb.append(ColorUtils.WHITE).append(" - ").append(ColorUtils.GREEN).append(" ");
        sb.append(player.getName()).append(" | ");
        sb.append(player.getNumber()).append(" | ");

        if(player instanceof Keeper) sb.append("Keeper");
        else if(player instanceof Defender) sb.append("Defender");
        else if(player instanceof FullBack) sb.append("Full-Back");
        else if(player instanceof MidFielder) sb.append("Mid-Fielder");
        else if(player instanceof Striker) sb.append("Striker");

        if(!player.getTeamHistory().isEmpty())
            sb.append(" | ").append(player.getTeamHistory().get(player.getTeamHistory().size()-1));

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
