package pt.ipb.dsys.com.rmi.cpu.provider;

import pt.ipb.dsys.com.rmi.cpu.common.CpuProvider;
import pt.ipb.dsys.com.rmi.cpu.common.CpuTask;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class CpuEngine extends UnicastRemoteObject implements CpuProvider {

    public CpuEngine() throws RemoteException {
        super();
    }

    public <T> T executeTask(CpuTask<T> t) {
        System.out.printf("[CpuEngine] Executing task %s\n", t.getClass());
        T result = t.process();
        System.out.printf("[CpuEngine] Task execution returned: %s\n", result);
        return result;
    }

}
