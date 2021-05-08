package objects;

import objects.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void addPlayer(Player player){
        Player newPlayer = player.clone();
        newPlayer.setCurrentTeam(this.name);
        this.teamPlayers.add(player.clone());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getTeamPlayers() {
        List<Player> newList = new ArrayList<>();
        for(Player crt : this.teamPlayers)
            newList.add(crt.clone());
        return newList;
    }

    public void setTeamPlayers(List<Player> teamPlayers) {
        List<Player> newList = new ArrayList<>();
        for(Player crt : teamPlayers)
            newList.add(crt.clone());
        this.teamPlayers = newList;
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
