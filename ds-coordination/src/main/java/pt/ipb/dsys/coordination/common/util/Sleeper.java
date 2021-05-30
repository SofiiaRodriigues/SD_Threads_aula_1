package pt.ipb.dsys.coordination.common.util;

public abstract class Sleeper {

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignore) {
            // don't care
        }
    }

}
