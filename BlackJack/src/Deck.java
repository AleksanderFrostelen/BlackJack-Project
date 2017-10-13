import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Arrays;

public class Deck 
{
	//private final int[] VALUESPAN = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 11 };
	private final int[] VALUESPAN = { 11,11,11,11,11,11,11,11,11,11,11,11,11 };
	private int[] deckSetup;
	private LinkedList<Integer> shoe;
	private ArrayList<String> shoeCol;
	private ArrayList<String> name;
	private ArrayList<String> suit;
	
	private Scanner input;

	public Deck() {
		super();
		deckSetup = new int[52];
		shoe = new LinkedList<>();
		shoeCol = new ArrayList<>();
		name = new ArrayList<String>(Arrays.asList( "2", "3", "4", "5", "6", "7", "8", "9", "10","Knekt", "Dam","Kung","Ess"));
		suit = new ArrayList<String>(Arrays.asList("Spader", "Hjärter", "Ruter", "Klöver"));
		input = new Scanner(System.in);
	}

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
		// Loopar igenom hela array deckSpan med repeat VALUESPAN.length
		for (int VALUESPANElem = 0; VALUESPANElem < deckSetup.length; VALUESPANElem = VALUESPANElem + VALUESPAN.length)
		{
			System.arraycopy(VALUESPAN, 0, deckSetup, VALUESPANElem, VALUESPAN.length);
		} // Kopierar hela array VALUESPAN till array deckSpan
	}

	// Blandar en ny lek att spela med.
	void shuffle() 
	{
		for (int LinkedElem = 0; LinkedElem < deckSetup.length; LinkedElem++) 
		{shoe.add(deckSetup[LinkedElem]);}

	
			for(int x=0; x<suit.size();x++) 
			{
				for (int xx = 0; xx < name.size(); xx++) 
				{
					shoeCol.add(suit.get(x) + " " + name.get(xx));
				}
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

	public void resetAll(Player playerObj, Player dealerObj) // shoe ska laddas om till deckSetup och båda händerna ska nollas.
	{
		shoe.clear();
		shoeCol.clear();
		playerObj.hand.clear();
		dealerObj.hand.clear();
		playerObj.handCol.clear();
		dealerObj.handCol.clear();
		playerObj.hand.add(new ArrayList<Integer>());// Adderar första raden till player
		dealerObj.hand.add(new ArrayList<Integer>());// Adderar första raden till dealer
		playerObj.handCol.add(new ArrayList<String>());// Adderar första raden till player, färger string
		dealerObj.handCol.add(new ArrayList<String>());// Adderar första raden till dealer, färger string
		shuffle();
	}
 
	public void dealRandomCards(int nr, Player playObj) // den ska använda randomCard och ger kort till player och dealer
	{
		//int randomCard=shoe.get(randomCard(shoe.size()));	
		int randomCard=randomCard(shoe.size());	
		
		System.out.println("randomCard"+randomCard);
		String randomCardCol=shoeCol.get(randomCard);		
		playObj.hand.get(nr).add(new Integer(shoe.get(randomCard)));
		playObj.handCol.get(nr).add(new String(randomCardCol));
		removeCards(randomCard);
	}

	String showAllCards(Player playerObj, int handIndex)// Returnerar en sträng med valörerna på alla korten på vald hand.
	{	
		String tempString = "";
		for (int handElem = 0; handElem < playerObj.hand.get(handIndex).size(); handElem++) 
		{
			if (handElem==playerObj.hand.get(handIndex).size()-1)
			{
				tempString += playerObj.handCol.get(handIndex).get(handElem).toString();
			}else {
				tempString += playerObj.handCol.get(handIndex).get(handElem).toString() + ", ";
			}
		}
		return tempString;
	}

	String showOneCard(Player playerObj, int handIndex, int cardIndex)// Returnerar valören på det första kortet på hand.
	{
		return playerObj.handCol.get(handIndex).get(cardIndex);
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
					System.out.println("Det finns ett ess i din hand.\nSka esset vara 1 eller 11?");
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
						System.out.println("Totalsumman för dina kort: " + totalHandValue(playerObj, 0)+"\n");
					} while (gotADecision==false);
				}
			}
		}
	}
   
	 public void dAceDecision(Player dealerObj)
		{
	    	for(int i = 0; i < dealerObj.hand.size(); i++) 
	    	{ 
	    		while (totalHandValue(dealerObj, i) > 21) 
	    		{
	    			int found = dealerObj.hand.get(i).indexOf(11);
	    			int num = dealerObj.hand.get(i).get(found);
	    			if (num == 11) 
	    			{
	    				dealerObj.hand.get(i).set(i, 1);
	    			}
	    		}
	    	 }
	   }
	
	void removeCards(int indexNmb)
	{
		shoe.remove(indexNmb);
		shoeCol.remove(indexNmb);
	}
	    	
}
