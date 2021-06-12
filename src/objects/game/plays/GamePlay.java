package objects.game.plays;

import objects.player.Player;

import java.util.Objects;

public abstract class GamePlay {
    /**
     * Player who does the play
     */
    private Player player;

    /**
     * Time in the game where the play occurs
     */
    private int gameTime;

    /**
     * Team where Player plays
     */
    private int team; // 0 - homeTeam; 1 - awayTeam

    /**
     * Instantiate a GamePlay
     */
    public GamePlay(){
        this.player = new Player();
        this.gameTime = 0;
        this.team = -1;
    }

    /**
     * Instantiate a GamePlay with the respective attributes
     * @param player Player who does the play
     * @param gameTime Time at which the play occurs
     * @param team Team where the player belongs to
     */
    public GamePlay(Player player, int gameTime, int team) {
        this.player = player;
        this.gameTime = gameTime;
        this.team = team;
    }

    /**
     * Instantiate a GamePlay from a GamePlay
     * @param gamePlay GamePlay's object
     */
    public GamePlay(GamePlay gamePlay){
        this.player = gamePlay.getPlayer();
        this.gameTime = gamePlay.getGameTime();
        this.team = gamePlay.getTeam();
    }

    /**
     * Give the play's result based on probabilities
     * @return Play has succeeded
     */
    public abstract boolean result();

    /**
     * Get Player
     * @return Player's object
     */
    public Player getPlayer() {
        return player.clone();
    }

    /**
     * Set Player
     * @param player Player's object
     */
    public void setPlayer(Player player) {
        this.player = player.clone();
    }

    /**
     * Get time at which the play occurs
     * @return Time at which the play occurs
     */
    public int getGameTime() {
        return this.gameTime;
    }

    /**
     * Set time at which the play occurs
     * @return Time at which the play occurs
     */
    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * Get team where Player belongs to
     * @return Team where Player belongs to
     */
    public int getTeam() {
        return this.team;
    }

    /**
     * Set team where Player belongs to
     * @param team Team where Player belongs to
     */
    public void setTeam(int team) {
        this.team = team;
    }

    /**
     * String representation of GamePlay's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GamePlay{");
        sb.append("player=").append(player);
        sb.append(", gameTime=").append(gameTime);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Equality between GamePlay's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamePlay gamePlay = (GamePlay) o;
        return gameTime == gamePlay.gameTime && Objects.equals(player, gamePlay.player);
    }

    /**
     * Clone GamePlay's instance
     * @return GamePlay's cloned instance
     */
    public abstract GamePlay clone();

    /**
     * Type of plays
     */
    public enum Type{
        HEADER,
        CROSS,
        JINK,
        DEFEND,
        FAULT
    }
}
