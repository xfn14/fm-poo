package menus.manager;

import objects.game.GameManager;
import objects.game.GameSim;
import objects.player.Player;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageGamesMenu {
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
     * Instantiates a ManageGamesMenu with a specific GameManager
     * @param gameManager GameManager's object
     */
    public ManageGamesMenu(GameManager gameManager){
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getGameList().size() / ITEMS_PER_PAGE);
    }

    /**
     * Print requested page for the user to select a Game
     */
    public void gameListLoop(){
        boolean quit = false;

        int page = 1;
        do{
            printPage(page);
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("b")) quit = true;
            else if(option.equalsIgnoreCase("a"))
                if(page <= 1) System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                else --page;
            else if(option.equalsIgnoreCase("d"))
                if(page >= maxPage) System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                else ++page;
            else{
                try{
                    int gameId = Integer.parseInt(option);
                    if(0 <= gameId && gameId < this.gameManager.getGameList().size())
                        gameInfoLoop(this.gameManager.getGameList().get(gameId));
                    else System.out.println(ColorUtils.RED_BOLD + "Invalid game id." + ColorUtils.RESET);
                }catch (NumberFormatException e){
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }
        } while (!quit);
    }

    /**
     * Loop and print Game's info
     * @param game Game's object
     */
    private void gameInfoLoop(GameSim game){
        boolean quit = false;
        System.out.println(getGameDetailedInfo(game));
        System.out.println(ColorUtils.BLUE + "[0] - Go Back" + ColorUtils.RESET);
        while(!quit){
            String input = scanner.nextLine();
            if(input.equals("0")) quit = true;
            else System.out.println(TextUtils.INVALID_MENU_OPTION);
        }
    }

    /**
     * String containing Game's info
     * @param game Team's object
     * @return String of Game's info
     */
    private String getGameDetailedInfo(GameSim game){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE).append("Game ").append(game.getId()).append(" Info ").append(ColorUtils.RESET);
        sb.append(ColorUtils.YELLOW).append('[').append(game.getHomeTeam().getName()).append(']')
          .append(ColorUtils.BLUE).append(" vs ")
          .append(ColorUtils.YELLOW).append('[').append(game.getAwayTeam().getName()).append(']').append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Time: ").append(ColorUtils.YELLOW).append(game.getTime()/60).append(':').append(game.getTime()%60).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> State: ").append(ColorUtils.YELLOW).append(game.getGameState().toString()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Score: ").append(ColorUtils.YELLOW).append(game.getGoals().getX()).append(" - ").append(game.getGoals().getY()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Home In-Field: ").append(ColorUtils.YELLOW).append(game.getInFieldHome().stream().map(Player::getFstLstName).collect(Collectors.toList())).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Home Formation: ").append(ColorUtils.YELLOW).append(game.getHomeFormation().toString()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Away In-Field: ").append(ColorUtils.YELLOW).append(game.getInFieldAway().stream().map(Player::getFstLstName).collect(Collectors.toList())).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Home Formation: ").append(ColorUtils.YELLOW).append(game.getAwayFormation().toString()).append(ColorUtils.RESET);
        return sb.toString();
    }

    /**
     * Print page's controls
     */
    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[a] - Previous Page; [d] - Next Page; [gameId] - Game Info; [b] - Go Back" + ColorUtils.RESET);
    }

    /**
     * Print respective page
     * @param page Page index
     */
    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int gameListLen = this.gameManager.getGameList().size();
        for(int i = fstIndex; i < Math.min(gameListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(gameSimpleInfo(this.gameManager.getGameList().get(i)));
        printControls();
    }

    /**
     * Create String representation of a specific Game Simulation's Info
     * @param gameSim GameSim's object
     * @return String of the GameSim's info
     */
    public String gameSimpleInfo(GameSim gameSim){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.RED_BACKGROUND_BRIGHT).append("[").append(gameSim.getId()).append("]");
        sb.append(ColorUtils.WHITE).append(" - ").append(ColorUtils.GREEN).append(" ");
        sb.append(gameSim.getHomeTeam().getName()).append(" ").append(gameSim.getGoals().getX());
        sb.append(" - ");
        sb.append(gameSim.getGoals().getY()).append(" ").append(gameSim.getAwayTeam().getName());
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
}
