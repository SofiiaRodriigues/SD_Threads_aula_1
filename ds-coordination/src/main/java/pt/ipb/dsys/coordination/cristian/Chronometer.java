package pt.ipb.dsys.coordination.cristian;

import java.util.Timer;
import java.util.TimerTask;

public class Chronometer {

    private static final int INTERVAL = 1000; // => 1 second

    private final TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            current += INTERVAL;
            System.out.printf("elapsed: %02d:%02d.%03d\n",
                    current / (60 * 1000), // minutes
                    (current / 1000) % 60, // seconds
                    current % 1000 // milliseconds
            );
        }
    };

    // milliseconds
    private long current = 0;

    public Chronometer() {
        setCurrent(0);
    }

    public void start() {
        new Timer(true).scheduleAtFixedRate(timerTask, 0, INTERVAL);
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long value) {
        System.out.printf("Chronometer reset from %d to %d (diff: %d)\n",
                current, value, value - current);
        this.current = value;
    }

}
