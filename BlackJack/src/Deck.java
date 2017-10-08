import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class Deck {
	
	private final int[] VALUESPAN = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	private int[] deckSetup = new int [52];
	private LinkedList<Integer> shoe = new LinkedList<>();
	
	public int[] getDeckSetup() {
		return deckSetup;
	}
	public void setDeckSetup(int[] deckSetup) {
		this.deckSetup = deckSetup;
	}
	public LinkedList<Integer> getShoe() {
		return shoe;
	}
	public void setShoe(LinkedList<Integer> shoe) {
		this.shoe = shoe;
	}

	
	//Lägger upp alla kort i grundleken deckSetup
	void deckSetup()
	{
		for (int VALUESPANElem=0;VALUESPANElem<deckSetup.length;VALUESPANElem=VALUESPANElem+VALUESPAN.length)//Loopar igenom hela array deckSpan med repeat VALUESPAN.length
		{
			System.arraycopy(VALUESPAN, 0, deckSetup, VALUESPANElem, VALUESPAN.length);//Kopierar hela array VALUESPAN till array deckSpan
		}
	}
	
	//Blandar en ny lek att spela med.
	void shuffle()
	{	
		for (int LinkedElem=0;LinkedElem<deckSetup.length;LinkedElem++)
		{
			shoe.add(deckSetup[LinkedElem]);//Lägger in varje element från array deckSetup till LinkedList shoe
		}
		
	}
	
	//Returnerar ett slumpmässigt nummer
	int randomCard (int randomIn)
	{
		int randomOut = (int)(Math.random()*randomIn);
		return randomOut;
	}
	
	//Skriver ut värdet i LinkedList shoe från inkommande attribut
	int shoeValue(int cardNmbIn)
	{
		//System.out.println(shoe.get(cardNmbIn).toString());
		return shoe.get(cardNmbIn);
	}
	
	public void resetAll(Player playerObj,Player dealerObj) //shoe ska laddas om till deckSetup och båda händerna ska nollas.
	{
		playerObj.hand.clear();
		playerObj.hand.clear();
		shoe.clear();
		playerObj.hand.add(new ArrayList<Integer>());//Adderar ett element till player
		dealerObj.hand.add(new ArrayList<Integer>());//Adderar ett element till dealer
		shuffle();
	}
	
	public void dealRandomCards() //den ska använda randomCard och ger kort till player och dealer
	{

	}
	
	public void removeCard(Object object) //den ska använda randomCard och ger kort till player och dealer
	{

	}
	
	String showAllCards(Player playerObj,int handIndex)//Returnerar en sträng med valörerna på alla korten på vald hand. 
	{
		String tempString="";
		for(int handElem=0;handElem<playerObj.hand.get(handIndex).size();handElem++)
		{tempString += playerObj.hand.get(handIndex).get(handElem).toString()+" ";}
		return tempString;
	}
	
	int showFirstCard(Player playerObj,int handIndex, int cardIndex)//Returnerar valören på det första kortet på hand.
	{return playerObj.hand.get(handIndex).get(cardIndex);}
	
	int totalHandValue(Player playerObj,int handIndex)//Returnerar totalen för en hand.
	{
		int handTotal = playerObj.hand.get(handIndex).stream().mapToInt(Integer::intValue).sum();
		return handTotal;
	}
}