package objects;

import objects.player.Player;
import utils.DateUtils;
import utils.Tuple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private Tuple<Integer,Integer> goals;
    private LocalDate date;
    private List<Player> inFieldHome;
    private List<Tuple<Integer,Integer>> homeSubs;
    private List<Player> inFieldAway;
    private List<Tuple<Integer,Integer>> awaySubs;

    public Game(){
        this.id = -1;
        this.homeTeam = new Team();
        this.awayTeam = new Team();
        this.goals = new Tuple<>(0, 0);
        this.date = LocalDate.MIN;
        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
    }

    public Game(int id, Team homeTeam, Team awayTeam){
        this.id = id;
        this.homeTeam = homeTeam.clone();
        this.awayTeam = awayTeam.clone();
        this.goals = new Tuple<>(0, 0);
        this.date = LocalDate.MIN;
        this.inFieldHome = new ArrayList<>();
        this.homeSubs = new ArrayList<>();
        this.inFieldAway = new ArrayList<>();
        this.awaySubs = new ArrayList<>();
    }

    public Game(int id, Team homeTeam, Team awayTeam, Tuple<Integer, Integer> goals,
                LocalDate date, List<Player> inFieldHome, List<Player> inFieldAway,
                List<Tuple<Integer,Integer>> homeSubs, List<Tuple<Integer,Integer>> awaySubs) {
        this.id = id;
        this.homeTeam = homeTeam.clone();
        this.awayTeam = awayTeam.clone();
        this.goals = goals.clone();
        this.date = LocalDate.of(date.getYear(), date.getMonth(), date.getDayOfMonth());
        setInFieldHome(inFieldHome);
        setHomeSubs(homeSubs);
        setInFieldAway(inFieldAway);
        setAwaySubs(awaySubs);
    }

    public Game(Game game){
        this.id = game.getId();
        this.homeTeam = game.getHomeTeam();
        this.awayTeam = game.getAwayTeam();
        this.goals = game.getGoals();
        this.date = game.getDate();
        this.inFieldHome = game.getInFieldHome();
        this.homeSubs = game.getHomeSubs();
        this.inFieldAway = game.getInFieldAway();
        this.awaySubs = game.getAwaySubs();
    }

    public static Game parser(String[] args, int gameId, Map<String, Team> teamMap) throws NumberFormatException{
        Team team1 = teamMap.get(args[0]), team2 = teamMap.get(args[1]);
        if(team1 == null || team2 == null) return null;

        Game game = new Game(gameId, team1, team2);
        game.setGoals(new Tuple<>(
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3])
        ));
        game.setDate(LocalDate.parse(args[4], DateUtils.dateFormatter));

        int switchTeam = 0;
        for(int i = 5; i < args.length; i++){
            if(args[i].contains("->")){
                switchTeam = 1;
                game.subPlayers(
                        i > 20 ? 1 : 0,
                        Integer.parseInt(args[i].split("->")[0]),
                        Integer.parseInt(args[i].split("->")[1])
                );
            }else
                game.addPlayerToField(
                        switchTeam,
                        Integer.parseInt(args[i])
                );
        }
        return game;
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
        Player leavePlayer = null, stayPlayer = null;

        for(Player player : (team == 0) ? this.inFieldHome : this.inFieldAway)
            if(player.getNumber() == leave)
                leavePlayer = player;

        for(Player player : wantedTeam.getTeamPlayers())
            if(player.getNumber() == stay)
                stayPlayer = player;

        if(stayPlayer != null && leavePlayer != null){
            ((team == 0) ? this.inFieldHome : this.inFieldAway).remove(leavePlayer);
            ((team == 0) ? this.inFieldHome : this.inFieldAway).add(stayPlayer.clone());
            ((team == 0) ? this.homeSubs : this.awaySubs).add(new Tuple<>(leave, stay));
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

    public LocalDate getDate() {
        return LocalDate.of(
                this.date.getYear(),
                this.date.getMonth(),
                this.date.getDayOfMonth()
        );
    }

    public void setDate(LocalDate date) {
        this.date = LocalDate.of(
                date.getYear(),
                date.getMonth(),
                date.getDayOfMonth()
        );
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

    public List<Tuple<Integer, Integer>> getAwaySubs() {
        List<Tuple<Integer,Integer>> newAwaySubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : this.awaySubs)
            newAwaySubs.add(tuple.clone());
        return newAwaySubs;
    }

    public void setAwaySubs(List<Tuple<Integer, Integer>> awaySubs) {
        List<Tuple<Integer,Integer>> newAwaySubs = new ArrayList<>();
        for(Tuple<Integer,Integer> tuple : awaySubs)
            newAwaySubs.add(tuple.clone());
        this.awaySubs = newAwaySubs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Game{");
        sb.append("id=").append(id);
        sb.append(", homeTeam=").append(homeTeam.getName());
        sb.append(", awayTeam=").append(awayTeam.getName());
        sb.append(", goals=").append(goals);
        sb.append(", date=").append(date.toString());
        sb.append(", inFieldHome=").append(inFieldHome);
        sb.append(", homeSubs=").append(homeSubs);
        sb.append(", inFieldAway=").append(inFieldAway);
        sb.append(", awaySubs=").append(awaySubs);
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
