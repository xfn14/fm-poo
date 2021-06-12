package utils;

import java.util.Random;

public class RandomUtils {
    private static final Random random = new Random();

    public static int randomBetween(int low, int high){
        return random.nextInt(high-low)+low;
    }
}
