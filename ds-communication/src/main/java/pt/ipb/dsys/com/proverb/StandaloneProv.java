package pt.ipb.dsys.com.proverb;

import javax.swing.*;

public class StandaloneProv {

    public static void main(String[] args) {

        ProverbProtocol pp = new ProverbProtocol();
        System.out.println(pp);
        String fromServer;
        String fromUser = "";

        while ((fromServer = pp.processInput(fromUser)) != null) {
            if (fromServer.equals("Bye."))
                break;
            fromUser = JOptionPane.showInputDialog(fromServer);

        }
    }
}
