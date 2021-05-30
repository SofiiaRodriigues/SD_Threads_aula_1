package pt.ipb.dsys.threads.coord;

public class CoordMain {

    public static void main(String[] args) {
        Accumulator acc = new Accumulator();
        new Thread(new ParcelGenerator(acc)).start();
        new Thread(new SumViewer(acc)).start();
    }
}
