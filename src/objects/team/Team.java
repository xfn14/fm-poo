package objects.team;

import objects.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Team implements Serializable {
    /**
     * Team's name
     */
    private String name;

    /**
     * Team's list of players
     */
    private List<Player> teamPlayers;

    /**
     * Team's total victories
     */
    private int teamVictories;

    /**
     * Team's list of played games' ID
     */
    private List<Integer> passedGames; // List of game's ids played

    /**
     * Instantiates a Team
     */
    public Team(){
        this.name = "";
        this.teamPlayers = new ArrayList<>();
        this.teamVictories = 0;
        this.passedGames = new ArrayList<>();
    }

    public Team(String name){
        this.name = name;
        this.teamPlayers = new ArrayList<>();
        this.teamVictories = 0;
        this.passedGames = new ArrayList<>();
    }

    /**
     * Instantiates a Team with respective attributes
     * @param name Team's name
     * @param teamPlayers Team's list of players
     * @param teamVictories Team's total victories
     * @param passedGames Team's list of games' ID
     */
    public Team(String name, List<Player> teamPlayers, int teamVictories, List<Integer> passedGames) {
        this.name = name;
        setTeamPlayers(teamPlayers);
        this.teamVictories = teamVictories;
        setPassedGames(passedGames);
    }

    /**
     * Instantiates a Team from a Team's object
     * @param team Team's object
     */
    public Team(Team team){
        this.name = team.getName();
        this.teamPlayers = team.getTeamPlayers();
        this.teamVictories = team.getTeamVictories();
        this.passedGames = team.getPassedGames();
    }

    /**
     * Parses a team from a String
     * @param input String to be parsed
     * @return New Team's object
     */
    public static Team parse(String input){
        return new Team(
                input,
                new ArrayList<>(),
                0,
                new ArrayList<>()
        );
    }

    /**
     * Adds a Player to the Team
     * @param player Player's object
     * @return Respective Player with possible changes to integrate the team
     */
    public Player addPlayer(Player player){
        Player newPlayer = player.clone();
        newPlayer.setCurrentTeam(this.name);

        Random random = new Random();
        int number = newPlayer.getNumber();

        while(containsShirtNumber(number))
            number = random.nextInt(100);

        newPlayer.setNumber(number);

        this.teamPlayers.add(newPlayer.clone());
        return newPlayer; // because players can be changed during the progress
    }

    /**
     * Check if shirt's number exists
     * @param shirtNumber Shirt's number
     * @return boolean
     */
    private boolean containsShirtNumber(int shirtNumber){
        for(Player player : this.teamPlayers)
            if(player.getNumber() == shirtNumber)
                return true;
        return false;
    }

    /**
     * Calculate Team's overall abilities
     * @return Team's overall abilities
     */
    public int calcOverall(){
        return (int) this.teamPlayers.stream()
                .mapToInt(Player::calcAbility)
                .average()
                .orElse(0.0);
    }

    /**
     * Removes a Player from the Team
     * @param player Player's object
     */
    public void removePlayer(Player player){
        this.teamPlayers.remove(player);
    }

    /**
     * Increment number of Team's victories
     */
    public void incrementTeamVictories(){
        this.teamVictories++;
    }

    /**
     * Add Game's ID to the list of passed games
     * @param gameId Game's ID
     */
    public void addPassedGame(int gameId){
        this.passedGames.add(gameId);
    }

    /**
     * Get Team's name
     * @return Team's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set Team's name
     * @param name Team's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get Team's players
     * @return List of Team's players
     */
    public List<Player> getTeamPlayers() {
        return this.teamPlayers.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set Team's Players
     * @param teamPlayers List of Team's players
     */
    public void setTeamPlayers(List<Player> teamPlayers) {
        this.teamPlayers = teamPlayers.stream()
                .map(Player::clone)
                .collect(Collectors.toList());
    }

    /**
     * Get Team's total victories
     * @return Number of victories
     */
    public int getTeamVictories() {
        return this.teamVictories;
    }

    /**
     * Set Team's total victories
     * @param teamVictories Number of victories
     */
    public void setTeamVictories(int teamVictories) {
        this.teamVictories = teamVictories;
    }

    /**
     * Get passed Games
     * @return List of passed Games' ID
     */
    public List<Integer> getPassedGames() {
        return new ArrayList<>(this.passedGames);
    }

    /**
     * Set passed Games
     * @param passedGames List of passed Games' ID
     */
    public void setPassedGames(List<Integer> passedGames) {
        this.passedGames = new ArrayList<>(passedGames);
    }

    /**
     * String representation of Team's instance
     * @return String representation of the instance
     */
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

    /**
     * Clone Team's instance
     * @return Team's cloned instance
     */
    @Override
    public Team clone() {
        return new Team(this);
    }

    /**
     * Equality between Team's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
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
