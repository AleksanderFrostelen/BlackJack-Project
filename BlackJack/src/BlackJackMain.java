import java.util.LinkedList;

public class BlackJackMain {

	public static void main(String[] args) {
		
		//Testar shuffle-metoden
		Deck deckTest = new Deck();	
		deckTest.deckSetup();
		deckTest.shuffle();
		
		//Testar att slumpmässigt ta ut ett kort från shoe, skriv ut positionen och kortvalör
		int rndCard1 = deckTest.randomCard (deckTest.getShoe().size());		
		System.out.println(rndCard1);		
		int printOutCard = deckTest.shoeValue(rndCard1);
		
	}

}
