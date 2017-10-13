import java.util.ArrayList;

public class Player {

	private final int STOPVALUE = 17; // dealern stannar på 17 eller över.
	protected ArrayList<ArrayList<Integer>>hand;
	protected ArrayList<ArrayList<String>> handCol;


	public Player() {
		super();
		hand = new ArrayList<>();
		handCol = new ArrayList<>();
	}

	public int getSTOPVALUE() {
		return STOPVALUE;
	}

	
}