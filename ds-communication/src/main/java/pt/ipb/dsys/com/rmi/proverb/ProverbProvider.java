package pt.ipb.dsys.com.rmi.proverb;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ProverbProvider {

    public static final String PROTOCOL_NAME = "RemoteProverb";

    public static void main(String[] args) {
        RemoteProverb proverb = new RemoteProverbImpl();
        try {
            RemoteProverb stub = (RemoteProverb) UnicastRemoteObject.exportObject(proverb, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(PROTOCOL_NAME, stub);
            System.out.printf("%s bound.\n", PROTOCOL_NAME);
        } catch (RemoteException e) {
            System.out.printf("Erro: %s\n", e.getMessage());
        }
    }

}
