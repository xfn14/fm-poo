package objects.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FullBack extends Player implements Serializable {
    /**
     * FullBack's crossing ability
     */
    private int crossing;

    /**
     * Instantiates a FullBack with Player&Person's attributes
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
     * @param crossing crossing ability
     */
    public FullBack(int id, String name, int number, List<String> teamHistory,
                    int velocity, int resistance, int dexterity, int thrust,
                    int header, int finish, int pass, int crossing) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.crossing = crossing;
    }

    /**
     * Instantiates a FullBack from a FullBack's object
     * @param fullback FullBack's object
     */
    public FullBack(FullBack fullback) {
        super(fullback);
        this.crossing = fullback.getCrossing();
    }

    /**
     * Instantiates a FullBack from a Player's object
     * @param player Player's object
     * @param crossing crossing ability
     */
    public FullBack(Player player, int crossing) {
        super(player);
        this.crossing = crossing;
    }

    /**
     * Parses an array of strings and creates a FullBack's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param id Person's Id
     * @return New FullBack instance
     * @throws NumberFormatException
     */
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

    /**
     * Calculate FullBack's ability
     * @return FullBack's ability value
     */
    @Override
    public int calcAbility() {
        return (int) Math.floor(
                this.getResistance() * 0.08
              + this.getDexterity() * 0.08
              + this.getPass() * 0.2
              + this.getVelocity() * 0.08
              + this.getThrust() * 0.2
              + this.getHeader() * 0.08
              + this.getFinish() * 0.08
              + this.getCrossing() * 0.2
        );
    }

    /**
     * Get FullBack's crossing ability
     * @return FullBack's crossing ability value
     */
    public int getCrossing() {
        return this.crossing;
    }

    /**
     * Clone FullBack's instance
     * @return FullBack's cloned instance
     */
    @Override
    public FullBack clone() {
        return new FullBack(this);
    }

    /**
     * Equality between FullBack's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * String representation of FullBack's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FullBack{");
        sb.append("crossing=").append(crossing);
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
