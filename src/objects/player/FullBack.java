package objects.player;

import java.util.ArrayList;
import java.util.List;

public class FullBack extends Player{
    private int crossing;

    public FullBack() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.crossing = 0;
    }

    public FullBack(int id, String name, int number, List<String> teamHistory,
                    int velocity, int resistance, int dexterity, int thrust,
                    int header, int finish, int pass, int crossing) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.crossing = crossing;
    }

    public FullBack(Person person, int number, List<String> teamHistory, int velocity,
                    int resistance, int dexterity, int thrust, int header,
                    int finish, int pass, int crossing) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.crossing = crossing;
    }

    public FullBack(Player player) {
        super(player);
        this.crossing = 0;
    }

    public static FullBack parser(String[] args, int id) throws NumberFormatException{
        return new FullBack(
                id,
                args[0],
                Integer.parseInt(args[1]),
                new ArrayList<>(),
                Integer.parseInt(args[2]),
                Integer.parseInt(args[3]),
                Integer.parseInt(args[4]),
                Integer.parseInt(args[5]),
                Integer.parseInt(args[6]),
                Integer.parseInt(args[7]),
                Integer.parseInt(args[8]),
                Integer.parseInt(args[9])
        );
    }

    @Override
    public int calcAbility() {
        return super.calcAbility() + this.crossing;
    }

    public int getCrossing() {
        return this.crossing;
    }

    public void setCrossing(int crossing) {
        this.crossing = this.crossing;
    }

    @Override
    public FullBack clone() {
        return new FullBack(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FullBack{");
        sb.append("crossing=").append(crossing);
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
