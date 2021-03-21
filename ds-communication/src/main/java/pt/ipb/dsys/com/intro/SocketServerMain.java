package pt.ipb.dsys.com.intro;

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
            System.out.printf("Waiting for connection on port %d\n", SocketServerMain.PORT);
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.printf("[%s:%d] connected!\n", clientSocket.getInetAddress().getHostName(), clientSocket.getPort());
                try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = input.readLine()) != null) {
                        JOptionPane.showMessageDialog(null, inputLine, "SERVER", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }
}
