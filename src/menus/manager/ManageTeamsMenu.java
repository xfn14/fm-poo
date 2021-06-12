package menus.manager;

import objects.game.GameManager;
import objects.player.*;
import objects.team.Team;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageTeamsMenu {
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
     * Instantiates a ManageTeamsMenu with a specific GameManager
     * @param gameManager GameManager's object
     */
    public ManageTeamsMenu(GameManager gameManager) {
        setGameManager(gameManager);
        this.maxPage = (int) Math.ceil((double) this.gameManager.getTeamMap().size() / ITEMS_PER_PAGE);
    }

    /**
     * Print requested page for the user to select a Team
     */
    public void teamListLoop(){
        boolean quit = false;

        int page = 1;
        do{
            printPage(page);
            String option = scanner.nextLine();
            if(option.equalsIgnoreCase("b")) quit = true;
            else if(option.equalsIgnoreCase("a"))
                if(page <= 1) System.out.println(ColorUtils.RED + "You are already in the first page." + ColorUtils.RESET);
                else printPage(--page);
            else if(option.equalsIgnoreCase("d"))
                if (page >= maxPage) System.out.println(ColorUtils.RED + "You are already on the last page." + ColorUtils.RESET);
                else printPage(++page);
            else if(option.equalsIgnoreCase("n")){
                Team newTeam = teamCreationMenu();
                this.gameManager.addTeam(newTeam.clone());
            }else if(this.gameManager.getTeamMap().containsKey(option))
                teamInfoLoop(this.gameManager.getTeamMap().get(option));
            else System.out.println(TextUtils.INVALID_MENU_OPTION);
        }while (!quit);
    }

    public Team teamCreationMenu(){
        Team team = null;
        System.out.println(ColorUtils.GREEN + "Insert new team name: " + ColorUtils.RESET);
        while(team == null){
            String input = scanner.nextLine();
            if(!input.isBlank())
                team = new Team(input);
            else System.out.println(ColorUtils.RED + "Invalid team name." + ColorUtils.RESET);
        }
        return team;
    }

    /**
     * Loop and print Team's info
     * @param team Team's object
     */
    private void teamInfoLoop(Team team){
        boolean quit = false;
        System.out.println(getTeamDetailedInfo(team));
        System.out.println(ColorUtils.BLUE + "[0] - Go Back" + ColorUtils.RESET);
        while(!quit){
            String input = scanner.nextLine();
            if(input.equals("0")) quit = true;
            else System.out.println(TextUtils.INVALID_MENU_OPTION);
        }
    }

    /**
     * String containing Team's info
     * @param team Team's object
     * @return String of Team's info
     */
    private String getTeamDetailedInfo(Team team){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE).append(team.getName()).append("'s Info\n").append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Victories: ").append(ColorUtils.YELLOW).append(team.getTeamVictories()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Players: ").append(ColorUtils.YELLOW).append(team.getTeamPlayers().stream().map(Player::getFstLstName).collect(Collectors.toList())).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Games Played: ").append(ColorUtils.YELLOW).append(team.getPassedGames()).append(ColorUtils.RESET).append('\n');
        sb.append(ColorUtils.GREEN).append(" -> Overall: ").append(ColorUtils.YELLOW).append(team.calcOverall()).append(ColorUtils.RESET);
        return sb.toString();
    }

    /**
     * Print page's controls
     */
    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[a] - Previous Page; [d] - Next Page; [n] - New Team; [teamName] - Team Info; [b] - Go Back" + ColorUtils.RESET);
    }

    /**
     * Print respective page
     * @param page Page index
     */
    private void printPage(int page){
        int fstIndex = (page - 1) * ITEMS_PER_PAGE;
        int teamListLen = this.gameManager.getTeamMap().size();
        for(int i = fstIndex; i < Math.min(teamListLen, fstIndex + ITEMS_PER_PAGE); ++i)
            System.out.println(teamSimpleInfo((Team) this.gameManager.getTeamMap().values().toArray()[i], i));
        printControls();
    }

    /**
     * Create String representation of a specific Team's Info with the corresponding line ID
     * @param team Team's object
     * @param id Line's id
     * @return String of the Team's info and line's id
     */
    public String teamSimpleInfo(Team team, int id){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.RED_BACKGROUND_BRIGHT).append("[").append(id).append("]");
        sb.append(ColorUtils.WHITE).append(" - ").append(ColorUtils.GREEN).append(" ");
        sb.append(team.getName()).append(" | ");
        sb.append(team.getTeamVictories()).append(" | ");
        sb.append(team.calcOverall()).append(" | ");
        sb.append(
                team.getTeamPlayers().stream()
                        .map(Player::getFstLstName)
                        .collect(Collectors.toList())
        );
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
