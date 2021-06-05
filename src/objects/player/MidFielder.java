package objects.player;

import java.util.ArrayList;
import java.util.List;

public class MidFielder extends Player{
    // TODO: 5/18/2021 Recover not being obtained from file 
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

    public MidFielder(MidFielder midFielder){
        super(midFielder.getId(), midFielder.getName(), midFielder.getNumber(), midFielder.getTeamHistory(), midFielder.getVelocity(), midFielder.getResistance(), midFielder.getDexterity(), midFielder.getThrust(), midFielder.getHeader(), midFielder.getFinish(), midFielder.getPass());
        this.recover = midFielder.getRecover();
    }

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

    @Override
    public int calcAbility() {
        return super.calcAbility() + this.recover;
    }

    public int getRecover() {
        return this.recover;
    }

    public void setRecover(int recover) {
        this.recover = recover;
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
        sb.append("} ").append(super.toString()).append('\n');
        return sb.toString();
    }
}
