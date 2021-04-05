package pt.ipb.dsys.com.rmi.cpu.client;

import pt.ipb.dsys.com.rmi.cpu.common.CpuProvider;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CpuClientMain {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            CpuProvider remote = (CpuProvider) registry.lookup(CpuProvider.STUB_NAME);
            WordSort task = new WordSort("The quick brown fox jumps over the lazy dog");
            System.out.printf("[CpuClient] Sending request to server %s\n", args[0]);
            String result = remote.executeTask(task);
            System.out.printf("[CpuClient] Server responded: %s\n", result);
        } catch (Exception e) {
            System.err.printf("CpuClient exception: %s\n", e.getMessage());
            e.printStackTrace();
        }
    }
}
