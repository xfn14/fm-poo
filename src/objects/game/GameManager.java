package objects.game;

import objects.team.Team;

import java.util.*;
import java.util.stream.Collectors;

public class GameManager {
    private Map<String, Team> teamMap;
    private List<GameSim> gameList;

    public GameManager(){
        this.teamMap = new HashMap<>();
        this.gameList = new ArrayList<>();
    }

    public GameManager(Map<String, Team> teamMap, List<GameSim> gameList){
        setTeamMap(teamMap);
        setGameList(gameList);
    }

    public GameManager(GameManager gameManager){
        this.teamMap = gameManager.getTeamMap();
        this.gameList = gameManager.getGameList();
    }

    public int createNewGame(Team team1, Team team2){
        GameSim newGameSim = new GameSim(
                this.gameList.size(),
                team1.clone(),
                team2.clone()
        );
        this.gameList.add(newGameSim);
        return newGameSim.getId();
    }

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
                (gameSim.getWinner() == 0 ? team1 : team2).incrementTeamVictories();
            }
        }
    }

    public Map<String, Team> getTeamMap() {
        Map<String, Team> newTeamMap = new HashMap<>();
        for(Team team : this.teamMap.values())
            newTeamMap.put(team.getName(), team.clone());
        return newTeamMap;
    }

    public void setTeamMap(Map<String, Team> teamMap) {
        Map<String, Team> newTeamMap = new HashMap<>();
        for(Team team : teamMap.values())
            newTeamMap.put(team.getName(), team.clone());
        this.teamMap = newTeamMap;
    }

    public List<GameSim> getGameList() {
        return this.gameList.stream()
                .map(GameSim::clone)
                .collect(Collectors.toList());
    }

    public void setGameList(List<GameSim> gameList) {
        this.gameList = gameList.stream()
                .map(GameSim::clone)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameManager{");
        sb.append("teamMap=").append(teamMap);
        sb.append(", gameList=").append(gameList);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameManager that = (GameManager) o;
        return Objects.equals(gameList, that.gameList);
    }

    public GameManager clone(){
        return new GameManager(this);
    }
}
