package pt.ipb.dsys.com.rmi.cpu.provider;


import pt.ipb.dsys.com.rmi.cpu.common.CpuProvider;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class CpuProviderMain {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        try {
            CpuProvider engine = new CpuEngine();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(CpuProvider.STUB_NAME, engine);
            System.out.printf("%s bound.\n", CpuProvider.STUB_NAME);
        } catch (Exception e) {
            System.err.printf("CpuProvider exception: %s\n", e.getMessage());
        }
    }
}
