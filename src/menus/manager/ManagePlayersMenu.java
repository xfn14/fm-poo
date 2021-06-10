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
    /**
     * Input Scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * GameManager instance
     */
    private GameManager gameManager;

    /**
     * Number of pages
     */
    private final int maxPage;

    /**
     * Items per page
     */
    public static final int ITEMS_PER_PAGE = 10;

    /**
     * Instantiates a ManagePlayersMenu
     */
    public ManagePlayersMenu(){
        this.gameManager = new GameManager();
        this.maxPage = 1;
    }

    /**
     * Instantiates a ManagePlayersMenu with a specific GameManager
     * @param gameManager GameManager's object
     */
    public ManagePlayersMenu(GameManager gameManager) {
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getPlayerMap().size() / ITEMS_PER_PAGE);
    }

    /**
     * Instantiates a ManagePlayersMenu with the respective attributes
     * @param gameManager GameManager's object
     * @param maxPage Number of pages
     */
    public ManagePlayersMenu(GameManager gameManager, int maxPage) {
        setGameManager(gameManager);
        this.maxPage = maxPage;
    }

    /**
     * Instantiates a ManagePlayersMenu from a ManagePlayersMenu
     * @param managePlayersMenu ManagePlayersMenu's object
     */
    public ManagePlayersMenu(ManagePlayersMenu managePlayersMenu){
        this.gameManager = managePlayersMenu.getGameManager();
        this.maxPage = managePlayersMenu.getMaxPage();
    }

    /**
     * Print requested page for the user to select a Player
     */
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

    /**
     * Print page's controls
     */
    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[a] - Previous Page; [d] - Next Page; [n] - New Player; [playerId] - Player Info; [b] - Go Back" + ColorUtils.RESET);
    }

    /**
     * Print respective page
     * @param page Page index
     */
    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int playerListLen = this.gameManager.getPlayerMap().size();
        for(int i = fstIndex; i < Math.min(playerListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(playerSimpleInfo(this.gameManager.getPlayerMap().get(i)));
        printControls();
    }

    /**
     * Create String representation of a specific Player's Info with the corresponding line ID in the page
     * @param player Player's object
     * @return String of the Player's info and line's id
     */
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

    /**
     * Get Number of pages
     * @return Number of pages
     */
    public int getMaxPage() {
        return this.maxPage;
    }
}
