package objects.player;

import java.util.ArrayList;
import java.util.List;

public class Keeper extends Player{
    private int elasticity;

    public Keeper() {
        super(0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.elasticity = 0;
    }

    public Keeper(int id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass, int elasticity) {
        super(id, name, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.elasticity = elasticity;
    }

    public Keeper(Person person, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass, int elasticity) {
        super(person, number, teamHistory, velocity, resistance, dexterity, thrust, header, finish, pass);
        this.elasticity = elasticity;
    }

    public Keeper(Player player, int elasticity) {
        super(player);
        this.elasticity = elasticity;
    }

    public Keeper(Keeper keeper){
        super(keeper.getId(), keeper.getName(), keeper.getNumber(), keeper.getTeamHistory(), keeper.getVelocity(), keeper.getResistance(), keeper.getDexterity(), keeper.getThrust(), keeper.getHeader(), keeper.getFinish(), keeper.getPass());
        this.elasticity = keeper.getElasticity();
    }

    @Override
    public int calcAbility() {
        return super.calcAbility() + this.elasticity;
    }

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

    public int getElasticity() {
        return this.elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Keeper keeper = (Keeper) o;

        return getElasticity() == keeper.getElasticity();
    }

    @Override
    public Keeper clone() {
        return new Keeper(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Keeper{");
        sb.append("elasticity=").append(elasticity);
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}
