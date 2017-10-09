import java.util.Scanner;
import java.util.ArrayList;


public class BlackJackMain {

	public static void main(String[] args) {
	/**Developed 2017 by: 
	 * Moa Gardsäter
	 * Sara Svensson
	 * Aleksander Frostelen
	 * Jamien
	 * Steffo Dimfelt
	 * */

		//Hit Run and play
		BlackJackMain blackJack = new BlackJackMain();
		blackJack.table();

	}

	private Deck deck = new Deck();
	private Scanner scan = new Scanner(System.in);
	private Player dealer = new Player ();
	private Player player = new Player();
	private Betting betting = new Betting();
	
	void table()
	{

		

		
		
//		String message = "* * * * * * * * * * * * * * \n* * B L A C K   J A C K * * \n* * * * * * * * * * * * * *";
//		vegasNeonSign(message, 25);
		
		//Spel setup
		boolean mainPlay = true;
		deck.resetAll(player,dealer);
		dealer.hand.get(0).add(new Integer(deck.randomCard (12)+1));//Sätter värde i elementetENDAST FÖR TEST
		dealer.hand.get(0).add(new Integer(deck.randomCard (12)+1));//Sätter värde i elementetENDAST FÖR TEST
//		player.hand.get(0).add(new Integer(deck.randomCard (12)+1));//Sätter värde i elementetENDAST FÖR TEST
//		player.hand.get(0).add(new Integer(deck.randomCard (12)+1));//Sätter värde i elementetENDAST FÖR TEST
		
		player.hand.get(0).add(new Integer(10));//Sätter värde i elementetENDAST FÖR TEST
		player.hand.get(0).add(new Integer(11));//Sätter värde i elementetENDAST FÖR TEST
//		player.hand.add(new ArrayList<Integer>());//Adderar första raden till player
//		player.hand.get(1).add(new Integer(10));//Sätter värde i elementetENDAST FÖR TEST
//		player.hand.get(1).add(new Integer(1));//Sätter värde i elementetENDAST FÖR TEST
		
//		System.out.println("spelarens hand "+player.hand.get(0).get(0)+" "+player.hand.get(0).get(1));
//		System.out.println("dealerns hand "+dealer.hand.get(0).get(0)+" "+dealer.hand.get(0).get(1));
		
		
		welcome();
		
		//Huvudloop för hela spelet.
		do {
			//Playerns tur.
			boolean playerHitNewCard=true;

			//Första betting loopen
			betting.bettingLoop();
			
			System.out.println("Dealerns kort är: "+deck.showFirstCard(dealer,0,0));
			System.out.println("Dina kort är: "+deck.showAllCards(player,0));
			System.out.println("Totalsumman för dina kort: "+deck.totalHandValue(player,0));
			
			betting.doubleUp(deck.totalHandValue(player,0),0);
			
			//BLIR PLAYER TJOCK SÅ FORLORAR DENNE AUTOMATISKT
			
			
			
//			do {
//				//Här ska in en "splitting"-loop som tar hand om index,kort istället för ett fast nummer.
//				
//				//splittning
//				
//				
//				//double
//					for (int playerElem=0;playerElem<player.hand.size();playerElem++)
//					{
//						if (player.hand.size()==1)
//						{
//							System.out.println("Dina kort är "+deck.showAllCards(player,playerElem));
//							System.out.println(" Totalt blir det "+deck.totalHandValue(player,playerElem)+".");
//						}else {
//							System.out.println("Korten på hand nummer "+(playerElem+1)+" är "+deck.showAllCards(player,playerElem));
//							System.out.println("Totalt blir det "+deck.totalHandValue(player,playerElem)+".");
//						}
//						
//					}
//					
//				
//				
//				//
//			}while(xx);
			
			//Players val
			do {

				
				

				
				
				//Hit or stay loop
				playerHitNewCard=false;//ENDAST FÖR TEST
			} while (playerHitNewCard==true);
			
			//Dealerns tur
			System.out.println("Dealerns visar sitt dolda kort: "+deck.showFirstCard(dealer,0,1));
			System.out.println("Dealerns hand är: "+deck.showAllCards(dealer,0));

			while(deck.totalHandValue(dealer,0)<=16)
			{
				int tempRandom = new Integer(deck.randomCard (12)+1);
				dealer.hand.get(0).add(tempRandom);
				System.out.println("Dealern drar: "+tempRandom);
			}

			if (dealer.hand.size()>=1)
			{
				System.out.println("Dealern alla kort: "+deck.showAllCards(dealer,0));
				System.out.println("Dealern stannar på "+deck.totalHandValue(dealer,0)+".");
			}else {
				System.out.println("Dealerns totalsumma är "+deck.totalHandValue(dealer,0)+".");
				System.out.println("Dealern stannar.");
			}
			
			//Utvärdera vem som vunnit. 
			printWinner();

			System.out.println("Vill du fortsätta spela? Ja eller Nej");
			String scannerAnswer = scan.next().toLowerCase();
			mainPlay = betting.yesOrNo(scannerAnswer, "Vill du fortsätta spela? Ja eller Nej");

		} while (mainPlay == true);
		
		//Avslutning
		System.out.println("Tack för att du ville spela en stund.");
	}

	//Metoder för Table. -------------------------
	
	int getTotalValue(Player playerObj) {return playerObj.getTotalValue();}//Hämtar det total värdet av en hand.
	

	
	void printWinner()
	{
		if (deck.totalHandValue(dealer,0)>21&&deck.totalHandValue(player,0)<=21) 
		{
			System.out.println("Du vann handen.");
			if (deck.totalHandValue(player,0)==21)
			{
				
			}else {
				
			}
		}		
		
		
		
		
//		if (deck.totalHandValue(dealer,0)>deck.totalHandValue(player,0)) 
//		{System.out.println("Du förlorade handen.");}
//		
//		if (deck.totalHandValue(player,0)>21) 
//		{System.out.println("Du förlorade handen.");}
//		
//		if (deck.totalHandValue(dealer,0)==deck.totalHandValue(player,0)) 
//		{
//			if (deck.totalHandValue(dealer,0)>=17&&deck.totalHandValue(dealer,0)<21)
//				
//		}

		
		
		//vinst ger 1x pengar. Alltså 10 man satsade + 10
		//Har man 21 och vinner får man 1.5x peng. Alltså 10 man satsade + 15
		//Har dealer och playern lika mellan 17-21 får man tillbka instatsen.  
		
//		if (evaluateHands()==true)
//		{
//			System.out.println("Du vann handen.");
//			String message = "* * * * * * * * * * * \n* * D U   V A N N * * \n* * * * * * * * * * *";
//			vegasNeonSign(message, 15);
//			
//		}else {
//			System.out.println("Du förlorade handen.");
//		}
		
		
		
		
		
	}
	
	
	public void vegasNeonSign(String message, long millisPerChar)
    {
        for (int i = 0; i < message.length(); i++)
        {
            System.out.print(message.charAt(i));

            try
            {
                Thread.sleep(millisPerChar);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("");
    }
	
	public void welcome()
	{
		System.out.println("Välkommen till Black Jack.");
		System.out.println("För att vinna behöver summan av dina kort vara högre än Dealerns.");
		System.out.println("Den som får högst kort, upp till och med 21, vinner handen.");
		System.out.println("Lycka till!");
	}
	
}
