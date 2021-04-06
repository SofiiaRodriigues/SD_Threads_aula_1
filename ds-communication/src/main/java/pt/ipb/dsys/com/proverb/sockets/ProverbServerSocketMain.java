package pt.ipb.dsys.com.proverb.sockets;

import pt.ipb.dsys.com.proverb.ProverbProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProverbServerSocketMain {

    public static final int PORT = 4444;

    public static final String HOSTNAME = "127.0.0.1";

    public static void main(String[] args) {
        ProverbProtocol pp = new ProverbProtocol();
        String fromUser = "";
        String toUser = "";

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.printf("Waiting for connection on port %d\n", ProverbServerSocketMain.PORT);
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.printf("[%s:%d] connected!\n", clientSocket.getInetAddress().getHostName(), clientSocket.getPort());
                try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    while ((toUser = pp.processInput(fromUser))!=null) {
                        writer.println(toUser);
                        fromUser = input.readLine();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }
}
