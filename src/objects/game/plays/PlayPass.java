package objects.game.plays;

import objects.game.GameConstants;
import objects.player.MidFielder;
import objects.player.Player;

import java.util.Objects;
import java.util.Random;

public class PlayPass extends GamePlay{
    private Player receiver;
    private Player interceptor = null;

    public PlayPass() {
        super();
        this.receiver = new Player();
        this.interceptor = new Player();
    }

    public PlayPass(Player player, int gameTime, int team, Player receiver, Player interceptor) {
        super(player, gameTime, team);
        this.receiver = receiver.clone();
        if(interceptor != null)
            this.interceptor = interceptor.clone();
    }

    public PlayPass(PlayPass playPass) {
        super(playPass);
        this.receiver = playPass.getReceiver();
        this.interceptor = playPass.getInterceptor();
    }

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

    public Player getReceiver() {
        return this.receiver.clone();
    }

    public static PlayPass initialGamePass(int team, int half, Player player, Player receiver){
        return new PlayPass(
                player,
                half == 1 ? 0 : GameConstants.GAME_HALF_TIME,
                team,
                receiver,
                null);

    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver.clone();
    }

    public Player getInterceptor() {
        return this.interceptor.clone();
    }

    public void setInterceptor(Player interceptor) {
        this.interceptor = interceptor.clone();
    }

    @Override
    public PlayPass clone() {
        return new PlayPass(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayPass{");
        sb.append("receiver=").append(receiver);
        sb.append(", interceptor=").append(interceptor);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PlayPass playPass = (PlayPass) o;
        return Objects.equals(receiver, playPass.receiver) && Objects.equals(interceptor, playPass.interceptor);
    }
}
