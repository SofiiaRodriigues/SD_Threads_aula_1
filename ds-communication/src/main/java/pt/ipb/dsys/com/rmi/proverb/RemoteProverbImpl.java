package pt.ipb.dsys.com.rmi.proverb;

import pt.ipb.dsys.com.proverb.ProverbProtocol;

import java.rmi.RemoteException;

public class RemoteProverbImpl implements RemoteProverb {

    ProverbProtocol protocol = new ProverbProtocol();

    @Override
    public String processInput(String theInput) throws RemoteException {
        return protocol.processInput(theInput);
    }

}
