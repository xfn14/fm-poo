package objects.game;

import objects.player.Player;
import objects.team.Team;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    /**
     * Map every Player's number to their respective object
     */
    private Map<Integer, Player> playerMap;

    /**
     * Map every Team's name to their respective object
     */
    private Map<String, Team> teamMap;

    /**
     * List all simulated games
     */
    private List<GameSim> gameList;

    /**
     * Instantiates a GameManager
     */
    public GameManager(){
        this.playerMap = new HashMap<>();
        this.teamMap = new HashMap<>();
        this.gameList = new ArrayList<>();
    }

    /**
     * Instantiates a GameManager with the respective attributes
     * @param playerMap Map Player's number to the object
     * @param teamMap Map Team's name to the object
     * @param gameList List simulated games
     */
    public GameManager(Map<Integer, Player> playerMap, Map<String, Team> teamMap, List<GameSim> gameList){
        setPlayerMap(playerMap);
        setTeamMap(teamMap);
        setGameList(gameList);
    }

    /**
     * Instantiates a GameManager from a GameManager's object
     * @param gameManager GameManager's object
     */
    public GameManager(GameManager gameManager){
        this.playerMap = gameManager.getPlayerMap();
        this.teamMap = gameManager.getTeamMap();
        this.gameList = gameManager.getGameList();
    }

    /**
     * Update current status of the game to each game simulation in gameList's list
     */
    public void updateTeamVictoriesHistory(){
        for(GameSim gameSim : this.gameList){
            Team team1 = this.teamMap.get(gameSim.getHomeTeam().getName()),
                 team2 = this.teamMap.get(gameSim.getAwayTeam().getName());

            gameSim.getHomeTeam().setPassedGames(team1.getPassedGames());
            gameSim.getHomeTeam().setTeamVictories(team1.getTeamVictories());

            gameSim.getAwayTeam().setPassedGames(team2.getPassedGames());
            gameSim.getAwayTeam().setTeamVictories(team2.getTeamVictories());

            if(gameSim.getGameState() == GameState.END_GAME){
                team1.addPassedGame(gameSim.getId());
                team2.addPassedGame(gameSim.getId());
                int winner = gameSim.getWinner();
                if (winner == 1) team1.incrementTeamVictories();
                else if (winner == 2) team2.incrementTeamVictories();
            }
        }
    }

    /**
     * Add a Player to the Map
     * @param player Player's object
     */
    public void addPlayer(Player player){
        this.playerMap.put(player.getId(), player.clone());
    }

    /**
     * Add a Team to the Map
     * @param team Team's object
     */
    public void addTeam(Team team){
        this.teamMap.put(team.getName(), team.clone());
    }

    /**
     * Get Map of Players
     * @return Map of Players
     */
    public Map<Integer, Player> getPlayerMap(){
        Map<Integer, Player> newPlayerMap = new HashMap<>();
        for(Player player : this.playerMap.values())
            newPlayerMap.put(player.getId(), player.clone());
        return newPlayerMap;
    }

    /**
     * Set Map of Players
     * @param playerMap Map of Players
     */
    public void setPlayerMap(Map<Integer, Player> playerMap){
        Map<Integer, Player> newPlayerMap = new HashMap<>();
        for(Player player : playerMap.values())
            newPlayerMap.put(player.getId(), player.clone());
        this.playerMap = newPlayerMap;
    }

    /**
     * Get Map of Teams
     * @return Map of Teams
     */
    public Map<String, Team> getTeamMap() {
        Map<String, Team> newTeamMap = new HashMap<>();
        for(Team team : this.teamMap.values())
            newTeamMap.put(team.getName(), team.clone());
        return newTeamMap;
    }

    /**
     * Set Map of Teams
     * @param teamMap Map of Teams
     */
    public void setTeamMap(Map<String, Team> teamMap) {
        Map<String, Team> newTeamMap = new HashMap<>();
        for(Team team : teamMap.values())
            newTeamMap.put(team.getName(), team.clone());
        this.teamMap = newTeamMap;
    }

    /**
     * Get Game Simulations' list
     * @return
     */
    public List<GameSim> getGameList() {
        return this.gameList.stream()
                .map(GameSim::clone)
                .collect(Collectors.toList());
    }

    /**
     * Set Game Simulations' list
     * @param gameList
     */
    public void setGameList(List<GameSim> gameList) {
        this.gameList = gameList.stream()
                .map(GameSim::clone)
                .collect(Collectors.toList());
    }

    /**
     * String representation of GameManager's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameManager{");
        sb.append("playerMap=").append(playerMap);
        sb.append(", teamMap=").append(teamMap);
        sb.append(", gameList=").append(gameList);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Equality between GameManager's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameManager that = (GameManager) o;
        return Objects.equals(gameList, that.gameList);
    }

    /**
     * Clone GameManager's instance
     * @return GameManager's cloned instance
     */
    public GameManager clone(){
        return new GameManager(this);
    }
}
