package objects.game;

public class GameConstants {
    /**
     * Maximum extra time on Game's half
     */
    public static int MAX_EXTRA_TIME = 7;

    /**
     * Maximum time between simulation plays
     */
    public static int MAX_SIM_TIME = 4;

    /**
     * Minimum time between simulation plays
     */
    public static int MIN_SIM_TIME = 1;

    /**
     * Minimum number of players per team (in field players + substitute players)
     */
    public static int MIN_GAME_PLAYER = 14;

    /**
     * Maximum possible substitutions
     */
    public static int MAX_SUB_NUMBER = 3;

    /**
     * Game half in seconds
     */
    public static int GAME_HALF_TIME = 45*60; // 45 min in seconds

    /**
     * Game play maximum time difference
     */
    public static int GAME_TIME_MAX_DIFF = 10;

    /**
     * Ability pass difference
     */
    public static int PASS_DIFF = 70;

    /**
     * Ability recover difference
     */
    public static int RECOVER_DIFF = 80;

    /**
     * Ability finish difference
     */
    public static int FINISH_DIFF = 75;

    /**
     * Ability save difference
     */
    public static int SAVE_DIFF = 100;

    /**
     * Ability save difference as a non keeper
     */
    public static int SAVE_DIFF_NOT_KEEPER = 150;
}
