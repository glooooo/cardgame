import java.util.Scanner;
import java.lang.Integer;
import java.util.Vector;
import java.lang.Math;

public class GoFish{
	int playerscore = 0, compscore = 0;
	Scanner input = new Scanner(System.in);
	Hand humanHand = new Hand();
	Hand compHand = new Hand();
	Deck deck = new Deck();
  public static void main(String[] args) {
		GoFish goFish = new GoFish();
    goFish.deck.shuffle();
    for(int i = 0; i < 7; i++){ //deal both players 7 cards
      goFish.compHand.addCard(goFish.deck.draw());
      goFish.humanHand.addCard(goFish.deck.draw());
    }
    System.out.println("Welcome to Go Fish!");
    System.out.println("Human cards:");
    System.out.println(goFish.humanHand);
    System.out.println("Computer cards:");
    System.out.println(goFish.compHand);
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
			}while(!acceptable);

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
			if(compHand.getAll(request).length > 0){
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
				if(drawnCard.getValue() == request){
					goAgain = true;
				}
			}
			for(int i = 1; i <= 13; i++){
				if (humanHand.findNum(i) == 4){
					System.out.println("Getting rid of:");
					for(Card card : humanHand.getAll(i)){
						System.out.println(card.getName() + " of " + card.getSuit());
					}
					playerscore++;
				}
			}
			if(goAgain == true){
				System.out.println("You got what you asked for, you get to go again.");
			}
		}while(goAgain);

	}

	public void compTurn(){
		int request, max = 0; //request is the card that the computer is requesting, max is the largest number of the same card that the computer has
		int[] cardNums = new int[13]; //this is the number of each value that is in the computer's hand
		Vector<Integer> maxValues = new Vector<Integer>(0,0); //this is the list of values that have the max number of cards
		for(int i = 1; i <= 13; i++){
			if(compHand.findNum(i) > max){
				max = compHand.findNum(i);
			//	maxNum = 0;
			}
			//if(compHand.findNum(i) == max)
			//	maxNum++;
			cardNums[i-1] = compHand.findNum(i);
		}
		//maxValues = new int[maxNum];
		for(int i = 0; i < 13; i++){
			if(cardNums[i] == max){
				maxValues.add(i+1);
			}
		}
		request = maxValues.get((int)Math.random() * maxValues.size());
	}
}
