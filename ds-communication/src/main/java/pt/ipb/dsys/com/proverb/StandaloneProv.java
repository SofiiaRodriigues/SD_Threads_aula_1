package pt.ipb.dsys.com.proverb;

import javax.swing.JOptionPane;

public class StandaloneProv {

	public static void main(String[] args) {

		ProverbProtocol pp = new ProverbProtocol();
		System.out.println(pp);
		String fromServer = pp.processInput("");
		String fromUser = JOptionPane.showInputDialog(fromServer);

		while ((fromServer = pp.processInput(fromUser)) != null) {
			if (fromServer.equals("Bye."))
				break;
			fromUser = JOptionPane.showInputDialog(fromServer);

		}
	}
}
