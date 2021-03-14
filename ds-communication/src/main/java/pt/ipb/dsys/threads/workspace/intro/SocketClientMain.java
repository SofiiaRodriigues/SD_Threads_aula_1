package pt.ipb.dsys.threads.workspace.intro;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClientMain {

    public static void main(String[] args) {
        try (Socket echoSocket = new Socket(SocketServerMain.HOSTNAME, SocketServerMain.PORT)) {
            try (OutputStream os = echoSocket.getOutputStream()) {
                try (PrintWriter writer = new PrintWriter(os)) {
                    String message = JOptionPane.showInputDialog(null, "Mensagem para o servidor?", "CLIENTE", JOptionPane.QUESTION_MESSAGE);
                    writer.println(message);
                }
            }
        } catch (IOException ex) {
            System.out.printf("IO Error: %s\n", ex.getMessage());
        }

        System.exit(0);
    }

}
