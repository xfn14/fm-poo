package menus.manager.playerManager;

import objects.game.GameManager;
import objects.player.*;
import objects.team.Team;
import utils.ColorUtils;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class PlayerCreationMenu {
    /**
     * Input Scanner
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * GameManager instance
     */
    private GameManager gameManager;

    /**
     * Instantiates a PlayerCreationMenu
     */
    public PlayerCreationMenu(){
        this.gameManager = new GameManager();
    }

    /**
     * Instantiates a PlayerCreationMenu with GameManager
     * @param gameManager GameManager's object
     */
    public PlayerCreationMenu(GameManager gameManager) {
        setGameManager(gameManager);
    }

    /**
     * Instantiates a PlayerCreationMenu from a PlayerCreationMenu
     * @param playerCreationMenu PlayerCreationMenu's object
     */
    public PlayerCreationMenu(PlayerCreationMenu playerCreationMenu){
        this.gameManager = playerCreationMenu.getGameManager();
    }

    /**
     * Menu for user to create a new Player
     */
    public void playerCreationMenu(){
        // Player stats are given randomly
        Random random = new Random();
        Person person = null;
        Player player = null;

        while(person == null){
            System.out.println(ColorUtils.GREEN + "Insert player name: " + ColorUtils.RESET);
            String playerName = scanner.nextLine();
            if(!playerName.isBlank() && !playerName.contains(",") && !playerName.contains("-")) {
                person = new Person(this.gameManager.getPlayerMap().size(), playerName);
            }
        }

        while(player == null){
            System.out.println(ColorUtils.GREEN + "Insert shirt number: " + ColorUtils.RESET);
            try{
                int shirtNumber = Integer.parseInt(scanner.nextLine());
                if(shirtNumber >= 0 && shirtNumber <= 99)
                    player = new Player(person, shirtNumber, random.nextInt(100), random.nextInt(100),
                            random.nextInt(100), random.nextInt(100), random.nextInt(100),
                            random.nextInt(100), random.nextInt(100));
            }catch (NumberFormatException e){
                System.out.println(ColorUtils.RED + "Invalid shirt number." + ColorUtils.RESET);
                scanner.next();
            }
        }

        boolean done = false;
        int pos = -1;
        System.out.println(ColorUtils.GREEN + "Choose player in field position: " + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "[0] - Keeper" + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "[1] - Defender" + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "[2] - Full-Back" + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "[3] - Mid-Fielder" + ColorUtils.RESET);
        System.out.println(ColorUtils.GREEN + "[4] - Striker" + ColorUtils.RESET);
        while(!done){
            try{
                pos = scanner.nextInt();
                if(0 <= pos && pos <= 4) done = true;
                else System.out.println(ColorUtils.RED + "Invalid in field position" + ColorUtils.RESET);
            }catch (InputMismatchException e){
                System.out.println(ColorUtils.RED + "Invalid in field position." + ColorUtils.RESET);
                scanner.next();
            }
        }

        if(pos == 0) player = new Keeper(player, random.nextInt(100));
        else if(pos == 1) player = new Defender(player);
        else if(pos == 2) player = new FullBack(player, random.nextInt(100));
        else if(pos == 3) player = new MidFielder(player, random.nextInt(100));
        else player = new Striker(player);

        done = false;
        String playerTeamName = null;
        System.out.println(ColorUtils.GREEN + "Insert player team name: " + ColorUtils.RESET);
        while(!done){
            playerTeamName = scanner.nextLine();
            if(this.gameManager.getTeamMap().containsKey(playerTeamName)) done = true;
            else if(!playerTeamName.isBlank()) System.out.println(ColorUtils.RED + "Invalid team name." + ColorUtils.RESET);
        }

        Team playerTeam = this.gameManager.getTeamMap().get(playerTeamName).clone();
        player = playerTeam.addPlayer(player);

        this.gameManager.addPlayer(player);
        this.gameManager.addTeam(playerTeam);

        System.out.println(ColorUtils.BLUE + "Player " + player.getName() + " has been created and added to " + playerTeam.getName() + ColorUtils.RESET);
    }

    /**
     * Get GameManager
     * @return GameManager's object
     */
    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    /**
     * Set GameManager
     * @param gameManager GameManager's object
     */
    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }
}
