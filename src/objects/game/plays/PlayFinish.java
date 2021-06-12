package objects.game.plays;

import objects.game.GameConstants;
import objects.player.Keeper;
import objects.player.Player;

import java.util.Objects;
import java.util.Random;

public class PlayFinish extends GamePlay{
    /**
     * Keeper who will face the play
     */
    private Player keeper;

    /**
     * Instantiate a PlayFinish
     */
    public PlayFinish() {
        super();
        this.keeper = new Player();
    }

    /**
     * Instantiate a PlayFinish with the respective attributes
     * @param player Player who does the play
     * @param gameTime Time at which the play occurs
     * @param team Team where the player belongs to
     * @param keeper Keeper who will face the play
     */
    public PlayFinish(Player player, int gameTime, int team, Player keeper) {
        super(player, gameTime, team);
        this.keeper = keeper.clone();
    }

    /**
     * * Instantiate a PlayFinish from a PlayFinish
     * @param playFinish PlayFinish's object
     */
    public PlayFinish(PlayFinish playFinish) {
        super(playFinish);
        this.keeper = playFinish.getKeeper();
    }

    /**
     * Give the play's result based on probabilities
     * @return Play has succeeded
     */
    @Override
    public boolean result() {
        Random random = new Random();
        int gameTimeDiff = (GameConstants.GAME_TIME_MAX_DIFF * getGameTime()) / GameConstants.GAME_HALF_TIME*2;
        int r = random.nextInt(Math.max(GameConstants.FINISH_DIFF + gameTimeDiff, GameConstants.FINISH_DIFF));
        if(getPlayer().getFinish() < r) return false;
        if(keeper instanceof Keeper){
            Keeper k = (Keeper) keeper;
            r = random.nextInt(Math.max(GameConstants.SAVE_DIFF + gameTimeDiff, GameConstants.SAVE_DIFF));
            return k.getElasticity() >= r;
        }
        return true;
    }

    /**
     * Get Keeper who is about to face the play
     * @return Keeper's object
     */
    public Player getKeeper() {
        return this.keeper.clone();
    }

    /**
     * Set Keeper who is about to face the play
     * @param keeper Keeper who is about to face the play
     */
    public void setKeeper(Player keeper) {
        this.keeper = keeper.clone();
    }

    /**
     * Clone PlayFinish's instance
     * @return PlayFinish's cloned instance
     */
    @Override
    public PlayFinish clone() {
        return new PlayFinish(this);
    }

    /**
     * String representation of PlayFinish's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayFinish{");
        sb.append("keeper=").append(keeper);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Equality between PlayFinish's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayFinish that = (PlayFinish) o;
        return Objects.equals(keeper, that.keeper);
    }
}
