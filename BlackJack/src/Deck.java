import java.text.Format;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;

public class Deck 
{

	ArrayList<String> name = new ArrayList<String>(
			Arrays.asList("Kung", "Knekt", "Dam", "Ess", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
	ArrayList<String> suit = new ArrayList<String>(Arrays.asList("Spader", "Hjärter", "Ruter", "Klöver"));
	ArrayList<String> shoeCol = new ArrayList<String>();
	{
		for(int x=0; x<suit.size();x++) 
		{
			for (int xx = 0; xx < name.size(); xx++) 
			{
				shoeCol.add(suit.get(x) + "." + name.get(xx));
				
			}
		}
}
	private ArrayList<String> reload = new ArrayList<>();
	
	//private final int[] VALUESPAN = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 11 };
	private final int[] VALUESPAN = { 11,11,11,11,11,11,11,11,11,11,11,11,11 };
	private int[] deckSetup = new int[52];
	public LinkedList<Integer> shoe = new LinkedList<>();//ska var private
	private Scanner input = new Scanner(System.in);

	public int[] getDeckSetup() 
	{
		return deckSetup;
	}

	public void setDeckSetup(int[] deckSetup) 
	{
		this.deckSetup = deckSetup;
	}

	public LinkedList<Integer> getShoe() 
	{
		return shoe;
	}

	public void setShoe(LinkedList<Integer> shoe) 
	{
		this.shoe = shoe;
	}

	// Lägger upp alla kort i grundleken deckSetup
	void deckSetup() 
	{
		for (int VALUESPANElem = 0; VALUESPANElem < deckSetup.length; VALUESPANElem = VALUESPANElem + VALUESPAN.length)// Loopar
																														// igenom
																														// hela
																														// array
																														// deckSpan
																														// med
																														// repeat
																														// VALUESPAN.length
		{
			System.arraycopy(VALUESPAN, 0, deckSetup, VALUESPANElem, VALUESPAN.length);
		} // Kopierar hela array VALUESPAN till array deckSpan
	}

	// Blandar en ny lek att spela med.
	void shuffle() 
	{
		System.out.println("Shuffle");

		System.out.println("Shoe: "+shoe.size());
		
		for (int LinkedElem = 0; LinkedElem < deckSetup.length; LinkedElem++) 
		{
			shoe.add(deckSetup[LinkedElem]);
		}
	}

	// Returnerar ett slumpmässigt nummer
	int randomCard(int randomIn) 
	{
		int randomOut = (int) (Math.random() * randomIn);
		return randomOut;
	}

	// Skriver ut värdet i LinkedList shoe från inkommande attribut
	int shoeValue(int cardNmbIn) 
	{
		return shoe.get(cardNmbIn);
	}

	public void resetAll(Player playerObj, Player dealerObj) // shoe ska laddas om till deckSetup och båda händerna ska
																// nollas.
	{
		playerObj.hand.clear();
		playerObj.hand.clear();
		shoe.clear();
		playerObj.hand.add(new ArrayList<Integer>());// Adderar första raden till player
		dealerObj.hand.add(new ArrayList<Integer>());// Adderar första raden till dealer
		shuffle();
	}

	public void dealRandomCards(int nr, Player playObj) // den ska använda randomCard och ger kort till player och
														// dealer
	{
		int randomCard=shoe.get(randomCard(shoe.size()));
		playObj.hand.get(nr).add(new Integer(randomCard));
		
		//REMOVE IN HÄR
		removeCards(randomCard);
		System.out.println("Colors REMOVE IN HÄR:");
	}

	String showAllCards(Player playerObj, int handIndex)// Returnerar en sträng med valörerna på alla korten på vald
														// hand.
	{
		String tempString = "";
		for (int handElem = 0; handElem < playerObj.hand.get(handIndex).size(); handElem++) 
		{
			tempString += playerObj.hand.get(handIndex).get(handElem).toString() + " ";
		}
		return tempString;
	}

	int showOneCard(Player playerObj, int handIndex, int cardIndex)// Returnerar valören på det första kortet på hand.
	{
		return playerObj.hand.get(handIndex).get(cardIndex);
	}

	int totalHandValue(Player playerObj, int handIndex)// Returnerar totalen för en hand.
	{
		int handTotal = 0;
		for (int handElement = 0; handElement < playerObj.hand.get(handIndex).size(); handElement++) 
		{
			int oneElement = playerObj.hand.get(handIndex).get(handElement);
			if (oneElement > 11) {
				oneElement = 10;
			}
			handTotal = handTotal + oneElement;
		}
		return handTotal;
	}

	public void aceDecision(Player playerObj) {
		int ace;
		for (int i = 0; i < playerObj.hand.size(); i++) {
			for (int ii = 0; ii < playerObj.hand.get(i).size(); ii++) {
				
				if (playerObj.hand.get(i).get(ii).equals(11) || playerObj.hand.get(i).get(ii).equals(1)) {
					System.out.println("Det finns ett ess i din hand. Ska esset vara 1 eller 11?");
					boolean gotADecision=false;
					do {
						ace = input.nextInt();
						if (ace == 1) {
							playerObj.hand.get(i).set(ii, ace);
							gotADecision=true;
						} else if (ace == 11) {
							playerObj.hand.get(i).set(ii, ace);
							gotADecision=true;
						} else {
							System.out.println("Du kan endast välja 1 eller 11.");
						}
					} while (gotADecision==false);
				}
			}
		}
	}

	

   
	 public void dAceDecision(Player dealerObj)
		{
		 System.out.println(dealerObj.hand.get(0));
	    	for(int i = 0; i < dealerObj.hand.size(); i++) 
	    	{ 
	    		if (totalHandValue(dealerObj, i) > 10) 
	    			
	    		{
	    			for(int ii = 0; ii < dealerObj.hand.get(i).size(); ii++) 
	    			{
	    				dealerObj.hand.get(0).set(i, 1)	;
	    				System.out.println(dealerObj.hand.get(0));
	    			}
	    			
	    		}
	    		else 
	    		{
	    			for(int ii = 0; ii < dealerObj.hand.get(i).size(); ii++) 
	    			{
	    				dealerObj.hand.get(0).set(ii, 11);
	    			}
	    		}
	    	}
	    	System.out.println(dealerObj.hand.get(0));
	    	 }
	
	void removeCards(int indexNmb)
	{
		shoe.remove(indexNmb);
	}
	    	
		

	
	
}
