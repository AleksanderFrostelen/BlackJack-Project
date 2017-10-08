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
		System.out.println(dealer.hand.size());
		dealer.hand.add(new ArrayList<Integer>());
		System.out.println(dealer.hand.size());
		
	//	dealer.hand.get(0).add(2);//ENDAST FÖR TEST
//		player.hand.add(44);	//ENDAST FÖR TEST
//		player.hand.add(234);	//ENDAST FÖR TEST
	//	player.hand.add(new ArrayList<Integer>());
//		player.setTotalValue(30);//ENDAST FÖR TEST
//		dealer.setTotalValue(20);//ENDAST FÖR TEST
		

		
		
//		String message = "* * * * * * * * * * * * * * \n* * B L A C K   J A C K * * \n* * * * * * * * * * * * * *";
//		vegasNeonSign(message, 25);

		boolean mainPlay = true;
		
		//Huvudloop för hela spelet.
		do {
			
			//Playerns tur.
			boolean playerHitNewCard=true;
			
			welcome();
			betting.bettingLoop();

			
			do {
				System.out.println("Dina kort är "+deck.showAllCards(player)+" Totalt blir det "+getTotalValue(player));
				System.out.println("Dealerns kort är "+deck.showFirstCard(dealer,0,0));
				playerHitNewCard=false;//ENDAST FÖR TEST
			} while (playerHitNewCard==true);
			
			//Dealerns tur
			boolean dealerHitNewCard=true;
			do {
				dealerHitNewCard=false;//ENDAST FÖR TEST
			} while (dealerHitNewCard==true);
			
			//Utvärdera vem som vunnit. 
			printWinner();
			System.out.println(deck.showFirstCard(player,0,0));
			System.out.println("Vill du fortsätta spela? Ja eller Nej");
			String scannerAnswer = scan.next().toLowerCase();
			//Vill spelaren fortsätta. 
		//	mainPlay = scanNewGame(scannerAnswer);
			mainPlay = yesOrNo(scannerAnswer, "Vill du fortsätta spela? Ja eller Nej");

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
			String message = "* * * * * * * * * * * \n* * D U   V A N N * * \n* * * * * * * * * * *";
			vegasNeonSign(message, 15);
			
		}else {
			System.out.println("Du förlorade.");
		}
	}

	boolean yesOrNo(String answerIn, String printOut)
	{
		boolean returnAnswer=true;
		boolean fetchReturnAnswer=false;
		
		System.out.println(printOut);
		do {
			switch (answerIn)
			{
				case "j": case "ja": returnAnswer=true;fetchReturnAnswer=true;break;
				case "n": case "nej": returnAnswer=false;fetchReturnAnswer=true;break;
				default: System.out.println(printOut);fetchReturnAnswer=false;break;
			}
		} while (fetchReturnAnswer==false);
		return returnAnswer;
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
