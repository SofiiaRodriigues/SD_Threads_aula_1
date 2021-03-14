package pt.ipb.dsys.threads.util;

public abstract class SD {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignore) {
            // don't care
        }
    }

}
