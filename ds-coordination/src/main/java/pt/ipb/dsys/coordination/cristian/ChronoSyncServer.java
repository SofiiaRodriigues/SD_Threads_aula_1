package pt.ipb.dsys.coordination.cristian;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChronoSyncServer {

    public static final String LOCK = "lock";

    public static final int CHRONO_PORT = 9999;

    public static void main(String[] args) {
        Chronometer chronometer = new Chronometer();
        chronometer.start();

        new Thread(() -> {
            try (DatagramSocket socket = new DatagramSocket(CHRONO_PORT)) {
                while (true) {
                    synchronized (LOCK) {
                        System.out.println("waiting for sync requests");
                        // don't care about the request content -> inputBuffer will be empty
                        DatagramPacket packet = new DatagramPacket(new byte[1], 1);
                        socket.receive(packet);
                        System.out.printf("sync packet received from %s:%d\n", packet.getAddress().getHostAddress(), packet.getPort());

                        byte[] dataBuffer = ByteUtils.longToBytes(chronometer.getCurrent());
                        System.out.printf("sending packet (%d bytes) to %s:%d -> current is %d\n", dataBuffer.length, packet.getAddress().getHostAddress(), packet.getPort(), chronometer.getCurrent());
                        DatagramPacket response = new DatagramPacket(dataBuffer, dataBuffer.length, packet.getAddress(), packet.getPort());
                        socket.send(response);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

        while (true) {
            String text = JOptionPane.showInputDialog("To reset chronometer type value below and press [OK]");
            if (text != null)
                try {
                    long value = Long.parseLong(text);
                    chronometer.setCurrent(value);
                } catch (NumberFormatException e) {
                    // ignored
                }
        }
    }

}
