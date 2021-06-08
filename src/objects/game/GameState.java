package objects.game;

public enum GameState {
    INIT_GAME,
    FST_HALF,
    HALF_TIME,
    SND_HALF,
    END_GAME;

    public String toString(){
        if(this.equals(INIT_GAME)) return "Pre-Game";
        else if(this.equals(FST_HALF)) return "First Half";
        else if(this.equals(HALF_TIME)) return "Half Time";
        else if(this.equals(SND_HALF)) return "Second Half";
        else return "Finished";
    }
}
