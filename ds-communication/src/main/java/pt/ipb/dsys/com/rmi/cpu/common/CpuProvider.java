package pt.ipb.dsys.com.rmi.cpu.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CpuProvider extends Remote {

    String STUB_NAME = "CpuProvider";

    <T> T executeTask(CpuTask<T> t) throws RemoteException;
}
