package menus;

import objects.game.GameManager;
import objects.game.GameSim;
import objects.team.Team;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.Tuple;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NewGameMenu {
    private final Scanner scanner = new Scanner(System.in);
    private GameManager gameManager;

    public NewGameMenu(){
        this.gameManager = new GameManager();
    }

    public NewGameMenu(GameManager gameManager){
        setGameManager(gameManager);
    }

    public NewGameMenu(NewGameMenu newGameMenu){
        this.gameManager = newGameMenu.getGameManager();
    }

    public void newGameMenu(){
        Tuple<Team, TeamFormation> team1, team2;

        System.out.println(ColorUtils.BLUE + "FIRST TEAM SELECTION" + ColorUtils.RESET);
        team1 = selectTeamInfo();

        System.out.println(ColorUtils.BLUE + "SECOND TEAM SELECTION" + ColorUtils.RESET);
        team2 = selectTeamInfo();

        GameSim gameSim = new GameSim(this.gameManager.getGameList().size(), team1.getX().clone(), team2.getX().clone());
        gameSim.setHomeFormation(team1.getY());
        gameSim.setAwayFormation(team2.getY());

        System.out.println(gameSim);
    }

    private Tuple<Team, TeamFormation> selectTeamInfo() {
        Team team = null;
        TeamFormation teamFormation = null;

        System.out.println(ColorUtils.GREEN + "Insert team name: " + ColorUtils.RESET);
        while(team == null){
            String teamName = scanner.nextLine();
            if(!teamName.isBlank() && this.gameManager.getTeamMap().containsKey(teamName))
                team = this.gameManager.getTeamMap().get(teamName).clone();
            else System.out.println(ColorUtils.RED + "Invalid team name" + ColorUtils.RESET);
        }

        System.out.println(teamFormationsString());
        System.out.println(ColorUtils.GREEN + "Choose team formation: " + ColorUtils.RESET);
        while(teamFormation == null){
            try{
                int wantedFormation = scanner.nextInt();
                if (0 <= wantedFormation && wantedFormation < TeamFormation.allFormations.size())
                    teamFormation = TeamFormation.allFormations.get(wantedFormation);
            }catch (InputMismatchException e){
                System.out.println(ColorUtils.RED + "Invalid team formation." + ColorUtils.RESET);
                scanner.next();
            }
        }
        return new Tuple<>(team, teamFormation);
    }

    private String teamFormationsString(){
        int counter = 0;
        StringBuilder sb = new StringBuilder(ColorUtils.BLUE + "Available formations: \n");
        for(TeamFormation teamFormation : TeamFormation.allFormations){
            sb.append("[").append(counter++).append("] ");
            sb.append(teamFormation.getKeepers()).append("-");
            sb.append(teamFormation.getDefenders()).append("-");
            sb.append(teamFormation.getMidFielders()).append("-");
            sb.append(teamFormation.getStrikers()).append('\n');
        }
        sb.append(ColorUtils.RESET);
        return sb.toString();
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }
}
