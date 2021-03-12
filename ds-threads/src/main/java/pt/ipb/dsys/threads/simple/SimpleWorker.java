package pt.ipb.dsys.threads.simple;

import java.util.Random;

public class SimpleWorker implements Runnable {

    private final int times;

    private final String message;

    public SimpleWorker(int times, String message) {
        this.times = times;
        this.message = message;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < times; i++) {
            System.out.printf("[%d] %s\n", i, message);
            sleep(random.nextInt(1000));
        }
    }

    // Soon...
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignore) {
            // don't care...
        }
    }

}
