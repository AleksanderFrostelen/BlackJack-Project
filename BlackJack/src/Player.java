import java.util.ArrayList;

public class Player {

	private final int STOPVALUE = 17; // dealern stannar på 17 eller över.
	private int totalValue; // Totala värdet för korten.
	protected ArrayList<ArrayList<Integer>>hand;
	protected ArrayList<ArrayList<String>> handCol;


	public Player() {
		super();
		totalValue=0;
		hand = new ArrayList<>();
		handCol = new ArrayList<>();
	}

	public int getSTOPVALUE() {
		return STOPVALUE;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
}