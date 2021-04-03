package objects.player;

import objects.Person;

import java.util.ArrayList;
import java.util.List;

public class Defender extends Player {
    public Defender() {
        super(null, null, 0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
    }

    public Defender(String id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Defender(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public Defender(Player player) {
        super(player);
    }

    @Override
    public Defender clone() throws CloneNotSupportedException {
        return (Defender) super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Defender{" + '\n' +
                super.toString() + "\n" +
                '}' + '\n';
    }
}
