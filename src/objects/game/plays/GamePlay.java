package objects.game.plays;

import objects.player.Player;

import java.util.Objects;

public abstract class GamePlay {
    private Player player;
    private int gameTime;
    private int team; // 0 - homeTeam; 1 - awayTeam

    public GamePlay(){
        this.player = new Player();
        this.gameTime = 0;
        this.team = -1;
    }

    public GamePlay(Player player, int gameTime, int team) {
        this.player = player;
        this.gameTime = gameTime;
        this.team = team;
    }

    public GamePlay(GamePlay gamePlay){
        this.player = gamePlay.getPlayer();
        this.gameTime = gamePlay.getGameTime();
        this.team = gamePlay.getTeam();
    }

    public abstract boolean result();

    public Player getPlayer() {
        return player.clone();
    }

    public void setPlayer(Player player) {
        this.player = player.clone();
    }

    public int getGameTime() {
        return this.gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public int getTeam() {
        return this.team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GamePlay{");
        sb.append("player=").append(player);
        sb.append(", gameTime=").append(gameTime);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlay gamePlay = (GamePlay) o;
        return gameTime == gamePlay.gameTime && Objects.equals(player, gamePlay.player);
    }

    public abstract GamePlay clone();

    public enum Type{
        HEADER,
        CROSS,
        JINK,
        DEFEND,
        FAULT
    }
}
