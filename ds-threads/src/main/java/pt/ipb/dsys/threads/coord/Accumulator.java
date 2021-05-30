package pt.ipb.dsys.threads.coord;

import pt.ipb.dsys.threads.util.SD;

import java.util.Arrays;

public class Accumulator {

    public static final int SUM_SIZE = 2;

    int[] nums = new int[SUM_SIZE];

    int n = 0;

    public synchronized void put(int parcela) {
        while (n == SUM_SIZE) {
            SD.wait(this);
        }

        nums[n++] = parcela;

        notifyAll();
    }

    public synchronized int getSum() {
        while (n < SUM_SIZE) {
            SD.wait(this);
        }

        int sum = Arrays.stream(nums).reduce(0, Integer::sum);
        n = 0;

        notifyAll();

        return sum;
    }

}
