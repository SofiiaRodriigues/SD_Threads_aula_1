package pt.ipb.dsys.threads.deadlock.swapper;

public class SwapperDeadlock {

    long value;

    public SwapperDeadlock(long value) {
        this.value = value;
    }

    public synchronized long getValue() {
        return value;
    }

    public synchronized void setValue(long value) {
        this.value = value;
    }

    public synchronized void swapValue(SwapperDeadlock other) {
        long t = getValue();
        long v = other.getValue();
        setValue(v);
        other.setValue(t);
        System.out.println("Sqapping " + t + " with " + v);
    }

    public static void main(String[] args) {
        final SwapperDeadlock s1 = new SwapperDeadlock(10);
        final SwapperDeadlock s2 = new SwapperDeadlock(20);

        new Thread(new Runnable() {
            public void run() {
                s2.swapValue(s1);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                s2.swapValue(s1);
            }
        }).start();

    }

}
