package menus.manager;

import objects.game.GameManager;
import objects.player.*;
import objects.team.Team;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.Scanner;
import java.util.stream.Collectors;

public class ManageTeamsMenu {
    private final Scanner scanner = new Scanner(System.in);
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

    private String getTeamDetailedInfo(Team team){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE).append(team.getName()).append("'s Info\n").append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Victories: ").append(ColorUtils.YELLOW).append(team.getTeamVictories()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Players: ").append(ColorUtils.YELLOW).append(team.getTeamPlayers().stream().map(Player::getFstLstName).collect(Collectors.toList())).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Games Played: ").append(ColorUtils.YELLOW).append(team.getPassedGames()).append(ColorUtils.RESET);
        return sb.toString();
    }

    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[a] - Previous Page; [d] - Next Page; [n] - New Team; [teamName] - Team Info; [b] - Go Back" + ColorUtils.RESET);
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
        sb.append(team.calcOverall()).append(" | ");
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
