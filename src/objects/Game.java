package objects;

import objects.player.Player;
import utils.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private Tuple<Integer,Integer> goals;
    private List<Player> inFieldHome;
    private List<Tuple<Integer,Integer>> homeSubs;
    private List<Player> inFieldAway;
    private List<Tuple<Integer,Integer>> awaySubs;

    public Game(){
        this.id = -1;
        this.homeTeam = new Team();
        this.awayTeam = new Team();
        this.goals = new Tuple<>(0, 0);
        this.inFieldHome = new ArrayList<>();
        this.inFieldAway = new ArrayList<>();
    }

    public Game(int id, Team homeTeam, Team awayTeam, State gameState,
                int time, Tuple<Integer, Integer> goals,
                List<Player> inFieldHome, List<Player> inFieldAway) {
        this.id = id;
        this.homeTeam = homeTeam.clone();
        this.awayTeam = awayTeam.clone();
        this.goals = goals.clone();
        setInFieldHome(inFieldHome);
        setInFieldAway(inFieldAway);
    }

    public Game(Game game){
        this.id = game.getId();
        this.homeTeam = game.getHomeTeam();
        this.awayTeam = game.getAwayTeam();
        this.goals = game.getGoals();
        this.inFieldHome = game.getInFieldHome();
        this.inFieldAway = game.getInFieldAway();
    }

    public enum State{
        INIT,
        FIRST_HALF,
        HALF_TIME,
        SECOND_HALF,
        ENDED
    }

    public boolean addPlayerToField(int team, int playerNumber){
        if((team == 0 ? this.inFieldHome : this.inFieldAway).size() >= 11)
            return false;

        Team wantedTeam = team == 0 ? this.homeTeam : this.awayTeam;
        Player wantedPlayer = null;

        for(Player player : wantedTeam.getTeamPlayers())
            if(player.getNumber() == playerNumber)
                wantedPlayer = player;

        if(wantedPlayer != null){
            (team == 0 ? this.inFieldHome : this.inFieldAway).add(wantedPlayer.clone());
            return true;
        }
        return false;
    }

    public boolean subPlayers(int team, int leave, int stay){
        if(leave == stay) return false;

        Team wantedTeam = team == 0 ? this.homeTeam : this.awayTeam;
        List<Player> wantedFieldPlayers = (team == 0) ? this.inFieldHome : this.inFieldAway;
        Player leavePlayer = null, stayPlayer = null;

        for(Player player : wantedFieldPlayers)
            if(player.getNumber() == leave)
                leavePlayer = player;

        for(Player player : wantedTeam.getTeamPlayers())
            if(player.getNumber() == stay)
                stayPlayer = player;

        if(stayPlayer != null && leavePlayer != null){
            wantedFieldPlayers.remove(leavePlayer);
            wantedFieldPlayers.add(stayPlayer.clone());
            return true;
        }
        return false;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getHomeTeam() {
        return this.homeTeam.clone();
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam.clone();
    }

    public Team getAwayTeam() {
        return this.awayTeam.clone();
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam.clone();
    }

    public Tuple<Integer, Integer> getGoals() {
        return this.goals.clone();
    }

    public void setGoals(Tuple<Integer, Integer> goals) {
        this.goals = goals.clone();
    }

    public List<Player> getInFieldHome() {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : this.inFieldHome)
            newArr.add(crt.clone());
        return newArr;
    }

    public void setInFieldHome(List<Player> inFieldHome) {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : inFieldHome)
            newArr.add(crt.clone());
        this.inFieldHome = newArr;
    }

    public List<Tuple<Integer, Integer>> getHomeSubs() {
        List<Tuple<Integer,Integer>> newHomeSubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : this.homeSubs)
            newHomeSubs.add(tuple.clone());
        return newHomeSubs;
    }

    public void setHomeSubs(List<Tuple<Integer, Integer>> homeSubs) {
        List<Tuple<Integer,Integer>> newHomeSubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : homeSubs)
            newHomeSubs.add(tuple.clone());
        this.homeSubs = newHomeSubs;
    }

    public List<Player> getInFieldAway() {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : this.inFieldAway)
            newArr.add(crt.clone());
        return newArr;
    }

    public void setInFieldAway(List<Player> inFieldAway) {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : inFieldAway)
            newArr.add(crt.clone());
        this.inFieldAway = newArr;
    }

    public List<Tuple<Integer, Integer>> setAwaySubs() {
        List<Tuple<Integer,Integer>> newAwaySubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : this.awaySubs)
            newAwaySubs.add(tuple.clone());
        return newAwaySubs;
    }

    public void setAwaySubs(List<Tuple<Integer, Integer>> awaySubs) {
        List<Tuple<Integer,Integer>> newAwaySubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : awaySubs)
            newAwaySubs.add(tuple.clone());
        this.homeSubs = newAwaySubs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("id=").append(id);
        sb.append(", homeTeam=").append(homeTeam);
        sb.append(", awayTeam=").append(awayTeam);
        sb.append(", goals=").append(goals);
        sb.append(", inFieldHome=").append(inFieldHome);
        sb.append(", inFieldAway=").append(inFieldAway);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (!Objects.equals(homeTeam, game.homeTeam)) return false;
        if (!Objects.equals(awayTeam, game.awayTeam)) return false;
        if (!Objects.equals(goals, game.goals)) return false;
        if (!Objects.equals(inFieldHome, game.inFieldHome)) return false;
        return Objects.equals(inFieldAway, game.inFieldAway);
    }

    @Override
    public Game clone(){
        return new Game(this);
    }
}
