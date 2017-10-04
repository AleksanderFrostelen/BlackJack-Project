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
	
	//Lägger upp alla kort 
	void shuffle()
	{
		for (int VALUESPANElem=0;VALUESPANElem<deckSetup.length;VALUESPANElem=VALUESPANElem+VALUESPAN.length)//Loopar igenom hela array deckSpan med repeat VALUESPAN.length
		{
			System.arraycopy(VALUESPAN, 0, deckSetup, VALUESPANElem, VALUESPAN.length);//Kopierar hela array VALUESPAN till array deckSpan
		}
		
		for (int LinkedElem=0;LinkedElem<deckSetup.length;LinkedElem++)
		{
			shoe.add(deckSetup[LinkedElem]);//Lägger in varje element från array deckSetup till LinkedList shoe
		}
	}
	
	//Returnerar ett slumpmässigt kort från array shoe
	int randomCard (int randomIn)
	{
		int randomOut = (int)(Math.random()*randomIn);
		return randomOut;
	}
	
}