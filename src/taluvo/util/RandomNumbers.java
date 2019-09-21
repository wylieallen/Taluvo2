package taluvo.util;

import java.util.Random;

public final class RandomNumbers
{
    private RandomNumbers() {}

    private static Random random = new Random();

    public static int nextInt(int bound)
    {
        return random.nextInt(bound);
    }

    public static float nextFloat()
    {
        return random.nextFloat();
    }

    public static double nextDouble()
    {
        return random.nextDouble();
    }
}
