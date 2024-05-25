package se.kth.iv1350.daniel.startup.compostion_vs_inheritance;

import java.util.Random;

public class CustomRandomComposition
{
    private final Random random = new Random();
    private int count = 0;

    public int nextInt(int bound) {
        count++;
        return random.nextInt(bound);
    }

    public int getCount() {
        return count;
    }

    public static void runSample() {
        CustomRandomComposition cstmRandom = new CustomRandomComposition();
        System.out.println("Random number: " + cstmRandom.nextInt(100));
        System.out.println("Random number: " + cstmRandom.nextInt(100));
        System.out.println("Total random numbers generated: " + cstmRandom.getCount());
    }
}
