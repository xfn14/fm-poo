package utils;

import java.io.IOException;

public class TextUtils {
    /**
     * Text for Invalid option in menu
     */
    public static final String INVALID_MENU_OPTION = ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET;

    /**
     * Text when input isn't a number
     */
    public static final String INPUT_NOT_NUMBER = ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET;

    /**
     * Ascii Art of the project
     * @return String art
     */
    public static String getASCIIArt(){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE_BRIGHT);
        sb.append(ColorUtils.BLUE_BRIGHT).append(" .----------------. .----------------.   ")   .append(ColorUtils.RED_BOLD).append(" .----------------. .----------------. ").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| .--------------. | .--------------. |  ")   .append(ColorUtils.RED_BOLD).append("| .--------------. | .--------------. |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| |  _________   | | | ____    ____ | |  ")   .append(ColorUtils.RED_BOLD).append("| |    _____     | | |      __      | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| | |_   ___  |  | | ||_   \\  /   _|| |  ")  .append(ColorUtils.RED_BOLD).append("| |   / ___ `.   | | |     /  |     | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| |   | |_  \\_|  | | |  |   \\/   |  | |  ") .append(ColorUtils.RED_BOLD).append("| |  |_/___) |   | | |     `| |     | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| |   |  _|      | | |  | |\\  /| |  | |  ")  .append(ColorUtils.RED_BOLD).append("| |   .'____.'   | | |      | |     | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| |  _| |_       | | | _| |_\\/_| |_ | |  ")  .append(ColorUtils.RED_BOLD).append("| |  / /____     | | |     _| |_    | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| | |_____|      | | ||_____||_____|| |  " )  .append(ColorUtils.RED_BOLD).append("| |  |_______|   | | |    |_____|   | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| |              | | |              | |  " )  .append(ColorUtils.RED_BOLD).append("| |              | | |              | |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append("| '--------------' | '--------------' |  " )  .append(ColorUtils.RED_BOLD).append("| '--------------' | '--------------' |").append('\n');
        sb.append(ColorUtils.BLUE_BRIGHT).append(" '----------------' '----------------'   " )  .append(ColorUtils.RED_BOLD).append(" '----------------' '----------------' ").append('\n');
        sb.append(ColorUtils.RESET);
        return sb.toString();
    }

    /**
     * Clear Console
     */
    // Doesnt work on IntelliJ terminal
    public static void clearConsole() {
        try{
            final String os = System.getProperty("os.name");
            if(os.contains("Windows")) Runtime.getRuntime().exec("cls");
            else Runtime.getRuntime().exec("clear");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
