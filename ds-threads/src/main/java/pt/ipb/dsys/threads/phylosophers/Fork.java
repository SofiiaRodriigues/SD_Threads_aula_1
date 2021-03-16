package pt.ipb.dsys.threads.phylosophers;

import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    ReentrantLock lock = new ReentrantLock();

    public Fork() {
    }

    public void pickUp() {
        lock.lock();
    }

    public void putDown() {
        lock.unlock();
    }

}
