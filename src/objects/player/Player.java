package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person {

    /**
     * Player's shirt number
     */
    private int number;

    /**
     * List of teams where the player has been
     */
    private List<String> teamHistory; // Last team on array is the players current team.

    // Abilities [0-100]
    /**
     * Player's velocity ability
     */
    private int velocity;
    /**
     * Player's resistance ability
     */
    private int resistance;
    /**
     * Player's dexterity ability
     */
    private int dexterity;
    /**
     * Player's thrust ability
     */
    private int thrust;
    /**
     * Player's header ability
     */
    private int header;
    /**
     * Player's finish ability
     */
    private int finish;
    /**
     * Player's pass ability
     */
    private int pass;

    /**
     * Instantiates a new Player
     */
    public Player(){
        super();
        this.number = 0;
        this.teamHistory = new ArrayList<>();
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.thrust = 0;
        this.header = 0;
        this.finish = 0;
        this.pass = 0;
    }

    /**
     * Instantiates a new Player from a Person's object
     * @param person Person's object
     */
    public Player(Person person){
        super(person);
        this.number = 0;
        this.teamHistory = new ArrayList<>();
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.thrust = 0;
        this.header = 0;
        this.finish = 0;
        this.pass = 0;
    }

    /**
     * Instantiates a new Player from a Person's object and the respective number
     * @param person Person's object
     * @param number shirt number
     */
    public Player(Person person, int number){
        super(person);
        this.number = number;
        this.teamHistory = new ArrayList<>();
        this.velocity = 0;
        this.resistance = 0;
        this.dexterity = 0;
        this.thrust = 0;
        this.header = 0;
        this.finish = 0;
        this.pass = 0;
    }

    /**
     * Instantiates a new Player from a Person's object and the respective attributes
     * @param person Person's object
     * @param number shirt number
     * @param velocity velocity ability
     * @param resistance resistance ability
     * @param dexterity dexterity ability
     * @param thrust thrust ability
     * @param header header ability
     * @param finish finish ability
     * @param pass pass ability
     */
    public Player(Person person, int number, int velocity,
                  int resistance, int dexterity, int thrust,
                  int header, int finish, int pass) {
        super(person);
        this.number = number;
        this.teamHistory = new ArrayList<>();
        this.velocity = velocity;
        this.resistance = resistance;
        this.dexterity = dexterity;
        this.thrust = thrust;
        this.header = header;
        this.finish = finish;
        this.pass = pass;
    }

    /**
     * Instantiates a new Player from their respective attributes with a teams' history
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
    public Player(int number, List<String> teamHistory, int velocity,
                  int resistance, int dexterity, int thrust,
                  int header, int finish, int pass) {
        super();
        this.number = number;
        this.teamHistory = teamHistory;
        this.velocity = velocity;
        this.resistance = resistance;
        this.dexterity = dexterity;
        this.thrust = thrust;
        this.header = header;
        this.finish = finish;
        this.pass = pass;
    }


    /**
     * Instantiates a new Player from their respective attributes with a teams' history and Person's attributes
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
    public Player(int id, String name, int number, List<String> teamHistory,
                  int velocity, int resistance, int dexterity, int thrust,
                  int header, int finish, int pass) {
        super(id, name);
        this.number = number;
        this.teamHistory = teamHistory;
        this.velocity = velocity;
        this.resistance = resistance;
        this.dexterity = dexterity;
        this.thrust = thrust;
        this.header = header;
        this.finish = finish;
        this.pass = pass;
    }

    /**
     * Instantiates a new Player from a Person's object and the respective attributes with a teams' history
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
    public Player(Person person, int number, List<String> teamHistory,
                  int velocity, int resistance, int dexterity, int thrust,
                  int header, int finish, int pass) {
        super(person);
        this.number = number;
        this.teamHistory = teamHistory;
        this.velocity = velocity;
        this.resistance = resistance;
        this.dexterity = dexterity;
        this.thrust = thrust;
        this.header = header;
        this.finish = finish;
        this.pass = pass;
    }

    /**
     * Instantiates a new Player from a Player's object
     * @param player Respective Player's object
     */
    public Player(Player player){
        super(player.getId(), player.getName());
        setNumber(player.getNumber());
        setTeamHistory(player.getTeamHistory());
        setVelocity(player.getVelocity());
        setResistance(player.getResistance());
        setDexterity(player.getDexterity());
        setThrust(player.getThrust());
        setHeader(player.getHeader());
        setFinish(player.getFinish());
        setPass(player.getPass());
    }

    /**
     * Calculate Player's total ability
     * @return Player's ability
     */
    public int calcAbility(){
        return (this.velocity +
                this.resistance +
                this.dexterity +
                this.thrust +
                this.header +
                this.finish +
                this.pass) / 7;
    }

    /**
     * Set Player's current team
     * @param teamName Team's name
     */
    public void setCurrentTeam(String teamName){
        this.teamHistory.add(teamName);
    }

    /**
     * Get Player's shirt number
     * @return Player's shirt number
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Set Player's shirt number
     * @param number Respective shirt number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Get Player's team history
     * @return List of teams where the player has been to
     */
    public List<String> getTeamHistory() {
        return new ArrayList<>(this.teamHistory);
    }

    /**
     * Set Player's team history
     * @param teamHistory List of teams
     */
    public void setTeamHistory(List<String> teamHistory) {
        this.teamHistory = new ArrayList<>(teamHistory);
    }

    /**
     * Get Player's velocity ability
     * @return Player's ability velocity value
     */
    public int getVelocity() {
        return this.velocity;
    }

    /**
     * Set Player's velocity ability
     * @param velocity Velocity ability value
     */
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    /**
     * Get Player's resistance ability
     * @return Player's resistance ability value
     */
    public int getResistance() {
        return this.resistance;
    }

    /**
     * Set Player's resistance ability
     * @param resistance Resistance ability value
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    /**
     * Get Player's dexterity ability
     * @return Player's dexterity ability value
     */
    public int getDexterity() {
        return this.dexterity;
    }

    /**
     * Set Player's dexterity ability
     * @param dexterity Dexterity ability value
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * Get Player's thrust ability
     * @return Player's thrust ability value
     */
    public int getThrust() {
        return this.thrust;
    }

    /**
     * Set Player's thrust ability
     * @param thrust Thrust ability value
     */
    public void setThrust(int thrust) {
        this.thrust = thrust;
    }

    /**
     * Get Player's header ability
     * @return Player's header ability value
     */
    public int getHeader() {
        return this.header;
    }

    /**
     * Set Player's header ability
     * @param header Header ability value
     */
    public void setHeader(int header) {
        this.header = header;
    }

    /**
     * Get Player's finish ability
     * @return Player's finish ability value
     */
    public int getFinish() {
        return this.finish;
    }

    /**
     * Set Player's finish ability
     * @param finish Finish ability value
     */
    public void setFinish(int finish) {
        this.finish = finish;
    }

    /**
     * Get Player's pass ability
     * @return Player's pass ability value
     */
    public int getPass() {
        return this.pass;
    }

    /**
     * Set Player's pass ability
     * @param pass Pass ability value
     */
    public void setPass(int pass) {
        this.pass = pass;
    }

    /**
     * Clones Player's instance
     * @return Player's new object
     */
    @Override
    public Player clone() {
        return new Player(this);
    }

    /**
     * Equality between Player's instance and another object
     * @param o Object
     * @return Boolean representing the equality of this instance comparing to the given object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Player player = (Player) o;

        if (getId() != player.getId()) return false;
        return getTeamHistory() != null ? getTeamHistory().equals(player.getTeamHistory()) : player.getTeamHistory() == null;
    }

    /**
     * String representation of Player's instance
     * @return String representation of the instance
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("number=").append(number);
        sb.append(", teamHistory=").append(teamHistory);
        sb.append(", velocity=").append(velocity);
        sb.append(", resistance=").append(resistance);
        sb.append(", dexterity=").append(dexterity);
        sb.append(", thrust=").append(thrust);
        sb.append(", header=").append(header);
        sb.append(", finish=").append(finish);
        sb.append(", pass=").append(pass);
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}
