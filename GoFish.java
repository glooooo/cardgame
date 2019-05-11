import java.util.Scanner;
import java.lang.Integer;
public class GoFish{
  public static void main(String[] args) {
		int playerscore = 0, compscore = 0;
		Scanner input = new Scanner(System.in);
    Hand humanHand = new Hand();
    Hand compHand = new Hand();
    Deck deck = new Deck();
    deck.shuffle();
    for(int i = 0; i < 7; i++){ //deal both players 7 cards
      compHand.addCard(deck.draw());
      humanHand.addCard(deck.draw());
    }
    System.out.println("Welcome to Go Fish!");
    System.out.println("Human cards:");
    System.out.println(humanHand);
    System.out.println("Computer cards:");
    System.out.println(compHand);
  }

	public void humanTurn(){
		String[] acceptableInputs = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String entered;
		int request;
		boolean acceptable = false;
		boolean goAgain = false;
		Card drawnCard;
		do{
			System.out.println("Your hand is " + humanHand);
			System.out.println("What card would you like to ask for? (Enter a number, or A, J, Q, or K)");
			outerloop:
			do{
				entered = input.nextLine();
				for(String value : acceptableInputs){
					if(entered.equals(value)){
						acceptable = true;
						break outerloop;
					}
				}
				System.out.println("Please enter a valid input.");
			}

			switch(entered){
				case "A":
					entered = "1";
					break;
				case "J":
					entered = "11";
					break;
				case "Q":
					entered = "12";
					break;
				case "K":
					entered = "13";
					break;
			}
			request = Integer.parseInt(entered);
			if(compHand.getAll(request).length() > 0){
				for (Card card : compHand.getAll(request)){
					humanHand.addCard(card);
					System.out.println("You got the " + card.getName() + " of " + card.getSuit());
				}
				goAgain = true;
			}
			else{
				System.out.println("The computer does not have that card. Go Fish!");
				drawnCard = deck.draw();
				System.out.println("You got the " + drawnCard.getName() + " of " + drawnCard.getSuit());
			}
			for(int i = 1; i <= 13; i++){
				if (findNum(i) == 4){
					System.out.println("Getting rid of:");
					for(Card card : humanHand.getAll(i)){
						System.out.println(card.getName() + " of " + card.getSuit());
					}
					playerscore++;
				}
			}
		}while(goAgain);

	}
}
