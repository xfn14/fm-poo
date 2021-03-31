import objects.Person;
import objects.player.Keeper;
import objects.player.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("34jnb3ujhkvb4", "Andre");
        Player player = new Player(person, 23, new ArrayList<>(),0,0,5,0,0,0,0);
        Keeper keeper = new Keeper(player, 10);
        System.out.println(keeper.calcAbility());
    }
}
