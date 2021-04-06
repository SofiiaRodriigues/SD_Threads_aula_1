package pt.ipb.dsys.com.proverb.sockets;

import pt.ipb.dsys.com.proverb.ProverbProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ProverbServerMultithreadedSocketMain implements Runnable {

    public static final int PORT = 4444;

    public static final String HOSTNAME = "127.0.0.1";

    private final Socket clientSocket;

    public ProverbServerMultithreadedSocketMain(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while(true) {
                System.out.printf("Waiting for connection on port %d\n", ProverbServerMultithreadedSocketMain.PORT);
                Socket clientSocket = serverSocket.accept();
                new Thread(new ProverbServerMultithreadedSocketMain(clientSocket)).start();
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }

    @Override
    public void run() {
        System.out.printf("[%s:%d] connected!\n", clientSocket.getInetAddress().getHostName(), clientSocket.getPort());
        ProverbProtocol pp = new ProverbProtocol();
        String fromUser = "";
        String toUser = "";
        try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            while ((toUser = pp.processInput(fromUser)) != null) {
                writer.println(toUser);
                fromUser = input.readLine();
            }
        } catch (IOException e) {
            System.out.printf("IO Error: %s\n", e.getMessage());
        }
    }
}
