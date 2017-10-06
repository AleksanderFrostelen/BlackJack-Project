import java.util.ArrayList;

     public class Player {
    	 private int totalValue; //Totala värdet för korten.
     private final int STOPVALUE = 17; //dealern stannar på 17 eller över.
     protected ArrayList <Integer> hand = new ArrayList<>();
     
	public ArrayList<Integer> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Integer> hand) {
		this.hand = hand;
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
