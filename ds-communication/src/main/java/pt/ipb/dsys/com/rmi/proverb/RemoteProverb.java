package pt.ipb.dsys.com.rmi.proverb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteProverb extends Remote {

    String processInput(String theInput) throws RemoteException;

}
