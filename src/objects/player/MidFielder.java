package objects.player;

import objects.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MidFielder extends Player{
    private int recover;

    public MidFielder() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.recover = 0;
    }

    public MidFielder(int id, String name, int number, List<String> teamHistory,
                      int velocity, int resistance, int dexterity, int thrust,
                      int header, int finish, int pass, int recover) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.recover = recover;
    }

    public MidFielder(Person person, int number, List<String> teamHistory,
                      int velocity, int resistance, int dexterity, int thrust,
                      int header, int finish, int pass, int recover) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.recover = recover;
    }

    public MidFielder(Player player) {
        super(player);
        this.recover = 0;
    }

    public MidFielder(Player player, int recover) {
        super(player);
        this.recover = recover;
    }

    public static MidFielder parser(String input, int id) throws NumberFormatException{
        String[] campos = input.split(",");
        return new MidFielder(
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
        return super.calcAbility() + this.recover;
    }

    @Override
    public MidFielder clone() {
        return new MidFielder(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MidFielder{");
        sb.append("recover=").append(recover);
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}
