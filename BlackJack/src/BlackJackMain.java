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

		// Huvudloop för hela spelet.
		boolean mainPlay = true;
		do {
			deck.deckSetup();
			deck.resetAll(player, dealer);

			System.out.println("Shoesize:"+deck.shoe.size());
			
			//Dela ut kort
			deck.dealRandomCards(0, player);
			deck.dealRandomCards(0, player);
			deck.dealRandomCards(0, dealer);
			deck.dealRandomCards(0, dealer);
			
			deck.dAceDecision(dealer);

			// Playerns tur.
			betting.bettingLoop();
			
			System.out.println("Dealerns öppna kort är: " + deck.showOneCard(dealer, 0, 0));
			System.out.println("Dina kort är: " + deck.showAllCards(player, 0));
			System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, 0));
			
			//Player får välja valör i fall det finns ess i leken.
			deck.aceDecision(player);
			
			System.out.println("Dealerns öppna kort är: " + deck.showOneCard(dealer, 0, 0));
			System.out.println("Dina kort är: " + deck.showAllCards(player, 0));
			System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, 0));

			betting.doubleUp(deck.totalHandValue(player, 0), 0);//Checka om det finns dubbla kort.

			//Kör igenom ifall Player vill splitta kort.
			splitHands();
			
			System.out.println("Dina kort är: " + deck.showAllCards(player, 0));
			System.out.println("Totalsumman för dina kort: " + deck.totalHandValue(player, 0));
			
			// Players val - Hit or stay
			hitOrStay ();

			boolean skipDealer = false;
			if (player.hand.size() == 1 && deck.totalHandValue(player, 0) > 21) {
				skipDealer = true;
			}

			// Dealerns tur
			if (skipDealer == false) {
				System.out.println("\nDealerns visar sitt dolda kort: " + deck.showOneCard(dealer, 0, 1));
				System.out.println("Dealerns hand är: " + deck.showAllCards(dealer, 0));

				while (deck.totalHandValue(dealer, 0) <= 16) {
					int tempRandom = new Integer(deck.randomCard(12) + 1);
					deck.dealRandomCards(0, dealer);
					deck.dAceDecision(dealer);
					dealer.hand.get(0).add(tempRandom);
					System.out.println("Dealern drar: " + tempRandom);
				}

				System.out.println("Dealerns hand är: " + deck.showAllCards(dealer, 0));
				System.out.println("Dealerns totalsumma är " + deck.totalHandValue(dealer, 0) + ".");

				if (deck.totalHandValue(dealer, 0) > 21) {
					System.out.println("Dealern blir tjock.");
				} else {
					System.out.println("Dealern stannar.");
				}
			}

			// Utvärdera vem som vunnit.
			printWinner();
			
			mainPlay = betting.yesOrNo("Vill du fortsätta spela? Ja eller Nej");

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
			String handNumber = (player.hand.size() == 1) ? "Din hand" : "Hand nummer " + formatHandNumber;

			if (deck.totalHandValue(dealer, 0) <= 21) {
				if (deck.totalHandValue(player, handIndex) <= 21) {
					if (deck.totalHandValue(player, handIndex) == deck.totalHandValue(dealer, 0)) {
						System.out.println("\n" + handNumber + " förlorade.");
						if (deck.totalHandValue(player, handIndex) >= 17) {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får behålla din insats.");
							betting.onlyStakePayBack(betting.playerBet.get(handIndex));
						}
					} else if (deck.totalHandValue(player, handIndex) > deck.totalHandValue(dealer, 0)) {
						System.out.println(handNumber + " vann!");
						if (deck.totalHandValue(player, handIndex) == 21) {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 1.5x din insats.");
							for (int bettingElem=0;bettingElem<betting.playerBet.size();bettingElem++){betting.bettingPayBack(bettingElem, 1.5);};
						} else {
							System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 1x din insats.");
							for (int bettingElem=0;bettingElem<betting.playerBet.size();bettingElem++){betting.bettingPayBack(bettingElem, 1);}
						}
					}
				} else {
					System.out.println(handNumber + " blev tjock. Du förlorar din insats.");
				}
			} else {
				if (deck.totalHandValue(player, handIndex) <= 21) {
					System.out.println("\n" + handNumber + " vann!");

					if (deck.totalHandValue(player, handIndex) == 21) {
						System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 1.5x din insats.");
						for (int bettingElem=0;bettingElem<betting.playerBet.size();bettingElem++){betting.bettingPayBack(bettingElem, 1.5);}
					} else {
						System.out.println("Du fick " + deck.totalHandValue(player, handIndex) + " och får tillbaka 1x din insats.");
						for (int bettingElem=0;bettingElem<betting.playerBet.size();bettingElem++){betting.bettingPayBack(bettingElem, 1);}
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
		for (int handIndex=0;handIndex<player.hand.size();handIndex++)
		{
			boolean hitMe = betting.yesOrNo("Vill du ha ett nytt kort? Ja eller Nej.");
			
			for (int handElement=0;handElement<player.hand.get(handIndex).size();handElement++)
			{}
			
			if (hitMe==true) {
				deck.dealRandomCards(handIndex, player);
				System.out.println("JA");
				}
		}
	}
	
	public void splitHands()
	{
		boolean splitHands = false;
		do {
			int checkedAllHands = 0;
			for (int handIndex = 0; handIndex < player.hand.size(); handIndex++) {
				if (deck.showOneCard(player, handIndex, 0) == deck.showOneCard(player, handIndex, 1)) {
					if (betting.getPlayerChips() >= betting.getBettingValue()) {
						System.out.println("\nDu har pengar till en split.");
						boolean splitCards = betting.yesOrNo("Vill du splitta dina kort? Ja eller Nej");

						if (splitCards == true) {
							System.out.println("Du splittar.");
							player.hand.add(new ArrayList<Integer>());// Adderar ny rad till player
							int tempElement = player.hand.get(handIndex).get(1);
							player.hand.get(handIndex + 1).add(new Integer(tempElement));// Kopierar värde från gammal array till ny																								
							player.hand.get(handIndex).clear();// Nollställer Row
							player.hand.get(handIndex).add(new Integer(tempElement));// Kopierar tillbaka värdet till gammal array.
							//Slumpmässigt tal till Player
							deck.dealRandomCards(handIndex, player);
							deck.dealRandomCards(handIndex+1, player);
							deck.aceDecision(player);
						} else {
							checkedAllHands++;
						}
					} else {
						System.out.println("\nDu har tyvärr inte insats nog till en split.");
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
