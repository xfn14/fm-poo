package objects.team;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public enum TeamFormation implements Serializable {
    /**
     * Team's formation 1-4-4-2
     */
    ONE_FOUR_FOUR_TWO    (1, 4, 4, 2),

    /**
     * Team's formation 1-4-3-3
     */
    ONE_FOUR_THREE_THREE (1, 4, 3, 3);

    /**
     * Number of keepers
     */
    private final int keepers;

    /**
     * Number of defenders
     */
    private final int defenders;

    /**
     * Number of mid fielders
     */
    private final int midFielders;

    /**
     * Number of strikers
     */
    private final int strikers;

    /**
     * Instantiates a TeamFormation from number of players per formation's role
     * @param keepers Number of keepers
     * @param defenders Number of defenders
     * @param midFielders Number of mid fielders
     * @param strikers Number of strikers
     */
    TeamFormation(int keepers, int defenders, int midFielders, int strikers){
        this.keepers = keepers;
        this.defenders = defenders;
        this.midFielders = midFielders;
        this.strikers = strikers;
    }

    /**
     * List of all possible Team's Formations
     */
    public static final List<TeamFormation> allFormations = List.of(TeamFormation.values());

    /**
     * Random object initialization
     */
    private static final Random random = new Random();

    /**
     * Get random Team's Formation
     * @return Random Team's Formation
     */
    public static TeamFormation getRandomFormation(){
        return allFormations.get(random.nextInt(allFormations.size()));
    }

    /**
     * Get number of keepers
     * @return Number of keepers
     */
    public int getKeepers() {
        return keepers;
    }

    /**
     * Get number of defenders
     * @return Number of defenders
     */
    public int getDefenders() {
        return defenders;
    }

    /**
     * Get number of mid fielders
     * @return Number of mid fielders
     */
    public int getMidFielders() {
        return midFielders;
    }

    /**
     * Get number of strikers
     * @return Number of strikers
     */
    public int getStrikers() {
        return strikers;
    }

    /**
     * String representation of TeamFormation's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        return this.keepers + "-" + this.defenders + "-" + this.midFielders + "-" + this.strikers;
    }
}
