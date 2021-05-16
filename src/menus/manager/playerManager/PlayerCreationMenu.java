package menus.manager.playerManager;

import objects.game.GameManager;

import java.util.Scanner;

public class PlayerCreationMenu {
    private GameManager gameManager;

    public PlayerCreationMenu(){
        this.gameManager = new GameManager();
    }

    public PlayerCreationMenu(GameManager gameManager) {
        setGameManager(gameManager);
    }

    public PlayerCreationMenu(PlayerCreationMenu playerCreationMenu){
        this.gameManager = playerCreationMenu.getGameManager();
    }

    public void playerCreationLoop(){
        Scanner scanner = new Scanner(System.in);
    }

    public GameManager getGameManager() {
        return this.gameManager.clone();
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager.clone();
    }
}
