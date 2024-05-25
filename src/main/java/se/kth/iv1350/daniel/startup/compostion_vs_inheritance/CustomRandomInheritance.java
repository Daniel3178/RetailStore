package se.kth.iv1350.daniel.startup.compostion_vs_inheritance;

import java.util.Random;

public class CustomRandomInheritance extends Random
{
        private int count = 0;
        @Override
        public int nextInt(int bound) {
            count++;
            return super.nextInt(bound);
        }

        public int getCount() {
            return count;
        }

        public static void runSample() {
            CustomRandomInheritance cstmRandom = new CustomRandomInheritance();
            System.out.println("Random number: " + cstmRandom.nextInt(100));
            System.out.println("Random number: " + cstmRandom.nextInt(100));
            System.out.println("Total random numbers generated: " + cstmRandom.getCount());
        }
}
