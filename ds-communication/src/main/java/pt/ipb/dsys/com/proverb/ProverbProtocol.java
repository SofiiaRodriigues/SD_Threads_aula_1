package pt.ipb.dsys.com.proverb;

public class ProverbProtocol {
	private static final int WAITING = 0;
	private static final int SENTPRONTO = 1;
	private static final int SENTCLUE = 2;
	private static final int ANOTHER = 3;

	private static final int NUMPROVERBS = 10;

	private int state = WAITING;
	private int currentProverb = 0;

	private String[] clues = { "At night all the cats", "Together", "Better late", "Tell me and I forget. Teach me and I remember.", "Live long" };

	private String[] answers = { "are gray.", "we're stronger.", "than sorry.", "Involve me and I learn.", "and prosper." };

	public String processInput(String theInput) {
		String theOutput = null;
		if(theInput==null) {
			theInput = "";
		}
		if (state == WAITING) {
			theOutput = "Are you ready?";
			state = SENTPRONTO;
		} else if (state == SENTPRONTO) {
			if (theInput.equalsIgnoreCase("Ready")) {
				theOutput = clues[currentProverb];
				state = SENTCLUE;
			} else {
				theOutput = "It is supposed to say \"Ready\"! " + "Try again. Are you ready?";
			}
		} else if (state == SENTCLUE) {
			if (theInput.equalsIgnoreCase(answers[currentProverb])) {
				theOutput = clues[currentProverb] + " " + answers[currentProverb] + "! One more? (y/n)";
				state = ANOTHER;
			} else {
				theOutput = "It is supposed to say \"" + answers[currentProverb] + "\""
						+ "! Try again. Are you ready?";
				state = SENTPRONTO;
			}
		} else if (state == ANOTHER) {
			if (theInput.equalsIgnoreCase("y")) {
				theOutput = "Are you ready?";
				if (currentProverb == (NUMPROVERBS - 1))
					currentProverb = 0;
				else
					currentProverb++;
				state = SENTPRONTO;
			} else {
				theOutput = "Bye.";
				state = WAITING;
			}
		}

		return theOutput;
	}
}
