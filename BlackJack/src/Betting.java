import java.util.ArrayList;

public class Betting {

	protected ArrayList <Integer> playerBet = new ArrayList<>();
	int playerChips = 00;

	int[] tempHand = {5,2};



	/**Har man inga pengar kan man inte betta
	 * har man pengar räknar man första handen hand[0]
	 * finns det fler händer
	 * @param betIn
	 * @param chipsIn
	 */
	
	
	
	//betting(playerBet,playerChips);



	//vinst ger 1x pengar. Alltså 10 man satsade + 10

	//Har man 21 och vinner får man 1.5x peng. Alltså 10 man satsade + 15

	//Har dealer och playern lika mellan 17-21 får man tillbka instatsen.  

	//Har spelaren 7-11 på grundgiven kan spelaren dubbla insatsen. 





	//felmeddelande om man bettar med för mycket pengar. 



	//är pengarna slut ska spelet sluta auto. ett felmeddelande att pengarna är slut. 
	
	  public static void betting(int betIn,int chipsIn) 

	  {

	   boolean bettingAgain=false;

	   do{

	     if (chipsIn>0)

	      {

	      System.out.println("Hur mycket vill du satsa?");

	      int tempSatsa = 10;//temp

	       

	       // en satsa loop

	      

	     

	      }else{

	      bettingAgain=false;

	      System.out.println("Pengarna är slut");

	      }

	     }while(bettingAgain==true);

	     System.out.println("Tack å hej");

	  }

	  

	  public static void doubleUp(int[] handIn)

	  {

	    //if handIn = 7-11 AND length =2;

	    //fråga om spelaren vill dubbla. 

	  }
}
