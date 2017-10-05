import java.util.LinkedList;
import java.util.Arrays;

public class Deck {
	final int[] VALUESPAN = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	int[] deckSetup = new int [52];
	LinkedList<Integer> shoe = new LinkedList<>();
	
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
	void printOutShoeCard(int cardNmbIn)
	{
		System.out.println(shoe.get(cardNmbIn).toString());
	}
	
	public void resetAll() //shoe ska laddas om till deckSetup och båda händerna ska nollas.
	{
		
	}
	
}