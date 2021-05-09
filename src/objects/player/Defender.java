package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Defender extends Player {
    public Defender() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
    }

    public Defender(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Defender(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Defender(Player player) {
        super(player);
    }

    public static Defender parser(String[] args, int id) throws NumberFormatException{
        return new Defender(
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
    public Defender clone() {
        return new Defender(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Defender{" + '\n' +
                super.toString() + "\n" +
                '}' + '\n';
    }
}
