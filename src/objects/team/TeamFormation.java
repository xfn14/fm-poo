package objects.team;

import java.util.List;
import java.util.Random;

public enum TeamFormation {
    ONE_FOUR_FOUR_TWO    (1, 4, 4, 2),
    ONE_FOUR_THREE_THREE (1, 4, 3, 3);

    private final int keepers;
    private final int defenders;
    private final int midFielders;
    private final int strikers;

    TeamFormation(int keepers, int defenders, int midFielders, int strikers){
        this.keepers = keepers;
        this.defenders = defenders;
        this.midFielders = midFielders;
        this.strikers = strikers;
    }

    public static final List<TeamFormation> allFormations = List.of(TeamFormation.values());
    private static final Random random = new Random();
    public static TeamFormation getRandomFormation(){
        return allFormations.get(random.nextInt(allFormations.size()));
    }

    public int getKeepers() {
        return keepers;
    }

    public int getDefenders() {
        return defenders;
    }

    public int getMidFielders() {
        return midFielders;
    }

    public int getStrikers() {
        return strikers;
    }

    @Override
    public String toString() {
        return this.keepers + "-" + this.defenders + "-" + this.midFielders + "-" + this.strikers;
    }
}
