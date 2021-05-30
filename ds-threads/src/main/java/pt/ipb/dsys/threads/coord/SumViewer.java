package pt.ipb.dsys.threads.coord;

import pt.ipb.dsys.threads.util.SD;

import java.util.Random;

public class SumViewer implements Runnable {

    private final Accumulator acc;

    public SumViewer(Accumulator acc) {
        this.acc = acc;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            System.out.printf("The sum is: %d\n", acc.getSum());

            SD.sleep(random.nextInt(5000));
        }
    }
}
