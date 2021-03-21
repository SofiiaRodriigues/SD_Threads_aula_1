package pt.ipb.dsys.threads.deadlock.phylosophers;

import pt.ipb.dsys.threads.util.SD;

public class Philosopher implements Runnable {

    private final Fork left;
    private final Fork right;
    private final String name;

    public Philosopher(String name, Fork left, Fork right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (true) {
            doSomething(getName() + ": started thinking");
            left.pickUp();
            doSomething(getName() + ": picked left fork");
            right.pickUp();
            doSomething(getName() + ": picked right fork - started eating");
            right.putDown();
            doSomething(getName() + ": released right fork");
            left.putDown();
            doSomething(getName() + ": released left fork");
        }

    }

    private void doSomething(String string) {
        System.out.println(string);
        SD.sleep((long) (Math.random() * 100));
    }

}
