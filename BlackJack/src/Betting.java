//3.3
import java.util.ArrayList;
import java.util.Scanner;

public class Betting {

	private int playerChips;
	private Scanner scan;
	private int bettingValue;
	private String overUnderChoice;
	private ArrayList<Integer> playerBet;
	
	public Betting() {
		super();
		playerBet = new ArrayList<>();
		playerChips = 10;
		scan = new Scanner(System.in);
		bettingValue = 0;
		overUnderChoice = "";
	}

	public int getPlayerChips() {

		return playerChips;
	}

	public void setPlayerChips(int playerChips) {
		this.playerChips = playerChips;
	}

	public int getBettingValue() {
		return bettingValue;
	}

	public void setBettingValue(int bettingValue) {
		this.bettingValue = bettingValue;
	}
	
	public ArrayList<Integer> getPlayerBet() {
		return playerBet;
	}

	public void setPlayerBet(ArrayList<Integer> playerBet) {
		this.playerBet = playerBet;
	}

	public int getPlayerBetSize() {
		return playerBet.size();
	}
	
	public int getPlayerBetHandIndex(int handIndex) {
		return playerBet.get(handIndex);
	}
	

	public void bettingLoop() {
		boolean bettingAgain = false;
		int bettingIn = 0;
		do {
			if (getPlayerChips() > 0) {
				System.out.println("Du har $" + getPlayerChips() + ". Hur mycket vill du satsa?");

				bettingIn = bettingScanEvaluator();

				if (bettingIn <= getPlayerChips()) {
					setPlayerChips(getPlayerChips() - bettingIn);
					setBettingValue(bettingIn);
					System.out.println("Du satsar $" + getBettingValue() + ". Du har $" + getPlayerChips() + " kvar.");
					playerBet.add(getBettingValue());
					bettingAgain = false;
				} else {
					System.out.println("Du kan inte satsa så mycket.");
					bettingAgain = true;
				}
				
			} else {
				bettingAgain = false;
				System.out.println("Du har inga pengar kvar att satsa med.");
			}
		} while (bettingAgain == true);

	}

	public void doubleUp(int bettingValue, int bettingIndex) {
		int doubleValue = getBettingValue() * 2;
		boolean doubleUp = false;
		int tempChips = getPlayerChips();

		if (bettingValue >= 7 && bettingValue <= 11) {
			
			if (tempChips >= doubleValue) {
				doubleUp = yesOrNo("Vill du dubbla insatsen? Ja eller Nej.");

				if (doubleUp == true) {
					setPlayerChips(tempChips - doubleValue);
					playerBet.set(0, doubleValue);
					System.out.println("Du dubblade till $" + doubleValue + ". Du har $" + getPlayerChips() + " kvar.");
				}
			}

			if (tempChips < doubleValue && getPlayerChips() > 0) {
				doubleUp = yesOrNo("Din hand har summan $" + bettingValue + ". Du kan inte dubbla insatsen, \nmen du kan satsa återstående pengar $" + getPlayerChips() + ". \nVill du det? Ja eller Nej");
				if (doubleUp == true) {
					playerBet.set(0, playerBet.get(0) + getPlayerChips());
					setPlayerChips(0);
					System.out.println("Du ökade till $" + playerBet.get(0) + ". Du har $" + getPlayerChips() + " kvar.");
				}
			}
		}
	}

	int bettingScanEvaluator() {
		int bettingIn;
		do {
			while (!scan.hasNextInt()) {
				System.out.println("Satsa gärna ha ett rikigt belopp. Försök igen.");
				scan.next();
			}
			bettingIn = scan.nextInt();
		} while (bettingIn <= 0);
		return bettingIn;
	}

	void bettingPayBack(int stakeIn, int multipleIn) {
		
		int newValue=0;
		
		switch (multipleIn) {
		case 0: newValue=playerBet.get(stakeIn);break;
		case 1: newValue=(playerBet.get(stakeIn)*2);break;
		case 2: newValue=(playerBet.get(stakeIn)*2)+playerBet.get(stakeIn);break;
		default:
			break;
		}
		setPlayerChips(getPlayerChips()+newValue);
	}

	void onlyStakePayBack(int stakeIn) {setPlayerChips(stakeIn + getPlayerChips());}

	boolean yesOrNo(String printOut) {
		boolean returnAnswer = true;
		boolean fetchReturnAnswer = false;

		while (fetchReturnAnswer == false) {
			System.out.println(printOut);
			String scannerAnswer = scan.next().toLowerCase();
			
			switch (scannerAnswer) {
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
				fetchReturnAnswer = false;
				break;
			}
		} 
		return returnAnswer;
	}
	

	//Välja om man vill satsa över eller under
		void overUnderMeth() {
			if (getPlayerChips() >= getBettingValue() )
			{
				boolean overUnderAnswer=false;
				overUnderAnswer = yesOrNo("Vill du satsa $"+getBettingValue()+" på Över/Under? Ja eller Nej.");
				if (overUnderAnswer == true)
				{
					boolean fetchReturnAnswer = false;
					
					while (fetchReturnAnswer == false) {
						System.out.println("Vad vill du satsa? Över eller Under?\nEss räknas som 1.");
						String scannerAnswer = scan.next().toLowerCase();
						System.out.println(scannerAnswer);
						switch (scannerAnswer) {
						case "ö":
						case "över":
							overUnderChoice = "över";
							System.out.println("Du har valt att spela $"+getBettingValue()+" på Över.");							
							fetchReturnAnswer=true;
							break;
						case "u":
						case "under":
							overUnderChoice = "under";
							System.out.println("Du har valt att spela $"+getBettingValue()+" på Under.");
							fetchReturnAnswer=true;
							break;
						default:
							fetchReturnAnswer = false;
							break;
						}
					}
				}
			}
			
			
		}
		void overUnderPay(Player playerObj)
		{
			if (overUnderChoice != "")
			{
				int playerCard1=playerObj.hand.get(0).get(0);
				int playerCard2=playerObj.hand.get(0).get(1);
				
				if (playerCard1==11) {playerCard1=1;}
				if (playerCard2==11) {playerCard2=1;}
				if (playerCard1>11) {playerCard1=10;}
				if (playerCard2>11) {playerCard2=10;}
				
				int TotalCardValue=	playerCard1+playerCard2;	
				
			
				
				if ( overUnderChoice.equals("över"))
				{
					if (TotalCardValue> 13)
					{
					System.out.println("Du satsade på Över och får tillbaka 2x insatsen.");
					bettingPayBack(0, 1);
					System.out.println("Du har $" + getPlayerChips() + ".");
					}
					else {
						System.out.println("Det blev "+TotalCardValue+" på Över/Under.\nDu förlorade $"+getBettingValue()+". Buhu.");
						setPlayerChips(getPlayerChips()-getBettingValue());
						System.out.println("Du har $" + getPlayerChips() + " kvar.");
					}
				}
				else if (overUnderChoice.equals("under"))
				{
					if (TotalCardValue<13)
					{
						int twoFirstCardsValue = playerObj.hand.get(0).get(0)+playerObj.hand.get(0).get(1);
						if (twoFirstCardsValue==22)
						{
							System.out.println("Du satsade på Under och fick två ess.\nDu får tillbaka 3x insatsen.");
							bettingPayBack(0, 2);
							System.out.println("Du har $" + getPlayerChips() + ".");
						}else {
							System.out.println("Du fick satsat på Under och får tillbaka 2x insatsen.");
							bettingPayBack(0, 1);
							System.out.println("Du har $" + getPlayerChips() + ".");
						}
					}
					else {
						System.out.println("Det blev "+TotalCardValue+" på Över/Under.\nDu förlorade $"+getBettingValue()+". Buhu.");
						setPlayerChips(getPlayerChips()-getBettingValue());
						System.out.println("Du har $" + getPlayerChips() + " kvar.");
					}

				}
				System.out.println("");
			}
			
		}

}