package utils;

import java.io.IOException;

public class TextUtils {
    public static final String INVALID_MENU_OPTION = ColorUtils.RED_BOLD + "Invalid option, please try again!" + ColorUtils.RESET;
    public static final String INPUT_NOT_NUMBER = ColorUtils.RED_BOLD + "Please input a number!" + ColorUtils.RESET;

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

    public static void clearConsole() throws IOException {
        final String os = System.getProperty("os.name");
        if(os.contains("Windows")) Runtime.getRuntime().exec("cls");
        else Runtime.getRuntime().exec("clear");
    }
}
