package menus;

import objects.game.GameConstants;
import objects.game.GameManager;
import objects.game.GameSim;
import objects.game.GameState;
import objects.team.Team;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.Triplet;
import utils.Tuple;

import java.util.*;

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
        GameSim gameSim = new GameSim(this.gameManager.getGameList().size());
        Triplet<Team, TeamFormation, List<Tuple<Integer,Integer>>> team1, team2;

        System.out.println(ColorUtils.BLUE + "FIRST TEAM SELECTION" + ColorUtils.RESET);
        team1 = selectTeamInfo();
        gameSim.setHomeTeam(team1.getX());
        gameSim.setHomeFormation(team1.getY());
        gameSim.setInFieldHome(gameSim.initInFieldTeam(1));

        System.out.println(ColorUtils.BLUE + "SECOND TEAM SELECTION" + ColorUtils.RESET);
        team2 = selectTeamInfo();
        gameSim.setAwayTeam(team2.getX());
        gameSim.setAwayFormation(team2.getY());
        gameSim.setInFieldAway(gameSim.initInFieldTeam(2));

        System.out.println(gameSim);

//        gameSim.setHomeSubs(team1.getZ());
//        gameSim.setAwaySubs(team2.getZ());

//        while(gameSim.getGameState() != GameState.END_GAME){
//            Random random = new Random();
//            int time = random.nextInt(GameConstants.MAX_SIM_TIME - GameConstants.MIN_SIM_TIME) - GameConstants.MIN_SIM_TIME;
//            String simRes = gameSim.simulateGame(time);
//            System.out.println(simRes);
//        }
    }

    private Triplet<Team, TeamFormation, List<Tuple<Integer,Integer>>> selectTeamInfo() {
        Team team = null;
        TeamFormation teamFormation = null;
        List<Tuple<Integer,Integer>> subs = new ArrayList<>();

        System.out.println(ColorUtils.GREEN + "Insert team name: " + ColorUtils.RESET);
        while(team == null){
            String teamName = scanner.nextLine();
            if(!teamName.isBlank() && this.gameManager.getTeamMap().containsKey(teamName)) {
                team = this.gameManager.getTeamMap().get(teamName).clone();
            }
            else System.out.println(ColorUtils.RED + "Invalid team name" + ColorUtils.RESET);
        }

        System.out.println(teamFormationsString());
        System.out.println(ColorUtils.GREEN + "Choose team formation: " + ColorUtils.RESET);
        while(teamFormation == null){
            try{
                int wantedFormation = scanner.nextInt();
                if (0 <= wantedFormation && wantedFormation < TeamFormation.allFormations.size()) {
                    teamFormation = TeamFormation.allFormations.get(wantedFormation);
                }
            }catch (InputMismatchException e){
                System.out.println(ColorUtils.RED + "Invalid team formation." + ColorUtils.RESET);
                scanner.next();
            }
        }

//        System.out.println(ColorUtils.YELLOW + "(Substitutions will be done at half-time)" + ColorUtils.RESET);
//        System.out.println(ColorUtils.GREEN + "Insert team substitutions " + ColorUtils.BLUE + "<leave>" + ColorUtils.YELLOW + "->" + ColorUtils.BLUE + "<enter>" + ColorUtils.YELLOW + "(3 required)" + ColorUtils.RESET);
//        while(subs.size() < 3){
//            String input = scanner.nextLine();
//            String[] players = input.split("->", 2);
//            if(players[0] != null && players[1] != null
//            && !players[0].isBlank() && !players[1].isBlank()){
//                try{
//                    int playerLeave = Integer.parseInt(players[0]);
//                    int playerStay = Integer.parseInt(players[1]);
//                    if(team.getTeamPlayers().stream().anyMatch(player -> playerLeave == player.getNumber()))
//                    subs.add(new Tuple<>(playerLeave, playerStay));
//                }catch (NumberFormatException e){
//                    System.out.println(ColorUtils.RED + "Invalid player substitution." + ColorUtils.RESET);
//                    scanner.next();
//                }
//            }
//        }
        return new Triplet<>(team, teamFormation, subs);
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
