import java.util.ArrayList;
public class Dealer {

	private final int STOPVALUE = 17; //dealern stannar på 17 eller över.
	
	int totalValue; //Totala värdet för korten.
	
	ArrayList <Integer> hand = new ArrayList<Integer>();
	

	public ArrayList<Integer> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Integer> hand) {
		this.hand = hand;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}

	public int getSTOPVALUE() {
		return STOPVALUE;
	}
	
}
