package objects.player;

import objects.Person;

import java.util.ArrayList;
import java.util.List;

public class FullBack extends Player{
    public FullBack() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
    }

    public FullBack(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public FullBack(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    public FullBack(Player player) {
        super(player);
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
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "FullBack{" + '\n' +
                super.toString() + "\n" +
                '}' + '\n';
    }
}
