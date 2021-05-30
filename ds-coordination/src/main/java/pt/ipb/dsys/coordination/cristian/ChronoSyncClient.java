package pt.ipb.dsys.coordination.cristian;

import javax.swing.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChronoSyncClient {

    public static void main(String[] args) throws InterruptedException {
        Chronometer chronometer = new Chronometer();
        chronometer.start();

        try (DatagramSocket socket = new DatagramSocket()) {

            while (true) {
                JOptionPane.showMessageDialog(null, "Press [OK] to sync.");

                long checkpoint = System.currentTimeMillis();

                // sync request will be empty
                DatagramPacket syncPacket = new DatagramPacket(new byte[1], 1, InetAddress.getByName("localhost"), ChronoSyncServer.CHRONO_PORT);
                socket.send(syncPacket);

                System.out.print("requesting time from server\n");

                byte[] data = new byte[Long.BYTES];
                DatagramPacket inputPacket = new DatagramPacket(data, data.length);
                socket.receive(inputPacket);

                byte[] inputData = inputPacket.getData();
                long serverTime = pt.ipb.dsys.coord.chrono.ByteUtils.bytesToLong(inputData);
                long elapsed = System.currentTimeMillis() - checkpoint;
                long delay = elapsed / 2;

                chronometer.setCurrent(serverTime + delay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
