package utils;

public class TextUtils {
    public static String getASCIIArt(){
        StringBuilder sb = new StringBuilder();
        sb.append(ColorUtils.BLUE_BRIGHT);
        sb.append(" .----------------. .----------------.           ").append('\n');
        sb.append("| .--------------. | .--------------. |          ").append('\n');
        sb.append("| |  _________   | | | ____    ____ | |          ").append('\n');
        sb.append("| | |_   ___  |  | | ||_   \\  /   _|| |         ").append('\n');
        sb.append("| |   | |_  \\_|  | | |  |   \\/   |  | |        ").append('\n');
        sb.append("| |   |  _|      | | |  | |\\  /| |  | |         ").append('\n');
        sb.append("| |  _| |_       | | | _| |_\\/_| |_ | |         ").append('\n');
        sb.append("| | |_____|      | | ||_____||_____|| |          ").append('\n');
        sb.append("| |              | | |              | |          ").append('\n');
        sb.append("| '--------------' | '--------------' |          ").append('\n');
        sb.append(" '----------------' '----------------'           ").append('\n');
        sb.append(ColorUtils.RED_BOLD);
        sb.append("                    .----------------. .----------------. ").append('\n');
        sb.append("                   | .--------------. | .--------------. |").append('\n');
        sb.append("                   | |    _____     | | |     ____     | |").append('\n');
        sb.append("                   | |   / ___ `.   | | |   .'    '.   | |").append('\n');
        sb.append("                   | |  |_/___) |   | | |  |  .--.  |  | |").append('\n');
        sb.append("                   | |   .'____.'   | | |  | |    | |  | |").append('\n');
        sb.append("                   | |  / /____     | | |  |  `--'  |  | |").append('\n');
        sb.append("                   | |  |_______|   | | |   '.____.'   | |").append('\n');
        sb.append("                   | |              | | |              | |").append('\n');
        sb.append("                   | '--------------' | '--------------' |").append('\n');
        sb.append("                    '----------------' '----------------' ").append('\n');
        sb.append(ColorUtils.RESET);
        sb.append('\n').append('\n');
        return sb.toString();
    }
}
