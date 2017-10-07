import java.util.Scanner;

public class BlackJackMain {

	public static void main(String[] args) {
	/**Developed 2017 by: 
	 * Moa Gardsäter
	 * Sara Svensson
	 * Aleksander Frostelen
	 * Jaimen Lavalle
	 * Steffo Dimfelt
	 * */

		//Hit Run and play
		BlackJackMain blackJack = new BlackJackMain();
		blackJack.table();

	}
	private Scanner scan = new Scanner(System.in);
	private Deck deck = new Deck();
	private Player dealer = new Player ();
	private Player player = new Player();
	private Betting betting = new Betting();
	
	void table()
	{
		dealer.hand.add(1);//ENDAST FÖR TEST
		player.hand.add(44);	//ENDAST FÖR TEST
		player.hand.add(234);	//ENDAST FÖR TEST
		player.setTotalValue(20);//ENDAST FÖR TEST
		dealer.setTotalValue(20);//ENDAST FÖR TEST
		
		String message = "* * * * * * * * * * * * * * \n* * B L A C K   J A C K * * \n* * * * * * * * * * * * * *";
		vegasNeonSign(message, 25);
		
		//Setup table
		boolean mainPlay = true;

		System.out.println(player.hand.get(0));
		deck.resetAll(player,dealer,betting);
	
		
		//Huvudloop för hela spelet.
		do {
			
			//Playerns tur.
			boolean playerHitNewCard=true;
			do {
				player.hand.add(44);	//ENDAST FÖR TEST
				dealer.hand.add(1);//ENDAST FÖR TEST
				System.out.println("Dealerns total: "+dealer.getTotalValue());
				System.out.println("Player total: "+player.getTotalValue());
//				System.out.println("Dina kort är "+deck.showAllCards(player)+" Totalt blir det "+getTotalValue(player));
//				System.out.println("Dealerns kort är "+deck.showFirstCard(dealer));
				playerHitNewCard=false;//ENDAST FÖR TEST
			} while (playerHitNewCard==true);
			
			//Dealerns tur
			boolean dealerHitNewCard=true;
			do {
				
				dealerHitNewCard=false;//ENDAST FÖR TEST
			} while (dealerHitNewCard==true);
			
			//Utvärdera vem som vunnit. 
			printWinner();
			System.out.println(deck.showFirstCard(player));
			System.out.println("Vill du fortsätta spela? Ja eller Nej");
			String scannerAnswer = scan.next().toLowerCase();
			//Vill spelaren fortsätta. 
			mainPlay = scanNewGame(scannerAnswer);

		} while (mainPlay == true);
		
		//Avslutning
		System.out.println("Tack för spelet.");
	}

	//Metoder för Table.
	
	int getTotalValue(Player playerObj) {return playerObj.getTotalValue();}//Hämtar det total värdet av en hand.
	
	boolean evaluateHands(Player playerObj, Player dealerObj)//Avgör om Players total är högre än Dealerns.
	{
		boolean outcome = true;		
		if (dealerObj.getTotalValue()>=playerObj.getTotalValue()){outcome=false;}		
		return outcome;
	}
	
	void printWinner()
	{
		if (evaluateHands(player,dealer)==true)
		{
			System.out.println("Du vann spelet.");
		}else {
			System.out.println("Du förlorade.");
		}
	}
	
	boolean scanNewGame(String answerIn)
	{
		boolean returnAnswer=true;
		boolean fetchReturnAnswer=false;

		do {
			switch (answerIn)
			{
				case "j": case "ja": returnAnswer=true;fetchReturnAnswer=true;break;
				case "n": case "nej": returnAnswer=false;fetchReturnAnswer=true;break;
				default: System.out.println("2 Vill du fortsätta spela? Ja eller Nej");fetchReturnAnswer=false;break;
			}
		} while (fetchReturnAnswer==false);
		return returnAnswer;
	}
	
	boolean hitOrStay(String answerIn)
	{
		boolean returnAnswer=true;
		boolean fetchReturnAnswer=false;
		
		System.out.println("Vill du fortsätta spela? Ja eller Nej");
		do {
			switch (answerIn)
			{
				case "j": case "ja": returnAnswer=true;fetchReturnAnswer=true;break;
				case "n": case "nej": returnAnswer=false;fetchReturnAnswer=true;break;
				default: System.out.println("Vill du fortsätta spela? Ja eller Nej");fetchReturnAnswer=false;break;
			}
		} while (fetchReturnAnswer==false);
		return returnAnswer;
	}
	
	public static void vegasNeonSign(String message, long millisPerChar)
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
}
