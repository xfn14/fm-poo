package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Defender extends Player {
    /**
     * Instantiates a Defender
     */
    public Defender() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * Instantiates a Defender with Player&Person's attributes
     * @param id Identification of the Person
     * @param name Name of the Person
     * @param number shirt number
     * @param teamHistory list of teams where the player has been
     * @param velocity velocity ability
     * @param resistance resistance ability
     * @param dexterity dexterity ability
     * @param thrust thrust ability
     * @param header header ability
     * @param finish finish ability
     * @param pass pass ability
     */
    public Defender(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    /**
     * Instantiates a Defender from a Person's object and with Player's attributes
     * @param person Person's object
     * @param number shirt number
     * @param teamHistory list of teams where the player has been
     * @param velocity velocity ability
     * @param resistance resistance ability
     * @param dexterity dexterity ability
     * @param thrust thrust ability
     * @param header header ability
     * @param finish finish ability
     * @param pass pass ability
     */
    public Defender(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    /**
     * Instantiates a Defender from a Player's object
     * @param player Player's object
     */
    public Defender(Player player) {
        super(player);
    }

    /**
     * Parses an array of strings and creates a Defender's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param id Person's Id
     * @return New Striker instance
     * @throws NumberFormatException
     */
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

    /**
     * Calculate Defender's ability
     * @return Defender's ability value
     */
    @Override
    public int calcAbility() {
        return (int) Math.floor(
                this.getResistance() * 0.2
              + this.getDexterity() * 0.2
              + this.getPass() * 0.2
              + this.getVelocity() * 0.1
              + this.getThrust() * 0.1
              + this.getHeader() * 0.1
              + this.getFinish() * 0.1
        );
    }

    /**
     * Clone Defender's instance
     * @return Defender's cloned instance
     */
    @Override
    public Defender clone() {
        return new Defender(this);
    }

    /**
     * Equality between Defender's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }


    /**
     * String representation of Defender's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Defender{");
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
