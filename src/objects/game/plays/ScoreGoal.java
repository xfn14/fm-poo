package objects.game.plays;

import objects.player.Player;
import utils.ColorUtils;
import utils.DateUtils;
import utils.Tuple;

public class ScoreGoal extends GamePlay{
    public ScoreGoal(Player player, int gameTime, int team) {
        super(player, gameTime, team);
    }

    public ScoreGoal(ScoreGoal scoreGoal) {
        super(scoreGoal);
    }

    @Override
    public boolean result() {
        return true;
    }

    @Override
    public ScoreGoal clone() {
        return new ScoreGoal(this);
    }

    @Override
    public String toString() {
        Tuple<Integer,Integer> time = DateUtils.secondsToTuple(getGameTime());
        return ColorUtils.BLUE + "[" + time.getX() + ":" + time.getY() + "]" + ColorUtils.GREEN + " Goal by " + getPlayer().getName() + ColorUtils.RESET;
    }
}
