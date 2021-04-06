package pt.ipb.dsys.com.proverb.sockets;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ProverbClientSocketMain {

    public static void main(String[] args) {
        try (Socket echoSocket = new Socket(ProverbServerSocketMain.HOSTNAME, ProverbServerSocketMain.PORT)) {
            try (PrintWriter output = new PrintWriter(echoSocket.getOutputStream(), true);
                 BufferedReader input = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()))
            ) {
                String fromServer;
                while((fromServer = input.readLine())!=null) {
                    if(fromServer.equals("Bye.")) {
                        break;
                    }
                    String message = JOptionPane.showInputDialog(null, fromServer);
                    output.println(message);
                }
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }

}
