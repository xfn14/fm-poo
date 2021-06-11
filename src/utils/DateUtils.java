package utils;

import java.time.format.DateTimeFormatter;

public class DateUtils {
    /**
     * Date Format year-month-day
     */
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static Tuple<Integer,Integer> secondsToTuple(int secs){
        return new Tuple<>(secs / 60, secs % 60);
    }
}
