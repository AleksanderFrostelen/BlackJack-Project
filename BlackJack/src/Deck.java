import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Arrays;

public class Deck {
	
	Dealer dealer = new Dealer ();
	Player player = new Player();
	
	final int[] VALUESPAN = {2,3,4,5,6,7,8,9,10,10,10,10,11};
	int[] deckSetup = new int [52];
	LinkedList<Integer> shoe = new LinkedList<>();
	ArrayList<Integer> shoe2 = new ArrayList<>();
	
	
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
	void shoeValue(int cardNmbIn)
	{
		//System.out.println(shoe.get(cardNmbIn).toString());
		shoe.get(cardNmbIn);
//		System.out.println("test");
	}
	
	public void resetAll() //shoe ska laddas om till deckSetup och båda händerna ska nollas.
	{
		shoe2.add(1);
		shoe2.add(1);
		System.out.println("Bacon "+shoe2.size());
//		player.hand.clear();
//		dealer.hand.clear();
		shoe2.clear();
		System.out.println("Bacon "+shoe2.size());
		shuffle();
		
	}

	
/*	public void dealRandomCards() //den ska använda randomCard och ger kort till player och dealer
	{
		Player player = new Player();
		Player.setTotalValue(randomCard(shoe));
		
		Dealer dealer = new Dealer ();
		dealer.setTotalValue(randomCard(shoe));*/
	}

