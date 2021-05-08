package objects.player;

import objects.Person;

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

    public static FullBack parser(String input, int id) throws NumberFormatException{
        String[] campos = input.split(",");
        return new FullBack(
                id,
                campos[0],
                Integer.parseInt(campos[1]),
                new ArrayList<>(),
                Integer.parseInt(campos[2]),
                Integer.parseInt(campos[3]),
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                Integer.parseInt(campos[6]),
                Integer.parseInt(campos[7]),
                Integer.parseInt(campos[8]),
                Integer.parseInt(campos[9])
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
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}
