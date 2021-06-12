package objects.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Striker extends Player implements Serializable {
    /**
     * Instantiates a Striker with Player&Person's attributes
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
    public Striker(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
    }

    /**
     * Instantiates a Striker from a Player's object
     * @param player Player's object
     */
    public Striker(Player player) {
        super(player);
    }

    /**
     * Parses an array of strings and creates a Striker's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param id Person's Id
     * @return New Striker instance
     * @throws NumberFormatException
     */
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

    /**
     * Calculate Striker's ability
     * @return Striker's ability value
     */
    @Override
    public int calcAbility() {
        return (int) Math.floor(
                this.getResistance() * 0.1
              + this.getDexterity() * 0.1
              + this.getPass() * 0.1
              + this.getVelocity() * 0.2
              + this.getThrust() * 0.1
              + this.getHeader() * 0.2
              + this.getFinish() * 0.2
        );
    }

    /**
     * Clone Striker's instance
     * @return Striker's cloned instance
     */
    @Override
    public Striker clone() {
        return new Striker(this);
    }

    /**
     * Equality between Striker's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * String representation of Striker's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Striker{");
        sb.append(super.toString()).append("}").append('\n');
        return sb.toString();
    }
}
