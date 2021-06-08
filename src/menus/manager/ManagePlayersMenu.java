package menus.manager;

import menus.manager.playerManager.PlayerCreationMenu;
import menus.manager.playerManager.PlayerInfoMenu;
import objects.game.GameManager;
import objects.player.*;
import utils.ColorUtils;
import utils.TextUtils;

import java.io.IOException;
import java.util.Scanner;

public class ManagePlayersMenu {
    private final Scanner scanner = new Scanner(System.in);
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
        boolean quit = false;

        int page = 1;
        do{
            printPage(page);
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("b")){
                quit = true;
            }else if(option.equalsIgnoreCase("a")){
                if(page <= 1) System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                else --page;
            }else if(option.equalsIgnoreCase("d")) {
                if (page >= maxPage)
                    System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                else ++page;
            }else if(option.equalsIgnoreCase("n")){
                PlayerCreationMenu playerCreationMenu = new PlayerCreationMenu(this.gameManager);
                playerCreationMenu.playerCreationMenu();
                this.gameManager = playerCreationMenu.getGameManager().clone();
                quit = true;
            }else{
                try{
                    int player_id = Integer.parseInt(option);
                    if(0 <= player_id && player_id < this.gameManager.getPlayerMap().size()){
                        PlayerInfoMenu playerInfoMenu = new PlayerInfoMenu(this.gameManager, player_id);
                        playerInfoMenu.playerInfoLoop();
                        this.gameManager = playerInfoMenu.getGameManager();
                        quit = true;
                    }else{
                        System.out.println(ColorUtils.RED_BOLD + "Invalid player id." + ColorUtils.RESET);
                    }
                }catch (NumberFormatException e){
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }
        }while (!quit);
    }

    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[a] - Previous Page; [d] - Next Page; [n] - New Player; [playerId] - Player Info; [b] - Go Back" + ColorUtils.RESET);
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
