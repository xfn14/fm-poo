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
    private State gameState;
    private int time;
    private Tuple<Integer,Integer> goals;
    private List<Player> inFieldHome;
    private List<Player> inFieldAway;

    public Game(){
        this.id = -1;
        this.homeTeam = new Team();
        this.awayTeam = new Team();
        this.gameState = State.INIT;
        this.time = 0;
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
        this.gameState = gameState;
        this.time = time;
        this.goals = goals.clone();
        setInFieldHome(inFieldHome);
        setInFieldAway(inFieldAway);
    }

    public Game(Game game){
        this.id = game.getId();
        this.homeTeam = game.getHomeTeam();
        this.awayTeam = game.getAwayTeam();
        this.gameState = game.getGameState();
        this.time = game.getTime();
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

    public State getGameState() {
        return this.gameState;
    }

    public void setGameState(State gameState) {
        this.gameState = gameState;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
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
        for(Player crt : this.inFieldHome)
            newArr.add(crt.clone());
        this.inFieldHome = newArr;
    }

    public List<Player> getInFieldAway() {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : this.inFieldAway)
            newArr.add(crt.clone());
        return newArr;
    }

    public void setInFieldAway(List<Player> inFieldAway) {
        List<Player> newArr = new ArrayList<>();
        for(Player crt : this.inFieldAway)
            newArr.add(crt.clone());
        this.inFieldAway = newArr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("id=").append(id);
        sb.append(", homeTeam=").append(homeTeam);
        sb.append(", awayTeam=").append(awayTeam);
        sb.append(", gameState=").append(gameState);
        sb.append(", time=").append(time);
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
        if (time != game.time) return false;
        if (!Objects.equals(homeTeam, game.homeTeam)) return false;
        if (!Objects.equals(awayTeam, game.awayTeam)) return false;
        if (gameState != game.gameState) return false;
        if (!Objects.equals(goals, game.goals)) return false;
        if (!Objects.equals(inFieldHome, game.inFieldHome)) return false;
        return Objects.equals(inFieldAway, game.inFieldAway);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + (gameState != null ? gameState.hashCode() : 0);
        result = 31 * result + time;
        result = 31 * result + (goals != null ? goals.hashCode() : 0);
        result = 31 * result + (inFieldHome != null ? inFieldHome.hashCode() : 0);
        result = 31 * result + (inFieldAway != null ? inFieldAway.hashCode() : 0);
        return result;
    }

    @Override
    public Game clone(){
        return new Game(this);
    }
}
