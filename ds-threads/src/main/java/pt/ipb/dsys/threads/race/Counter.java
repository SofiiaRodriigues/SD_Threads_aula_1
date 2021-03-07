package pt.ipb.dsys.threads.race;

public class Counter {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        /*
         * 3 operações:
         *  - get counter
         *  - increment counter (local object memory)
         *  - set counter
         */
    }

    public int getCounter() {
        return counter;
    }


}
