package pt.ipb.dsys.com.rmi.proverb;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ProverbClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            RemoteProverb stub = (RemoteProverb) registry.lookup(ProverbProvider.PROTOCOL_NAME);
            String output = stub.processInput(null);
            System.out.println(output);
        } catch (RemoteException | NotBoundException e) {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
    }

}
