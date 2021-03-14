package pt.ipb.dsys.threads.workspace.intro;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerMain {

    public static final int PORT = 4444;

    public static final String HOSTNAME = "127.0.0.1";

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            try (Socket clientSocket = serverSocket.accept()) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        JOptionPane.showMessageDialog(null, inputLine, "SERVIDOR", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }
}
