import menus.StartMenu;
import utils.TextUtils;

public class Main {
    public static void main(String[] args) {
        System.out.println(TextUtils.getASCIIArt());

        StartMenu startMenu = new StartMenu();
        startMenu.startMenuLoop();
    }
}
