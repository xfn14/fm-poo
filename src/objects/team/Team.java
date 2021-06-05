package objects.team;

import objects.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Team {
    private String name;
    private List<Player> teamPlayers;
    private int teamVictories;
    private List<Integer> passedGames; // List of game's ids played

    public Team(){
        this.name = "";
        this.teamPlayers = new ArrayList<>();
        this.teamVictories = 0;
        this.passedGames = new ArrayList<>();
    }

    public Team(String name, List<Player> teamPlayers, int teamVictories, List<Integer> passedGames) {
        this.name = name;
        setTeamPlayers(teamPlayers);
        this.teamVictories = teamVictories;
        setPassedGames(passedGames);
    }

    public Team(Team team){
        this.name = team.getName();
        this.teamPlayers = team.getTeamPlayers();
        this.teamVictories = team.getTeamVictories();
        this.passedGames = team.getPassedGames();
    }

    public static Team parse(String input){
        return new Team(
                input,
                new ArrayList<>(),
                0,
                new ArrayList<>()
        );
    }

    // TODO: 6/5/2021 Check player shirt number
    // returns players because players can be changed during the progress
    public Player addPlayer(Player player){
        Player newPlayer = player.clone();
        newPlayer.setCurrentTeam(this.name);
        this.teamPlayers.add(newPlayer.clone());
        return newPlayer;
    }

    public int calcOverall(){
        return this.teamPlayers.stream()
                .mapToInt(Player::calcAbility)
                .sum() / this.teamPlayers.size();
    }

    public void removePlayer(Player player){
        this.teamPlayers.remove(player);
    }

    public void incrementTeamVictories(){
        this.teamVictories++;
    }

    public void addPassedGame(int gameId){
        this.passedGames.add(gameId);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getTeamPlayers() {
        return this.teamPlayers.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    public int getTeamVictories() {
        return this.teamVictories;
    }

    public void setTeamVictories(int teamVictories) {
        this.teamVictories = teamVictories;
    }

    public List<Integer> getPassedGames() {
        return new ArrayList<>(this.passedGames);
    }

    public void setPassedGames(List<Integer> passedGames) {
        this.passedGames = new ArrayList<>(passedGames);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Team{");
        sb.append("name='").append(name).append('\'');
        sb.append(", teamPlayers=").append(teamPlayers);
        sb.append(", teamVictories=").append(teamVictories);
        sb.append(", passedGames=").append(passedGames);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public Team clone() {
        return new Team(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (teamVictories != team.teamVictories) return false;
        if (!Objects.equals(name, team.name)) return false;
        if (!Objects.equals(teamPlayers, team.teamPlayers)) return false;
        return Objects.equals(passedGames, team.passedGames);
    }
}
