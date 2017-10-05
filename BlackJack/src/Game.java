import java.util.ArrayList;


public class Game {

	Deck deck = new Deck();
	Player dealer = new Player ();
	Player player = new Player();

	
	void mainLoop()
	{
		printTotalValue(player);
		printTotalValue(dealer);
	}
	
	
//	void showAllCards(Personer a)
//	{
//		System.out.println(a.getSTOPVALUE());
////		ArrayList <Integer> testHand = new ArrayList<Integer>();
////		dealer.setHand(12);
////		int testHand = dealer.getTotalValue();
////		System.out.println(testHand);
//	}
	
	
	
	
	public void printTotalValue(Player playerObj) 
	{
		System.out.println(playerObj.getTotalValue());
	}
	
	public void printPlayer(Player a) 
	{
		a.setTotalValue(55);
		System.out.println(a.getTotalValue());
	}
	

	
	
	
}
