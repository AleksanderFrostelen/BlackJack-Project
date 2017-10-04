import java.util.LinkedList;

public class BlackJackMain {

	public static void main(String[] args) {
		
		//Testar shuffle-metoden
		Deck deckTest = new Deck();		
		deckTest.shuffle();
		
		//Testar att slumpmässigt ta ut ett kort från shoe, 
		int rndCard = deckTest.randomCard (deckTest.getShoe().size());
		System.out.println(rndCard);
		
	}

}
