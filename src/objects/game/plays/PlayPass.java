package objects.game.plays;

import objects.game.GameConstants;
import objects.player.MidFielder;
import objects.player.Player;

import java.util.Objects;
import java.util.Random;

public class PlayPass extends GamePlay{
    /**
     * Player who is about to receive the ball
     */
    private Player receiver;

    /**
     * Possible Player who will cut the pass
     */
    private Player interceptor = null;

    /**
     * Instantiate a PlayPass
     */
    public PlayPass() {
        super();
        this.receiver = new Player();
        this.interceptor = new Player();
    }

    /**
     * Instantiate a PlayPass with the respective attributes
     * @param player Player who does the play
     * @param gameTime Time at which the play occurs
     * @param team Team where the player belongs to
     * @param receiver Player who is about to receive the ball
     * @param interceptor Possible Player who will cut the pass
     */
    public PlayPass(Player player, int gameTime, int team, Player receiver, Player interceptor) {
        super(player, gameTime, team);
        this.receiver = receiver.clone();
        if(interceptor != null)
            this.interceptor = interceptor.clone();
    }

    /**
     * Instantiate a PlayPass from a PlayPass
     * @param playPass PlayPass's object
     */
    public PlayPass(PlayPass playPass) {
        super(playPass);
        this.receiver = playPass.getReceiver();
        this.interceptor = playPass.getInterceptor();
    }

    /**
     * Give the play's result based on probabilities
     * @return Play has succeeded
     */
    @Override
    public boolean result() {
        Random random = new Random();
        int gameTimeDiff = (GameConstants.GAME_TIME_MAX_DIFF * getGameTime()) / GameConstants.GAME_HALF_TIME*2;
        int r = random.nextInt(Math.max(GameConstants.PASS_DIFF + gameTimeDiff, GameConstants.PASS_DIFF));
        if(interceptor == null) return true;
        if(getPlayer().getPass() < r) return false;
        if(interceptor instanceof MidFielder){
            MidFielder midFielder = (MidFielder) interceptor;
            r = random.nextInt(Math.max(GameConstants.RECOVER_DIFF + gameTimeDiff, GameConstants.RECOVER_DIFF));
            return midFielder.getRecover() >= r;
        }
        return true;
    }

    /**
     * Get Player who is about to receive the ball
     * @return Player who is about to receive the ball
     */
    public Player getReceiver() {
        return this.receiver.clone();
    }

    /**
     * Instantiate the first PlayPass by the respective attributes and the respective half of the game
     * @param team Team which the Player belongs to
     * @param half half is 0 if beginning or 1 if second part
     * @param player Player's object
     * @param receiver Player's receiver
     * @return PlayPass's object
     */
    public static PlayPass initialGamePass(int team, int half, Player player, Player receiver){
        return new PlayPass(
                player,
                half == 1 ? 0 : GameConstants.GAME_HALF_TIME,
                team,
                receiver,
                null);

    }

    /**
     * Set Player who is about to receive the ball
     * @param receiver Player who is about to receive the ball
     */
    public void setReceiver(Player receiver) {
        this.receiver = receiver.clone();
    }

    /**
     * Get Player who will try to cut the pass
     * @return Player who will try to cut the pass
     */
    public Player getInterceptor() {
        return this.interceptor.clone();
    }

    /**
     * Set Player who will try to cut the pass
     * @param interceptor Player who will try to cut the pass
     */
    public void setInterceptor(Player interceptor) {
        this.interceptor = interceptor.clone();
    }

    /**
     * Clone PlayPass's instance
     * @return PlayPass's cloned instance
     */
    @Override
    public PlayPass clone() {
        return new PlayPass(this);
    }

    /**
     * String representation of PlayPass's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayPass{");
        sb.append("receiver=").append(receiver);
        sb.append(", interceptor=").append(interceptor);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Equality between PlayPass's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayPass playPass = (PlayPass) o;
        return Objects.equals(receiver, playPass.receiver) && Objects.equals(interceptor, playPass.interceptor);
    }
}
