import java.util.ArrayList;
import java.util.Scanner;

public class Betting {

	public ArrayList<Integer> playerBet = new ArrayList<>();
	private int playerChips = 10;
	private Scanner scan = new Scanner(System.in);
	private int bettingValue = 0;
	private String overUnderChoice = "";

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

	public int bettingLoop() {
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
		return bettingIn;
	}

	public void doubleUp(int bettingValue, int bettingIndex) {
		int doubleValue = getBettingValue() * 2;
		boolean doubleUp = false;
		int tempChips = getPlayerChips();

		if (bettingValue >= 7 && bettingValue <= 11) {
			
			if (tempChips >= doubleValue) {
				doubleUp = yesOrNo("Vill du dubbla insatsen? Ja eller Nej");

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

	void bettingPayBack(int stakeIn, double multipleIn) {
		System.out.println(stakeIn+" "+multipleIn);
		int newValue = (int) Math.ceil((stakeIn * multipleIn) + stakeIn);
		newValue = newValue + getPlayerChips();
		int newValueOut = (int) newValue;
		setPlayerChips(newValueOut);
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
				overUnderAnswer = yesOrNo("Vill du satsa Över/Under? Ja eller Nej.");
				if (overUnderAnswer = true)
				{
					boolean fetchReturnAnswer = false;

					while (fetchReturnAnswer == false) {
						System.out.println("Vill du satsa över eller under?");
						String scannerAnswer = scan.next().toLowerCase();
						
						switch (scannerAnswer) {
						case "ö":
						case "över":
							overUnderChoice = "över";
							System.out.println("Du valde att spela $ " + overUnderChoice);
							break;
						case "u":
						case "under":
							overUnderChoice = "under";
							System.out.println("Du valde att spela $ " + overUnderChoice);
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
				if (playerObj.getTotalValue()> 13 && Betting.this.overUnderChoice.equals("över")){
					
				
				}
				else if (playerObj.getTotalValue() < 13 && Betting.this.overUnderChoice.equals("under"))
				{
					
				}
				else {
					System.out.println("Det blev 13 du förlorade buhu");
				}
			}
			
		}
}