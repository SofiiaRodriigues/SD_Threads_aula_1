package pt.ipb.dsys.threads.coord;

import pt.ipb.dsys.threads.util.SD;

import java.util.Random;

public class ParcelGenerator implements Runnable {

    private final Accumulator acc;

    public ParcelGenerator(Accumulator acc) {
        this.acc = acc;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            int a = random.nextInt(5000);
            System.out.printf("Putting: %d\n", a);
            acc.put(a);

            SD.sleep(random.nextInt(5000));
        }
    }
}
