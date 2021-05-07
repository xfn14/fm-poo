import objects.Game;
import objects.Person;
import objects.Team;
import objects.player.*;
import utils.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Player> realMadridPlayers = new ArrayList<>();
        realMadridPlayers.add(new Keeper(new Player(new Person()), 98));
        realMadridPlayers.add(new Defender(new Player(new Person())));
        realMadridPlayers.add(new MidFielder(new Player(new Person())));
        realMadridPlayers.add(new FullBack(new Player(new Person())));
        realMadridPlayers.add(new Striker(new Player(new Person())));
        realMadridPlayers.add(new Defender(new Player(new Person())));
        realMadridPlayers.add(new MidFielder(new Player(new Person())));
        realMadridPlayers.add(new Striker(new Player(new Person())));
        realMadridPlayers.add(new FullBack(new Player(new Person())));
        realMadridPlayers.add(new Defender(new Player(new Person())));
        realMadridPlayers.add(new Defender(new Player(new Person())));
        realMadridPlayers.add(new MidFielder(new Player(new Person())));
        realMadridPlayers.add(new Striker(new Player(new Person())));
        realMadridPlayers.add(new MidFielder(new Player(new Person())));
        realMadridPlayers.add(new FullBack(new Player(new Person())));
        realMadridPlayers.add(new Defender(new Player(new Person())));
        realMadridPlayers.add(new Keeper(new Player(new Person()), 87));
        realMadridPlayers.add(new FullBack(new Player(new Person())));
//        System.out.println(realMadridPlayers);

        List<Player> juventusPlayers = new ArrayList<>();
        juventusPlayers.add(new Keeper(new Player(new Person()), 97));
        juventusPlayers.add(new Defender(new Player(new Person())));
        juventusPlayers.add(new MidFielder(new Player(new Person())));
        juventusPlayers.add(new Defender(new Player(new Person())));
        juventusPlayers.add(new Defender(new Player(new Person())));
        juventusPlayers.add(new Defender(new Player(new Person())));
        juventusPlayers.add(new Striker(new Player(new Person())));
        juventusPlayers.add(new MidFielder(new Player(new Person())));
        juventusPlayers.add(new FullBack(new Player(new Person())));
        juventusPlayers.add(new Striker(new Player(new Person())));
        juventusPlayers.add(new FullBack(new Player(new Person())));
        juventusPlayers.add(new Striker(new Player(new Person())));
        juventusPlayers.add(new MidFielder(new Player(new Person())));
        juventusPlayers.add(new MidFielder(new Player(new Person())));
        juventusPlayers.add(new FullBack(new Player(new Person())));
        juventusPlayers.add(new Defender(new Player(new Person())));
        juventusPlayers.add(new Keeper(new Player(new Person()), 85));
        juventusPlayers.add(new FullBack(new Player(new Person())));
//        System.out.println(juventusPlayers);

        Team realMadrid = new Team(
                "Real Madrid CF",
                realMadridPlayers,
                66,
                new ArrayList<>()
        );
        Team juventus = new Team(
                "Juventus",
                juventusPlayers,
                66,
                new ArrayList<>()
        );

        Game game = new Game(
                0,
                realMadrid,
                juventus,
                Game.State.ENDED,
                93,
                new Tuple<>(1, 1),
                realMadridPlayers.subList(0, 11),
                juventusPlayers.subList(0, 11)
        );

        System.out.println(game.toString());
    }
}
