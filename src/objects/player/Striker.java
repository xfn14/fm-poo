package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Striker extends Player {
    public Striker() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
    }

    public Striker(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Striker(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Striker(Player player) {
        super(player);
    }

    public static Striker parser(String[] args, int id) throws NumberFormatException{
        return new Striker(
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
                Integer.parseInt(args[8])
        );
    }

    @Override
    public Striker clone() {
        return new Striker(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Striker{");
        sb.append(super.toString()).append("}").append('\n');
        return sb.toString();
    }
}
