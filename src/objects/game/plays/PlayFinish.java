package objects.game.plays;

import objects.game.GameConstants;
import objects.player.Keeper;
import objects.player.Player;

import java.util.Objects;
import java.util.Random;

public class PlayFinish extends GamePlay{
    private Player keeper;

    public PlayFinish() {
        super();
        this.keeper = new Player();
    }

    public PlayFinish(Player player, int gameTime, int team, Player keeper) {
        super(player, gameTime, team);
        this.keeper = keeper.clone();
    }

    public PlayFinish(PlayFinish playFinish) {
        super(playFinish);
        this.keeper = playFinish.getKeeper();
    }

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

    public Player getKeeper() {
        return this.keeper.clone();
    }

    public void setKeeper(Player keeper) {
        this.keeper = keeper.clone();
    }

    @Override
    public PlayFinish clone() {
        return new PlayFinish(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayFinish{");
        sb.append("keeper=").append(keeper);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayFinish that = (PlayFinish) o;
        return Objects.equals(keeper, that.keeper);
    }
}
