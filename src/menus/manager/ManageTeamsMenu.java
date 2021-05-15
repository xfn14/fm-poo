package menus.manager;

import menus.ManagerMenu;
import objects.game.GameManager;
import objects.player.*;
import objects.team.Team;
import utils.ColorUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageTeamsMenu {
    private GameManager gameManager;
    private final int maxPage;

    public static final int ITEMS_PER_PAGE = 10;

    public ManageTeamsMenu(){
        this.gameManager = new GameManager();
        this.maxPage = 1;
    }

    public ManageTeamsMenu(GameManager gameManager) {
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getTeamMap().size() / ITEMS_PER_PAGE);
    }

    public ManageTeamsMenu(GameManager gameManager, int maxPage) {
        setGameManager(gameManager);
        this.maxPage = maxPage;
    }

    public ManageTeamsMenu(ManagePlayersMenu managePlayersMenu){
        this.gameManager = managePlayersMenu.getGameManager();
        this.maxPage = managePlayersMenu.getMaxPage();
    }

    public void teamListLoop(){
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
        System.out.println(ColorUtils.BLUE + "[1] - Previous Page; [2] - Next Page; ; [0] - Go Back" + ColorUtils.RESET);
    }

    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int teamListLen = this.gameManager.getTeamMap().size();
        for(int i = fstIndex; i < Math.min(teamListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(teamSimpleInfo((Team) this.gameManager.getTeamMap().values().toArray()[i], i));
        printControls();
    }

    public String teamSimpleInfo(Team team, int id){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.RED_BACKGROUND_BRIGHT).append("[").append(id).append("]");
        sb.append(ColorUtils.WHITE).append(" - ").append(ColorUtils.GREEN).append(" ");
        sb.append(team.getName()).append(" | ");
        sb.append(team.getTeamVictories()).append(" | ");
        sb.append(
                team.getTeamPlayers().stream()
                        .map(Player::getFstLstName)
                        .collect(Collectors.toList())
        );

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
