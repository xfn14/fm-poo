package utils;

public class TextUtils {
    public static String getASCIIArt(){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE_BRIGHT);
        sb.append(" .----------------. .----------------.     ")   .append(" .----------------. .----------------. ").append('\n');
        sb.append("| .--------------. | .--------------. |    ")   .append("| .--------------. | .--------------. |").append('\n');
        sb.append("| |  _________   | | | ____    ____ | |    ")   .append("| |    _____     | | |     ____     | |").append('\n');
        sb.append("| | |_   ___  |  | | ||_   \\  /   _|| |    ")  .append("| |   / ___ `.   | | |   .'    '.   | |").append('\n');
        sb.append("| |   | |_  \\_|  | | |  |   \\/   |  | |    ") .append("| |  |_/___) |   | | |  |  .--.  |  | |").append('\n');
        sb.append("| |   |  _|      | | |  | |\\  /| |  | |    ")  .append("| |   .'____.'   | | |  | |    | |  | |").append('\n');
        sb.append("| |  _| |_       | | | _| |_\\/_| |_ | |    ")  .append("| |  / /____     | | |  |  `--'  |  | |").append('\n');
        sb.append("| | |_____|      | | ||_____||_____|| |    " )  .append("| |  |_______|   | | |   '.____.'   | |").append('\n');
        sb.append("| |              | | |              | |    " )  .append("| |              | | |              | |").append('\n');
        sb.append("| '--------------' | '--------------' |    " )  .append("| '--------------' | '--------------' |").append('\n');
        sb.append(" '----------------' '----------------'     " )  .append(" '----------------' '----------------' ").append('\n');
        sb.append(ColorUtils.RESET);
        return sb.toString();
    }
}
