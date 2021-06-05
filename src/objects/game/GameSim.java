package objects.game;

import objects.team.Team;
import objects.player.Player;
import objects.team.TeamFormation;
import utils.DateUtils;
import utils.Tuple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameSim extends GameInfo {
    private GameState gameState;
    private int time;
    private int extraTime;
    private Tuple<Integer,Integer> goals;

    private List<Player> inFieldHome;
    private List<Tuple<Integer,Integer>> homeSubs;
    private TeamFormation homeFormation;

    private List<Player> inFieldAway;
    private List<Tuple<Integer,Integer>> awaySubs;
    private TeamFormation awayFormation;

    public GameSim(){
        super();

        this.gameState = GameState.INIT_GAME;
        this.time = 0;
        this.extraTime = 0;
        this.goals = new Tuple<>(0, 0);

        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.homeFormation = TeamFormation.getRandomFormation();

        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
        this.awayFormation = TeamFormation.getRandomFormation();
    }

    public GameSim(int id, Team homeTeam, Team awayTeam){
        super(id, homeTeam.clone(), awayTeam.clone());

        this.gameState = GameState.INIT_GAME;
        this.time = 0;
        this.extraTime = 0;
        this.goals = new Tuple<>(0, 0);

        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.homeFormation = TeamFormation.getRandomFormation();

        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
        this.awayFormation = TeamFormation.getRandomFormation();
    }

    public GameSim(int id, LocalDate date, Team homeTeam, Team awayTeam, GameState gameState,
                   int time, int extraTime, Tuple<Integer, Integer> goals, List<Player> inFieldHome,
                   List<Tuple<Integer, Integer>> homeSubs, TeamFormation homeFormation,
                   List<Player> inFieldAway, List<Tuple<Integer, Integer>> awaySubs,
                   TeamFormation awayFormation) {
        super(id, date, homeTeam, awayTeam);

        this.gameState = gameState;
        this.time = time;
        this.extraTime = extraTime;
        this.goals = goals;

        setInFieldHome(inFieldHome);
        setHomeSubs(homeSubs);
        this.homeFormation = homeFormation;

        setInFieldAway(inFieldAway);
        setAwaySubs(awaySubs);
        this.awayFormation = awayFormation;
    }

    public GameSim(GameSim gameSim){
        super(gameSim.getId(), gameSim.getDate(), gameSim.getHomeTeam(), gameSim.getAwayTeam());

        this.gameState = gameSim.getGameState();
        this.time = gameSim.getTime();
        this.extraTime = gameSim.getExtraTime();
        this.goals = gameSim.getGoals();

        this.inFieldHome = gameSim.getInFieldHome();
        this.homeSubs = gameSim.getHomeSubs();
        this.homeFormation = gameSim.getHomeFormation();

        this.inFieldAway = gameSim.getInFieldAway();
        this.awaySubs = gameSim.getAwaySubs();
        this.awayFormation = gameSim.getAwayFormation();
    }

    public void parseCustom(String[] args) throws IllegalArgumentException {
        setHomeFormation(args[1] != null ? TeamFormation.valueOf(args[1]) : null);
        setAwayFormation(args[2] != null ? TeamFormation.valueOf(args[2]) : null);

        setGameState(args[3] != null ? GameState.valueOf(args[3]) : null);

        try{
            setTime(Integer.parseInt(args[4]));
        }catch (NumberFormatException e){
            System.out.println("GameSim_Time must be a number");
        }
    }

    public static GameSim parser(String[] args, int gameId, Map<String, Team> teamMap) throws NumberFormatException{
        Team team1 = teamMap.get(args[0]), team2 = teamMap.get(args[1]);
        if(team1 == null || team2 == null) return null;

        GameSim gameSim = new GameSim(gameId, team1, team2);
        gameSim.setGoals(new Tuple<>(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        ));
        gameSim.setDate(LocalDate.parse(args[4], DateUtils.dateFormatter));

        int switchTeam = 0;
        for(int i = 5; i < args.length; i++){
            if(args[i].contains("->")){
                switchTeam = 1;
                gameSim.subPlayers(
                        i > 20 ? 1 : 0,
                        Integer.parseInt(args[i].split("->")[0]),
                        Integer.parseInt(args[i].split("->")[1])
                );
            }else {
                gameSim.addPlayerToField(
                        switchTeam,
                        Integer.parseInt(args[i])
                );
            }
        }
        return gameSim;
    }

    public int getWinner(){
        if(this.gameState == GameState.END_GAME)
            if(this.goals.getX() > this.goals.getY())
                return 1;
            else if(this.goals.getX() < this.goals.getY())
                return 2;
            else
                return 0;
        return -1;
    }

    public boolean addPlayerToField(int team, int playerNumber){
        if((team == 0 ? this.inFieldHome : this.inFieldAway).size() >= 11)
            return false;

        Player wantedPlayer = (team == 0 ? getHomeTeam() : getAwayTeam())
                .getTeamPlayers().stream()
                .filter(player -> playerNumber == player.getNumber())
                .findAny()
                .orElse(null);

        if(wantedPlayer != null){
            (team == 0 ? this.inFieldHome : this.inFieldAway).add(wantedPlayer.clone());
            return true;
        }
        return false;
    }

    public boolean subPlayers(int team, int leave, int stay){
        if(leave == stay) return false;
        if((team == 0 ? this.homeSubs : this.awaySubs).size() >= 3) return false;

        Player leavePlayer = (team == 0 ? this.inFieldHome : this.inFieldAway).stream()
                .filter(player -> leave == player.getNumber())
                .findAny()
                .orElse(null);

        if(leavePlayer == null) return false;

        Player stayPlayer = (team == 0 ? getHomeTeam() : getAwayTeam()).getTeamPlayers().stream()
                .filter(player -> stay == player.getNumber())
                .findAny()
                .orElse(null);

        if(stayPlayer != null){
            ((team == 0) ? this.inFieldHome : this.inFieldAway).remove(leavePlayer);
            ((team == 0) ? this.inFieldHome : this.inFieldAway).add(stayPlayer.clone());
            ((team == 0) ? this.homeSubs : this.awaySubs).add(new Tuple<>(leave, stay));
            return true;
        } return false;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setGameState(GameState gameState) {
        if(gameState != null)
            this.gameState = gameState;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        if(0 <= time && time <= 90 + GameConstants.MAX_EXTRA_TIME)
            this.time = time;
    }

    public int getExtraTime() {
        return this.extraTime;
    }

    public void setExtraTime(int extraTime) {
        if(extraTime <= GameConstants.MAX_EXTRA_TIME)
            this.extraTime = extraTime;
    }

    public Tuple<Integer, Integer> getGoals() {
        return this.goals.clone();
    }

    public void setGoals(Tuple<Integer, Integer> goals) {
        this.goals = goals.clone();
    }

    public List<Player> getInFieldHome() {
        return this.inFieldHome.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public void setInFieldHome(List<Player> inFieldHome) {
        this.inFieldHome = inFieldHome.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public List<Tuple<Integer, Integer>> getHomeSubs() {
        return this.homeSubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    public void setHomeSubs(List<Tuple<Integer, Integer>> homeSubs) {
        this.homeSubs = homeSubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    public TeamFormation getHomeFormation() {
        return this.homeFormation;
    }

    public void setHomeFormation(TeamFormation homeFormation) {
        if(homeFormation != null)
            this.homeFormation = homeFormation;
    }

    public List<Player> getInFieldAway() {
        return this.inFieldAway.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public void setInFieldAway(List<Player> inFieldAway) {
        this.inFieldAway = inFieldAway.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public List<Tuple<Integer, Integer>> getAwaySubs() {
        return this.awaySubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    public void setAwaySubs(List<Tuple<Integer, Integer>> awaySubs) {
        this.awaySubs = awaySubs.stream()
                .map(Tuple::clone)
                .collect(Collectors.toList());
    }

    public TeamFormation getAwayFormation() {
        return this.awayFormation;
    }

    public void setAwayFormation(TeamFormation awayFormation) {
        if(awayFormation != null)
            this.awayFormation = awayFormation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameSim{");
        sb.append("gameState=").append(gameState);
        sb.append(", time=").append(time);
        sb.append(", extraTime=").append(extraTime);
        sb.append(", goals=").append(goals);
        sb.append(", inFieldHome=").append(inFieldHome.stream().map(Player::getNumber).collect(Collectors.toList()));
        sb.append(", homeSubs=").append(homeSubs);
        sb.append(", homeFormation=").append(homeFormation);
        sb.append(", inFieldAway=").append(inFieldAway.stream().map(Player::getNumber).collect(Collectors.toList()));
        sb.append(", awaySubs=").append(awaySubs);
        sb.append(", awayFormation=").append(awayFormation);
        sb.append('}').append(super.toString()).append('\n');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameSim gameSim = (GameSim) o;
        return Objects.equals(goals, gameSim.goals) && Objects.equals(inFieldHome, gameSim.inFieldHome) && Objects.equals(homeSubs, gameSim.homeSubs) && homeFormation == gameSim.homeFormation && Objects.equals(inFieldAway, gameSim.inFieldAway) && Objects.equals(awaySubs, gameSim.awaySubs) && awayFormation == gameSim.awayFormation;
    }

    @Override
    public GameSim clone(){
        return new GameSim(this);
    }
}
