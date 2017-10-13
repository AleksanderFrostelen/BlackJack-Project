import java.util.ArrayList;

public class BlackJackMain {

	public static void main(String[] args) {

	/**Developed 2017 by: 
	 * Moa Gardsäter
	 * Sara Svensson
	 * Aleksander Frostelen
	 * Jaimen Lavalle
	 * Steffo Dimfelt
	 * */

		BlackJackMain blackJack = new BlackJackMain();
		blackJack.table();
	}

	private Deck deck;
	private Player dealer;
	private Player player;
	private Betting betting;

	public BlackJackMain() {
		super();
		deck = new Deck();
		dealer = new Player();
		player = new Player();
		betting = new Betting();
	}

	void table() {
		
		welcome();
		deck.deckSetup();

		// Huvudloop för hela spelet.
		boolean mainPlay = true;
		do {
			
			deck.resetAll(player, dealer);

			//Dela ut kort
			deck.dealRandomCards(0, player);
			deck.dealRandomCards(0, player);
			deck.dealRandomCards(0, dealer);
			deck.dealRandomCards(0, dealer);

			deck.dAceDecision(dealer);

			betting.bettingLoop();
			
			betting.overUnderMeth();
			System.out.println("");
			System.out.println("Dina kort är: " + deck.showAllCards(player, 0));
			System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, 0));
			betting.overUnderPay(player);
			System.out.println("Dealern visar sitt första kort: " + deck.showOneCard(dealer, 0, 0));
			System.out.println("");

			//Kör igenom ifall Player vill splitta kort.
			betting.doubleUp(deck.totalHandValue(player, 0), 0);//Checka om det finns dubbla kort.
			splitHands();
			
			deck.aceDecision(player);
			
			// Players val - Hit or stay
			hitOrStay ();
			
			boolean skipDealer = false;
			if (player.hand.size() == 1 && deck.totalHandValue(player, 0) > 21) {
				skipDealer = true;
			}

			// Dealerns tur
			if (skipDealer == false) {
				System.out.println("Dealerns visar sitt dolda kort: " + deck.showOneCard(dealer, 0, 1));
				System.out.println("Dealerns hand är: " + deck.showAllCards(dealer, 0));
				System.out.println("Dealerns totalsumma är " + deck.totalHandValue(dealer, 0) + ".");
				System.out.println("");

				boolean Dealerdesicion = true;
				while (Dealerdesicion==true) {

					if (deck.totalHandValue(dealer, 0)>=16)
					{
						Dealerdesicion = false;
					}
					else 
					{
						deck.dealRandomCards(0, dealer);
						System.out.println("Dealern drar ett nytt kort.");
						System.out.println("Dealerns hand är: " + deck.showAllCards(dealer, 0));
						System.out.println("Dealerns totalsumma är " + deck.totalHandValue(dealer, 0) + ".");
						System.out.println("");
					}					
					
				}
				
				if (deck.totalHandValue(dealer, 0) > 21) {
					System.out.println("Dealern blir tjock.");
				} else {
					System.out.println("Dealern stannar.");
				}
			}

			// Utvärdera vem som vunnit.
			
			printWinner();
			
			if (betting.getPlayerChips()==0)
			{
				System.out.println("");
				System.out.println("Dina pengar är slut. Tack för att du ville spela.");
				mainPlay = false;
			}else {
				System.out.println("");
				mainPlay = betting.yesOrNo("Vill du fortsätta spela? Ja eller Nej.");
			}


		} while (mainPlay == true);

		// Avslutning
		String messageOut = "* * * * * * * * * * * * * * *\n* * V I   S E S   I G E N * *\n* * * * * * * * * * * * * * *";
		vegasNeonSign(messageOut, 15);


	}

// Metoder för Table. -------------------------

	void printWinner() {
		for (int handIndex = 0; handIndex < player.hand.size(); handIndex++) {
			int formatHandNumber = handIndex + 1;
			// Formaterar outputstring i fall man har splittat.
			String handNumber = (player.hand.size() == 1) ? "Din hand" : "Hand " + formatHandNumber;

			if (deck.totalHandValue(dealer, 0) <= 21) {
				if (deck.totalHandValue(player, handIndex) <= 21) {
					if (deck.totalHandValue(player, handIndex) == deck.totalHandValue(dealer, 0)) {
						System.out.println("\n" + handNumber + " förlorade.");
						if (deck.totalHandValue(player, handIndex) >= 17) {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får behålla din insats.");
							
							betting.bettingPayBack(betting.getPlayerBetHandIndex(handIndex), 0);
						}
					} else if (deck.totalHandValue(player, handIndex) > deck.totalHandValue(dealer, 0)) {
						System.out.println(handNumber + " vann!");
						if (deck.totalHandValue(player, handIndex) == 21) {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 3x insatsen.");
							for (int bettingElem=0;bettingElem<betting.getPlayerBetSize();bettingElem++)
							{betting.bettingPayBack(bettingElem, 2);};
						} else {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 2x insatsen.");
							for (int bettingElem=0;bettingElem<betting.getPlayerBetSize();bettingElem++)
							{betting.bettingPayBack(bettingElem, 1);}
						}
					}
				} else {
					System.out.println(handNumber + " blev tjock. Du förlorar din insats.");
				}
			} else {
				if (deck.totalHandValue(player, handIndex) <= 21) {
					System.out.println("\n" + handNumber + " vann!");

					if (deck.totalHandValue(player, handIndex) == 21) {
						System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 3x insatsen.");
						for (int bettingElem=0;bettingElem<betting.getPlayerBetSize();bettingElem++){betting.bettingPayBack(bettingElem, 2);}
					} else {
						System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 2x insatsen.");
						for (int bettingElem=0;bettingElem<betting.getPlayerBetSize();bettingElem++){betting.bettingPayBack(bettingElem, 1);}
					}
				} else {
					System.out.println(handNumber + " blev tjock. Du förlorar din insats.");
				}
				
			}
		}
		System.out.println("Du har nu $" + betting.getPlayerChips() + ".");
	}

	public void vegasNeonSign(String message, long millisPerChar) {
		for (int i = 0; i < message.length(); i++) {
			System.out.print(message.charAt(i));

			try {
				Thread.sleep(millisPerChar);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
	}

	public void welcome() {

		String message = "* * * * * * * * * * * * *  \n* * B L A C K J A C K * * \n* * * * * * * * * * * * * ";
		vegasNeonSign(message, 25);
		System.out.println("Välkommen till Black Jack.");
		System.out.println("För att vinna behöver summan av dina kort vara högre än Dealerns.");
		System.out.println("Den som får högst kort, upp till och med 21, vinner handen.");
		System.out.println("Lycka till!\n");
	}
	
	public void hitOrStay ()
	{
		for (int i = 0; i < player.hand.size(); i++) {
					boolean fetchReturnAnswer = true;

					do {
						int tempNmb = i+1;
						String handNumber = (player.hand.size() == 1) ? "din hand" : "Hand " + tempNmb;
						boolean hitMe = betting.yesOrNo("Vill du ha ett nytt kort på "+handNumber+"? Ja eller Nej.");
						
						if (hitMe==true)
						{
							deck.dealRandomCards(i, player);
							deck.aceDecision(player);
							System.out.println("Dina kort är: " + deck.showAllCards(player, i));
							System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, i));
						}else 
						{
							fetchReturnAnswer=false;
						}
						
						if (deck.totalHandValue(player, 0) > 21) {
							
							if (hitMe==false)
							{
								System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, i));
							}
							fetchReturnAnswer=false;
						}
						
					} while (fetchReturnAnswer==true);
			
		}

	}
	
	public void splitHands()
	{
		boolean splitHands = false;
		boolean madeASplit = false;
		
		do {
			int checkedAllHands = 0;
			for (int handIndex = 0; handIndex < player.hand.size(); handIndex++) {

				if (player.hand.get(handIndex).get(0).equals(player.hand.get(handIndex).get(1))) 
				{
					if (betting.getPlayerChips() >= betting.getBettingValue()) 
					{
						System.out.println("Du har pengar till en split.");
						boolean splitCards = betting.yesOrNo("Vill du splitta dina kort? Ja eller Nej.");

						if (splitCards == true) 
						{
							player.hand.add(new ArrayList<Integer>());// Adderar ny rad till player
							player.handCol.add(new ArrayList<String>());// Adderar ny rad till player

							int tempElement = player.hand.get(handIndex).get(1);
							String tempElementCol = player.handCol.get(handIndex).get(1);
							player.hand.get(handIndex + 1).add(new Integer(tempElement));// Kopierar värde från gammal array till ny																								
							player.hand.get(handIndex).clear();// Nollställer Row
							player.hand.get(handIndex).add(new Integer(tempElement));// Kopierar tillbaka värdet till gammal array.
							
							player.handCol.get(handIndex + 1).add(new String(tempElementCol));// Kopierar värde från gammal array till ny																								
							player.handCol.get(handIndex).clear();// Nollställer Row
							player.handCol.get(handIndex).add(new String(tempElementCol));// Kopierar tillbaka värdet till gammal array.
							
							deck.dealRandomCards(handIndex, player);
							deck.dealRandomCards(handIndex+1, player);
							
							System.out.println("På hand nummer "+handIndex+" fick du: "+deck.showOneCard(player, handIndex+1, 1));
							
							betting.setPlayerChips(betting.getPlayerChips()-betting.getBettingValue());
							System.out.println("Du har $"+betting.getPlayerChips()+" kvar.\n");
							madeASplit = true;

						} else {
							checkedAllHands++;
						}
					} else {
						if(madeASplit)
						{
							System.out.println("Du har tyvärr inte insats nog till fler splittar.");
						}
						
						checkedAllHands++;
					}
				} else {
					checkedAllHands++;
				}				
			}			
			if (checkedAllHands == player.hand.size()) {splitHands = true;}

		} while (splitHands = false);
	}

}
