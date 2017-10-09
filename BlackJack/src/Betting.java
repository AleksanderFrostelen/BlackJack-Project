import java.util.ArrayList;
import java.util.Scanner;


public class Betting {

	public ArrayList <Integer> playerBet = new ArrayList<>();	
	 
	private int playerChips = 10;
	private Scanner scan = new Scanner(System.in);
	private int bettingValue=0;

	
	public ArrayList<Integer> getPlayerBet() {
		return playerBet;
	}

	public void setPlayerBet(ArrayList<Integer> playerBet) {
		this.playerBet = playerBet;
		
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



	/**Har man inga pengar kan man inte betta - skriv ut att man inte kan betta mer. 
	 * har man pengar räknar man första handen playerBet[0]
	 * finns det fler händer fortsätter man 
	 * 
	 * Om man har 7-11 kan man dubbla
	 * Om man inte har pengar får man istället upp att man kan satsa sina sista slantar
	 * Om man inte har några pengar kvar får man upp att man inte kan dubbla 
	 * 
	 * 
	 * Om man satsat sin sista slant och förlorat så åker man ut. 
	 * 
	 * @param betIn
	 * @param chipsIn
	 */
	
	//vinst ger 1x pengar. Alltså 10 man satsade + 10
	//Har man 21 och vinner får man 1.5x peng. Alltså 10 man satsade + 15
	//Har dealer och playern lika mellan 17-21 får man tillbka instatsen.  
	//Har spelaren 7-11 på grundgiven kan spelaren dubbla insatsen. 
	//felmeddelande om man bettar med för mycket pengar. 
	//är pengarna slut ska spelet sluta auto. ett felmeddelande att pengarna är slut. 
	
	  public int bettingLoop() 
	  {
		   boolean bettingAgain=false;
		   int bettingIn=0;
	   do{
	     if (getPlayerChips()>0)
	      {
			  System.out.println("Du har $"+getPlayerChips()+". Hur mycket vill du satsa?");
			  
			  bettingIn = bettingScanEvaluator();

			  if (bettingIn<=getPlayerChips())
			  {
				  setPlayerChips(getPlayerChips()-bettingIn); 
				  setBettingValue(bettingIn);
				  System.out.println("Du satsar $" + getBettingValue()+". Du har $"+getPlayerChips()+" kvar.");
				  playerBet.add(getBettingValue());
				  bettingAgain=false;
			  }else {
				  System.out.println("Du kan inte satsa så mycket.");
				  bettingAgain=true;
			  }
			  
	      }else{
		      bettingAgain=false;
		      System.out.println("Du har inga pengar kvar att satsa med.");
	      }
	     }while(bettingAgain==true);	   
	   return bettingIn;
	  
	  }

	  

	  public void doubleUp(int bettingValue, int bettingIndex)
	  {
		  int doubleValue = getBettingValue()*2;
		  boolean doubleUp = false;
		  int tempChips = getPlayerChips();
		  
		  if (bettingValue >=7 && bettingValue<=11)
		  {
			  System.out.println(tempChips+" "+doubleValue);
			  
			  if (tempChips>=doubleValue)
			  {
				  System.out.println("Vill du dubbla insatsen? Ja eller Nej");
				  String scannerAnswer = scan.next().toLowerCase();
				  doubleUp = yesOrNo(scannerAnswer, "Vill du dubbla insatsen? Ja eller Nej");
			 
				if (doubleUp==true)
				  {
					  setPlayerChips(tempChips-doubleValue);
					  playerBet.set(0,doubleValue);
					  System.out.println("Du dubblade till $" + doubleValue+". Du har $"+getPlayerChips()+" kvar.");
				  } 
			  }
			  
			  if(tempChips<doubleValue&&getPlayerChips()>0)
			  {
				  System.out.println("Din hand har summan $"+bettingValue+". Du kan inte dubbla insatsen, \nmen du kan satsa återstående pengar. \nVill du det? Ja eller Nej");
				  String scannerAnswer = scan.next().toLowerCase();
				  doubleUp = yesOrNo(scannerAnswer, "Din hand har summan $"+bettingValue+". Du kan inte dubbla insatsen, \nmen du kan satsa återstående pengar $"+getPlayerChips()+". \nVill du det? Ja eller Nej");
					if (doubleUp==true)
					  {
						  playerBet.set(0,playerBet.get(0)+getPlayerChips());
						  setPlayerChips(0);
						  System.out.println("Du ökade till $" + playerBet.get(0)+". Du har $"+getPlayerChips()+" kvar.");
					  } 
			  }
		  }
		  

		  
	  }
	  
	  int bettingScanEvaluator ()
	  {
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
	  
	  //GÖR OM DET BLIR FEL I DEFAULT
		boolean yesOrNo(String answerIn, String printOut)
		{
			boolean returnAnswer=true;
			boolean fetchReturnAnswer=false;
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


	  
}
