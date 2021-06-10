package menus;

import objects.game.GameConstants;
import objects.game.GameManager;
import objects.game.GameSim;
import objects.game.GameState;
import objects.player.Player;
import objects.team.Team;
import objects.team.TeamFormation;
import utils.ColorUtils;
import utils.Tuple;

import java.util.*;
import java.util.stream.Collectors;

public class NewGameMenu {
    private final Random random = new Random();
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
        Tuple<Team, TeamFormation> team1, team2;

        System.out.println(ColorUtils.BLUE + "FIRST TEAM SELECTION" + ColorUtils.RESET);
        team1 = selectTeamInfo();
        gameSim.setHomeTeam(team1.getX());
        gameSim.setHomeFormation(team1.getY());
        gameSim.setInFieldHome(gameSim.initInFieldTeam(1));
        List<Player> team1Subs = gameSim.getHomeTeam().getTeamPlayers();
        team1Subs.removeAll(gameSim.getInFieldHome());
        gameSim.setHomeSubs(selectTeamSubs(gameSim.getInFieldHome(), team1Subs));

        System.out.println(ColorUtils.BLUE + "SECOND TEAM SELECTION" + ColorUtils.RESET);
        team2 = selectTeamInfo();
        gameSim.setAwayTeam(team2.getX());
        gameSim.setAwayFormation(team2.getY());
        gameSim.setInFieldAway(gameSim.initInFieldTeam(2));
        List<Player> team2Subs = gameSim.getAwayTeam().getTeamPlayers();
        team2Subs.removeAll(gameSim.getInFieldAway());
        gameSim.setAwaySubs(selectTeamSubs(gameSim.getInFieldAway(), team2Subs));

        System.out.println(gameSim);

//        GameSim gameSim = new GameSim(this.gameManager.getGameList().size(), this.gameManager.getTeamMap().get("Sporting Club Schubert"), this.gameManager.getTeamMap().get("Mendelssohn F. C."));
//        gameSim.setInFieldHome(gameSim.initInFieldTeam(0));
//        gameSim.setInFieldAway(gameSim.initInFieldTeam(1));
//
//
//        gameSim.setGameState(GameState.FST_HALF);
//        while(gameSim.getGameState() != GameState.END_GAME){
//            // this time in in minutes
//            int time = this.random.nextInt(GameConstants.MAX_SIM_TIME - GameConstants.MIN_SIM_TIME) - GameConstants.MIN_SIM_TIME;
//            String simRes = gameSim.simulateGame(time);
//            System.out.println(simRes);
//        }
    }

    private Tuple<Team, TeamFormation> selectTeamInfo() {
        Team team = null;
        TeamFormation teamFormation = null;

        System.out.println(ColorUtils.GREEN + "Insert team name: " + ColorUtils.RESET);
        while(team == null){
            String teamName = scanner.nextLine();
            if(!teamName.isBlank() && this.gameManager.getTeamMap().containsKey(teamName)){
                Team temp = this.gameManager.getTeamMap().get(teamName).clone();
                if(temp.getTeamPlayers().size() >= GameConstants.MIN_GAME_PLAYER)
                    team = temp;
            } else System.out.println(ColorUtils.RED + "Invalid team name" + ColorUtils.RESET);
        }

        System.out.println(teamFormationsString());
        System.out.println(ColorUtils.GREEN + "Choose team formation: " + ColorUtils.RESET);
        while(teamFormation == null){
            try{
                int wantedFormation = Integer.parseInt(scanner.nextLine());
                if (0 <= wantedFormation && wantedFormation < TeamFormation.allFormations.size())
                    teamFormation = TeamFormation.allFormations.get(wantedFormation);
            }catch (NumberFormatException e){
                System.out.println(ColorUtils.RED + "Invalid team formation." + ColorUtils.RESET);
                scanner.next();
            }
        }
        return new Tuple<>(team, teamFormation);
    }

    private List<Tuple<Integer,Integer>> selectTeamSubs(List<Player> inField, List<Player> teamSubs){
        List<Tuple<Integer,Integer>> subs = new ArrayList<>();
        List<Player> inFieldTemp = new ArrayList<>(inField);
        List<Player> teamSubsTemp = new ArrayList<>(teamSubs);
        System.out.println(ColorUtils.GREEN + "Starting in-field players: " + ColorUtils.YELLOW + inFieldTemp.stream().map(Player::getNumber).collect(Collectors.toList()) + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "Starting bench players: " + ColorUtils.YELLOW + teamSubsTemp.stream().map(Player::getNumber).collect(Collectors.toList()) + ColorUtils.RESET);
        System.out.println(ColorUtils.YELLOW + "(Substitutions will be done at half-time)" + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "Insert team substitutions " + ColorUtils.BLUE + "<leave>" + ColorUtils.YELLOW + "->" + ColorUtils.BLUE + "<enter>" + ColorUtils.YELLOW + "(3 required)" + ColorUtils.RESET);
        while(subs.size() < GameConstants.MAX_SUB_NUMBER){
            String input = scanner.nextLine();
            String[] players = input.split("->", 2);
            if(players.length == 2 && players[0] != null && players[1] != null
            && !players[0].isBlank() && !players[1].isBlank()){
                try{
                    int playerLeave = Integer.parseInt(players[0]);
                    int playerStay = Integer.parseInt(players[1]);
                    Player leave = inFieldTemp.stream()
                            .filter(player -> playerLeave == player.getNumber())
                            .findAny()
                            .orElse(null);
                    Player stay = teamSubsTemp.stream()
                            .filter(player -> playerStay == player.getNumber())
                            .findAny()
                            .orElse(null);
                    if(leave != null && stay != null){
                        inFieldTemp.set(inFieldTemp.indexOf(leave), stay);
                        teamSubsTemp.add(leave);
                        subs.add(new Tuple<>(playerLeave, playerStay));
                    }else{
                        System.out.println(ColorUtils.RED + "Invalid player substitution." + ColorUtils.RESET);
                    }
                }catch (NumberFormatException e){
                    System.out.println(ColorUtils.RED + "Invalid player substitution." + ColorUtils.RESET);
                    scanner.next();
                }
            }
        }
        return subs;
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
