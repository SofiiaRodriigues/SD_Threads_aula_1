package pt.ipb.dsys.com.rmi.proverb;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProverbClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            RemoteProverb remote = (RemoteProverb) registry.lookup(ProverbProvider.PROTOCOL_NAME);

            String fromServer;
            String fromUser = "";

            while ((fromServer = remote.processInput(fromUser)) != null) {
                if (fromServer.equals("Bye."))
                    break;
                fromUser = JOptionPane.showInputDialog(fromServer);
            }
        } catch (RemoteException | NotBoundException e) {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
    }

}
