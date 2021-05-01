package objects.player;

import objects.Person;

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

    // TODO: 3/31/2021 Temporary for testing
    public int calcAbility(){
        return this.velocity + this.resistance + this.dexterity + this.thrust + this.header + this.finish + this.pass;
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
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getNumber();
        result = 31 * result + (getTeamHistory() != null ? getTeamHistory().hashCode() : 0);
        result = 31 * result + getVelocity();
        result = 31 * result + getResistance();
        result = 31 * result + getDexterity();
        result = 31 * result + getThrust();
        result = 31 * result + getHeader();
        result = 31 * result + getFinish();
        result = 31 * result + getPass();
        return result;
    }

    @Override
    public String toString() {
        return "Player{" + '\n' +
                "    " + super.toString() + "" +
                "     , number=" + number + '\n' +
                "     , teamHistory=" + teamHistory + '\n' +
                "     , velocity=" + velocity + '\n' +
                "     , resistance=" + resistance + '\n' +
                "     , dexterity=" + dexterity + '\n' +
                "     , thrust=" + thrust + '\n' +
                "     , header=" + header + '\n' +
                "     , finish=" + finish + '\n' +
                "     , pass=" + pass + '\n' +
                '}' + '\n';
    }
}
