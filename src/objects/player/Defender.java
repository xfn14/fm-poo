package objects.player;

import objects.Person;

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

    public static Defender parser(String input, int id){
        String[] campos = input.split(",");
        try{
            return new Defender(
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
                    Integer.parseInt(campos[8])
            );
        }catch (NumberFormatException e){
            return null;
        }
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
