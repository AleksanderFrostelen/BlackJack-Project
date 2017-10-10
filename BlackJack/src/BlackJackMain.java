import java.util.Scanner;

public class BlackJackMain {

	public static void main(String[] args) {
		/**
		 * Developed 2017 by: Moa Gardsäter Sara Svensson Aleksander Frostelen Jamien
		 * Steffo Dimfelt
		 */

		// Hit Run and play
		BlackJackMain blackJack = new BlackJackMain();
		blackJack.table();

	}

	private Deck deck = new Deck();
	private Scanner scan = new Scanner(System.in);
	private Player dealer = new Player();
	private Player player = new Player();

	void table()
	{
		dealer.hand.add(1);//ENDAST FÖR TEST
		player.hand.add(44);	//ENDAST FÖR TEST
		player.hand.add(234);	//ENDAST FÖR TEST
		player.setTotalValue(20);//ENDAST FÖR TEST
		dealer.setTotalValue(20);//ENDAST FÖR TEST

		boolean mainPlay = true;
		
		//Huvudloop för hela spelet.
		do {
			
			//Playerns tur.
			boolean playerHitNewCard=true;
			System.out.println("Vill du fortsätta \"Ja\" \"Nej\"");
			String scannerAnswer = scan.next().toLowerCase();
			boolean hitMe=hitOrStay(scannerAnswer);

			
			do {
				if (hitOrStay("Ja")) {
					player.hand.add(dealRandomCards());
					
				}else {

				System.out.println("Dina kort är "+deck.showAllCards(player)+" Totalt blir det "+getTotalValue(player));
				System.out.println("Dealerns kort är "+deck.showFirstCard(dealer));
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
			String scannerAnswerOne = scan.next().toLowerCase();
			//Vill spelaren fortsätta. 
			mainPlay = scanNewGame(scannerAnswerOne);
			if (scanNewGame("Ja")) {
				
			} else {

			

		} while (mainPlay == true);
		
		//Avslutning
	System.out.println("Tack för spelet."); 
			
		
	}

	// Metoder för Table.

	int getTotalValue(Player playerObj) {
		return playerObj.getTotalValue();
	}// Hämtar det total värdet av en hand.

	boolean evaluateHands(Player playerObj, Player dealerObj)// Avgör om Players total är högre än Dealerns.
	{
		boolean outcome = true;
		if (dealerObj.getTotalValue() >= playerObj.getTotalValue()) {
			outcome = false;
		}
		return outcome;
	}

	void printWinner() {
		if (evaluateHands(player, dealer) == true) {
			System.out.println("Du vann spelet.");
		} else {
			System.out.println("Du förlorade.");
		}
	}

	boolean scanNewGame(String answerIn) {
		boolean returnAnswer = true;
		boolean fetchReturnAnswer = false;

		do {
			switch (answerIn) {
			case "j":
			case "ja":
				returnAnswer = true;
				fetchReturnAnswer = true;
				break;
			case "n":
			case "nej":
				returnAnswer = false;
				fetchReturnAnswer = true;
				break;
			default:
				System.out.println(" Vill du fortsätta spela? Ja eller Nej");
				fetchReturnAnswer = false;
				break;
			}
		} while (fetchReturnAnswer == false);
		return returnAnswer;
	}

	boolean hitOrStay(String answerIn) {
		boolean returnAnswer = true;
		boolean fetchReturnAnswer = false;

		System.out.println("Vill du fortsätta spela? Ja eller Nej");
		do {
			switch (answerIn) {
			case "j":
			case "ja":
				returnAnswer = true;
				fetchReturnAnswer = true;
				break;
			case "n":
			case "nej":
				returnAnswer = false;
				fetchReturnAnswer = true;
				break;
			default:
				System.out.println("Vill du fortsätta spela? Ja eller Nej");
				fetchReturnAnswer = false;
				break;
			}
		} while (fetchReturnAnswer == false);
		return returnAnswer;
	}

}
