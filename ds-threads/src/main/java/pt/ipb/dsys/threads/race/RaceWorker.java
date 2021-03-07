package pt.ipb.dsys.threads.race;

public class RaceWorker implements Runnable {

    private final int max;

    private final String workerName;

    private final Counter counter;

    public RaceWorker(int max, String workerName, Counter counter) {
        this.max = max;
        this.workerName = workerName;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            counter.increment();
            System.out.printf("[%s] counter: %d\n", workerName, counter.getCounter());
            sleep(10);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // don't carae
        }
    }

}
