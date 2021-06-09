package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Keeper extends Player{
    /**
     * Keeper's elasticity ability
     */
    private int elasticity;

    /**
     * Instantiates a Keeper
     */
    public Keeper() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.elasticity = 0;
    }

    /**
     * Instantiates a Keeper with Player&Person's attributes
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
     * @param elasticity elasticity ability
     */
    public Keeper(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass, int elasticity) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.elasticity = elasticity;
    }

    /**
     * Instantiates a Keeper from a Person's object and with Player's attributes
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
     * @param elasticity elasticity ability
     */
    public Keeper(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass, int elasticity) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.elasticity = elasticity;
    }

    /**
     * Instantiates a Keeper from a Player's object
     * @param player Player's object
     * @param elasticity elasticity ability
     */
    public Keeper(Player player, int elasticity) {
        super(player);
        this.elasticity = elasticity;
    }

    /**
     * Instantiates a Keeper from a Keeper's object
     * @param keeper Keeper's object
     */
    public Keeper(Keeper keeper){
        super(keeper);
        this.elasticity = keeper.getElasticity();
    }

    /**
     * Calculate Keeper's ability
     * @return Keeper's ability value
     */
    @Override
    public int calcAbility() {
        return (int) Math.floor(
                this.getResistance() * 0.08
              + this.getDexterity() * 0.08
              + this.getPass() * 0.2
              + this.getVelocity() * 0.08
              + this.getThrust() * 0.08
              + this.getHeader() * 0.2
              + this.getFinish() * 0.08
              + this.getElasticity() * 0.2
        );
    }

    /**
     * Parses an array of strings and creates a Keeper's instance from it
     * @param args Array of strings containing respective values to be parsed
     * @param id Person's Id
     * @return New Keeper instance
     * @throws NumberFormatException
     */
    public static Keeper parser(String[] args, int id) throws NumberFormatException{
        return new Keeper(
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
     * Get Keeper's elasticity ability
     * @return Keeper's elasticity ability value
     */
    public int getElasticity() {
        return this.elasticity;
    }

    /**
     * Set Keeper's elasticity ability
     * @param elasticity Elasticity ability value
     */
    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    /**
     * Equality between Keeper's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Keeper keeper = (Keeper) o;

        return getElasticity() == keeper.getElasticity();
    }

    /**
     * Clone Keeper's instance
     * @return Keeper's cloned instance
     */
    @Override
    public Keeper clone() {
        return new Keeper(this);
    }

    /**
     * String representation of Keeper's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Keeper{");
        sb.append("elasticity=").append(elasticity);
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
