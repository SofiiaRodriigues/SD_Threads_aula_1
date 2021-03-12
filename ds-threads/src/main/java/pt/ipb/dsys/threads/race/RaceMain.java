package pt.ipb.dsys.threads.race;

public class RaceMain {

    public static void main(String[] args) {
        Counter counter = new Counter();
        new Thread(new RaceWorker(300, "A", counter)).start();
        new Thread(new RaceWorker(300, "\tB", counter)).start();
    }

}
