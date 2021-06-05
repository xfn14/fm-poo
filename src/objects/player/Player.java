package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Player extends Person {
    private int number; // Player's shirt number
    private List<String> teamHistory; // Last team on array is the players current team.

    // Abilities [0-100]
    private int velocity;
    private int resistance;
    private int dexterity;
    private int thrust;
    private int header;
    private int finish;
    private int pass;

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

    public String getFstLstName(){
        String[] names = this.getName().split(" ");
        if(names.length != 1) return names[0] + " " + names[names.length-1];
        return names[0];
    }

    public int calcAbility(){
        return this.velocity + this.resistance + this.dexterity + this.thrust + this.header + this.finish + this.pass;
    }

    public void setCurrentTeam(String teamName){
        this.teamHistory.add(teamName);
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getTeamHistory() {
        return new ArrayList<>(this.teamHistory);
    }

    public void setTeamHistory(List<String> teamHistory) {
        this.teamHistory = new ArrayList<>(teamHistory);
    }

    public int getVelocity() {
        return this.velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getResistance() {
        return this.resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getThrust() {
        return this.thrust;
    }

    public void setThrust(int thrust) {
        this.thrust = thrust;
    }

    public int getHeader() {
        return this.header;
    }

    public void setHeader(int header) {
        this.header = header;
    }

    public int getFinish() {
        return this.finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getPass() {
        return this.pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    @Override
    public Player clone() {
        return new Player(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Player player = (Player) o;

        if (getId() != player.getId()) return false;
        return getTeamHistory() != null ? getTeamHistory().equals(player.getTeamHistory()) : player.getTeamHistory() == null;
    }

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
