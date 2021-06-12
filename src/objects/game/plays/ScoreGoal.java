package objects.game.plays;

import objects.player.Player;
import utils.ColorUtils;
import utils.DateUtils;
import utils.Tuple;

public class ScoreGoal extends GamePlay{
    /**
     * Instantiate a ScoreGoal with the respectiv attributes
     * @param player Player who does the play
     * @param gameTime Time at which the play occurs
     * @param team Team where the player belongs to
     */
    public ScoreGoal(Player player, int gameTime, int team) {
        super(player, gameTime, team);
    }

    public ScoreGoal(ScoreGoal scoreGoal) {
        super(scoreGoal);
    }

    /**
     * Give the play's result based on probabilities
     * @return Play has succeeded
     */
    @Override
    public boolean result() {
        return true;
    }

    /**
     * Clone ScoreGoal's instance
     * @return ScoreGoal's cloned instance
     */
    @Override
    public ScoreGoal clone() {
        return new ScoreGoal(this);
    }

    /**
     * String representation of ScoreGoal's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        Tuple<Integer,Integer> time = DateUtils.secondsToTuple(getGameTime());
        return ColorUtils.BLUE + "[" + time.getX() + ":" + time.getY() + "]" + ColorUtils.GREEN + " Goal by " + getPlayer().getName() + ColorUtils.RESET;
    }
}
