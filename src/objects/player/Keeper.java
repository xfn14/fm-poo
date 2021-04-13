package objects.player;

import objects.Person;

import java.util.ArrayList;
import java.util.List;

public class Keeper extends Player{
    private int elasticity;

    public Keeper() {
        super(null, null, 0, new ArrayList<>(), 0, 0, 0, 0, 0, 0, 0);
        this.elasticity = 0;
    }

    public Keeper(String id, String name, int number, List<String> teamHistory, int velocity, int resistance, int dexterity, int thrust, int header, int finish, int pass, int elasticity) {
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

    public int getElasticity() {
        return this.elasticity;
    }

    public void setElasticity(int elasticity) {
        this.elasticity = elasticity;
    }

    // TODO: 3/31/2021 Temporary for testing
    @Override
    public int calcAbility() {
        return super.calcAbility() + this.elasticity;
    }

    @Override
    public Keeper clone() throws CloneNotSupportedException {
        return (Keeper) super.clone();
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
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getElasticity();
        return result;
    }

    @Override
    public String toString() {
        return "Keeper{" + '\n' +
                super.toString() + "\n" +
                "    elasticity=" + elasticity +'\n' +
                '}' + '\n';
    }
}
