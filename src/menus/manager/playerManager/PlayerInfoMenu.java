package menus.manager.playerManager;

import menus.manager.ManagePlayersMenu;
import objects.game.GameManager;
import objects.player.*;
import utils.ColorUtils;
import utils.TextUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlayerInfoMenu {
    private GameManager gameManager;
    private int playerId;

    public PlayerInfoMenu(){
        this.gameManager = new GameManager();
        this.playerId = -1;
    }

    public PlayerInfoMenu(GameManager gameManager, int playerId) {
        this.gameManager = gameManager.clone();
        this.playerId = playerId;
    }

    public PlayerInfoMenu(PlayerInfoMenu playerInfoMenu){
        this.gameManager = playerInfoMenu.getGameManager();
        this.playerId = playerInfoMenu.getPlayerId();
    }

    public void playerInfoLoop(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        System.out.print(getPlayerInfo());
        printControls();

        do{
            try{
                int option = scanner.nextInt();
                if(option == 0){
                    ManagePlayersMenu managePlayersMenu = new ManagePlayersMenu(this.gameManager);
                    managePlayersMenu.playerListLoop();
                    quit = true;
                }else{
                    System.out.println(TextUtils.INVALID_MENU_OPTION);
                }
            }catch (InputMismatchException e){
                System.out.println(TextUtils.INPUT_NOT_NUMBER);
                scanner.next();
            }
        }while (!quit);
        scanner.close();
    }

    private void printControls(){
        System.out.println(ColorUtils.BLUE + "[0] - Go Back" + ColorUtils.RESET);
    }

    public String getPlayerInfo(){
        Player player = gameManager.getPlayerMap().get(this.playerId);
        if(player == null) return ColorUtils.RED_BOLD + "Invalid player" + ColorUtils.RESET;

        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE).append(" Player ").append(player.getId()).append(" Info\n").append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Name: ").append(ColorUtils.YELLOW).append(player.getName()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Number: ").append(ColorUtils.YELLOW).append(player.getName()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Team History: ").append(ColorUtils.YELLOW).append(player.getTeamHistory()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append(" -> Skills: ").append(ColorUtils.YELLOW).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Velocity: ").append(ColorUtils.YELLOW).append(player.getVelocity()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Resistance: ").append(ColorUtils.YELLOW).append(player.getResistance()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Dexterity: ").append(ColorUtils.YELLOW).append(player.getDexterity()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Thrust: ").append(ColorUtils.YELLOW).append(player.getThrust()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Header: ").append(ColorUtils.YELLOW).append(player.getHeader()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Finish: ").append(ColorUtils.YELLOW).append(player.getFinish()).append('\n').append(ColorUtils.RESET);
        sb.append(ColorUtils.GREEN).append("    Pass: ").append(ColorUtils.YELLOW).append(player.getPass()).append('\n').append(ColorUtils.RESET);

        if(player instanceof Keeper){
            Keeper keeper = (Keeper) player;
            sb.append(ColorUtils.GREEN).append("    Elasticity: ").append(ColorUtils.YELLOW).append(keeper.getElasticity()).append('\n').append(ColorUtils.RESET);
            sb.append(ColorUtils.GREEN).append(" -> Position: ").append(ColorUtils.YELLOW).append("Keeper").append('\n').append(ColorUtils.RESET);
        }else if(player instanceof Defender){
            sb.append(ColorUtils.GREEN).append(" -> Position: ").append(ColorUtils.YELLOW).append("Defender").append('\n').append(ColorUtils.RESET);
        }else if(player instanceof FullBack){
            FullBack fullBack = (FullBack) player;
            sb.append(ColorUtils.GREEN).append("    Crossing: ").append(ColorUtils.YELLOW).append(fullBack.getCrossing()).append('\n').append(ColorUtils.RESET);
            sb.append(ColorUtils.GREEN).append(" -> Position: ").append(ColorUtils.YELLOW).append("Full-Back").append('\n').append(ColorUtils.RESET);
        }else if(player instanceof MidFielder){
            MidFielder midFielder = (MidFielder) player;
            sb.append(ColorUtils.GREEN).append("    Recover: ").append(ColorUtils.YELLOW).append(midFielder.getRecover()).append('\n').append(ColorUtils.RESET);
            sb.append(ColorUtils.GREEN).append(" -> Position: ").append(ColorUtils.YELLOW).append("Mid-Fielder").append('\n').append(ColorUtils.RESET);
        }else if(player instanceof Striker){
            sb.append(ColorUtils.GREEN).append(" -> Position: ").append(ColorUtils.YELLOW).append("Striker").append('\n').append(ColorUtils.RESET);
        }

        return sb.toString();
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
