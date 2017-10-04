import java.util.LinkedList;

public class Deck {
	

	
	int [] valueSpan = {1,2,3,4,5,6,7,8,9,10,10,10,10,11};
	
	int [] deckSetup = new int [52];
	
	LinkedList<Integer> shoe = new LinkedList<Integer>();
	
	public void removeCard() //Denna metod tar ut de slumpade korten ur shoe så det inte kan användas igen.
	{
		shoe.remove(Math.random())
		
	}
	
	public int randomCard() // Denna ska ge slumpässigt kort från shoe
	{
		
		
	}
	
	
	

}
