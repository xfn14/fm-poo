package objects.game;

import java.io.Serializable;

public enum GameState implements Serializable {
    /**
     * Begin of the game
     */
    INIT_GAME,

    /**
     * First half of the game
     */
    FST_HALF,

    /**
     * Half time of the game
     */
    HALF_TIME,

    /**
     * Second half of the game
     */
    SND_HALF,

    /**
     * End of the game
     */
    END_GAME;

    /**
     * String representation of GameState's instance
     * @return String representation of the instance
     */
    public String toString(){
        if(this.equals(INIT_GAME)) return "Pre-Game";
        else if(this.equals(FST_HALF)) return "First Half";
        else if(this.equals(HALF_TIME)) return "Half Time";
        else if(this.equals(SND_HALF)) return "Second Half";
        else return "Finished";
    }
}
