package objects.player;

import java.util.ArrayList;
import java.util.List;

public class MidFielder extends Player{
    /**
     * MidFielder's recover ability
     */
    private int recover;

    /**
     * Instantiates a MidFielder
     */
    public MidFielder() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.recover = 0;
    }

    /**
     * Instantiates a MidFielder with Player&Person's attributes
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
     * @param recover recover ability
     */
    public MidFielder(int id, String name, int number, List<String> teamHistory,
                      int velocity, int resistance, int dexterity, int thrust,
                      int header, int finish, int pass, int recover) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.recover = recover;
    }

    /**
     * Instantiates a MidFielder from a Person's object and with Player's attributes
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
     * @param recover recover ability
     */
    public MidFielder(Person person, int number, List<String> teamHistory,
                      int velocity, int resistance, int dexterity, int thrust,
                      int header, int finish, int pass, int recover) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.recover = recover;
    }

    /**
     * Instantiates a MidFielder from a Player's object
     * @param player Player's object
     * @param recover recover ability
     */
    public MidFielder(Player player, int recover) {
        super(player);
        this.recover = recover;
    }

    /**
     * Instantiates a MidFielder from a MidFielder's object
     * @param midFielder MidFielder's object
     */
    public MidFielder(MidFielder midFielder){
        super(midFielder);
        this.recover = midFielder.getRecover();
    }

    /**
     * Parses an array of strings and creates a MidFielder's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param id Person's Id
     * @return New MidFielder instance
     * @throws NumberFormatException
     */
    public static MidFielder parser(String[] args, int id) throws NumberFormatException{
        return new MidFielder(
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
     * Calculate MidFielder's ability
     * @return MidFielder's ability value
     */
    @Override
    public int calcAbility() {
        return (int) Math.floor(
                this.getResistance() * 0.08
              + this.getDexterity() * 0.2
              + this.getPass() * 0.08
              + this.getVelocity() * 0.08
              + this.getThrust() * 0.2
              + this.getHeader() * 0.08
              + this.getFinish() * 0.08
              + this.getRecover() * 0.2
        );
    }

    /**
     * Get MidFielder's recover ability
     * @return MidFielder's recover ability value
     */
    public int getRecover() {
        return this.recover;
    }

    /**
     * Set MidFielder's recover ability
     * @param recover Recover ability value
     */
    public void setRecover(int recover) {
        this.recover = recover;
    }

    /**
     * Clone MidFielder's instance
     * @return MidFielder's cloned instance
     */
    @Override
    public MidFielder clone() {
        return new MidFielder(this);
    }

    /**
     * Equality between MidFielder's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * String representation of MidFielder's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MidFielder{");
        sb.append("recover=").append(recover);
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
