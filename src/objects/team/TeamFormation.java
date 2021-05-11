package objects.team;

import java.util.List;
import java.util.Random;

public enum TeamFormation {
    ONE_FOUR_FOUR_TWO    ("1-4-4-2"),
    ONE_FOUR_THREE_THREE ("1-4-3-3");

    private String name;

    TeamFormation(String name){
        this.name = name;
    }

    private static final List<TeamFormation> allFormations = List.of(TeamFormation.values());
    private static final Random random = new Random();

    public static TeamFormation getRandomFormation(){
        return allFormations.get(random.nextInt(allFormations.size()));
    }

    public String getName() {
        return name;
    }
}
