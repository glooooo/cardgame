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
    System.out.println("Welcome to Go Fish!");
		System.out.println("Drawing to see who goes first...");
		Card compCard;
		Card humanCard;

		do{
			humanCard = goFish.deck.draw();
			compCard = goFish.deck.draw();
			System.out.println("You got the " + humanCard.getName() + " of " + humanCard.getSuit());
			System.out.println("The computer got the " + compCard.getName() + " of " + compCard.getSuit());
			if(humanCard.getValue() > compCard.getValue()){
				System.out.println("You go first.");
				goFish.deck = new Deck();
				goFish.deck.shuffle();

				for(int i = 0; i < 7; i++){ //deal both players 7 cards
		      goFish.compHand.addCard(goFish.deck.draw());
		      goFish.humanHand.addCard(goFish.deck.draw());
		    }
			}
			else{
				if(humanCard.getValue() < compCard.getValue()){
					System.out.println("The computer goes first");
					goFish.deck = new Deck();
					goFish.deck.shuffle();

					for(int i = 0; i < 7; i++){ //deal both players 7 cards
			      goFish.compHand.addCard(goFish.deck.draw());
			      goFish.humanHand.addCard(goFish.deck.draw());
			    }
					goFish.compTurn();
				}
				else{
					System.out.println("It's a tie, drawing again.");
				}
			}
		}while(humanCard.getValue() == compCard.getValue());


		System.out.println("Your hand:");
		System.out.println(goFish.humanHand);

		while((goFish.humanHand.getNumOfCards() > 0 || goFish.compHand.getNumOfCards() > 0) || goFish.deck.getRemaining() > 0){
			System.out.println("It's your turn.");
			goFish.humanTurn();
			System.out.println("It's the computer's turn.");
			goFish.compTurn();
		}
		System.out.println("There are no more cards, the game is over.");
		System.out.println("Computer's score: " + goFish.compscore);
		System.out.println("Human's score: " + goFish.playerscore);
		if(goFish.playerscore > goFish.compscore){
			System.out.println("You win!");
		}
		else{
			if(goFish.playerscore < goFish.compscore){
				System.out.println("The computer wins!");
			}
			else{
				System.out.println("It's a tie.");
			}
		}
		System.out.println("Good game!");
  }

	public void humanTurn(){
		String[] acceptableInputs = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String entered = "";
		int request;
		boolean acceptable = false;
		boolean goAgain = false;
		Card drawnCard;
		do{
			System.out.println("Your hand is\n" + humanHand);
			System.out.println("What card would you like to ask for? (Enter a number, or A, J, Q, or K)");
			acceptable = false;
			goAgain = false;
			outerloop:
			while(!acceptable){
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
			if(compHand.findNum(request) > 0){
				for (Card card : compHand.getAll(request)){
					humanHand.addCard(card);
					System.out.println("You got the " + card.getName() + " of " + card.getSuit());
				}
				compHand.removeAll(request);
				goAgain = true;
			}
			else{
				System.out.println("The computer does not have that card. Go Fish!");
				if(deck.getRemaining() > 0){
					drawnCard = deck.draw();
					humanHand.addCard(drawnCard);
					System.out.println("You got the " + drawnCard.getName() + " of " + drawnCard.getSuit());
					if(drawnCard.getValue() == request){
						goAgain = true;
					}
				}
				else{
					System.out.println("You tried to draw, but there are no more cards in the deck.");
				}
			}

			for(int i = 1; i <= 13; i++){
				if (humanHand.findNum(i) == 4){
					System.out.println("Getting rid of:");
					for(Card card : humanHand.getAll(i)){
						System.out.println(card.getName() + " of " + card.getSuit());
					}
					humanHand.removeAll(i);
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
		Vector<Integer> maxValues; //this is the list of values that have the max number of cards
		boolean goAgain = false;
		Card drawnCard;
		Card requestCard; //used to use the card class' getName method
		do{
			goAgain = false;
			max = 0;
			maxValues = new Vector<Integer>(0,0);
			for(int i = 1; i <= 13; i++){
				if(compHand.findNum(i) > max)
					max = compHand.findNum(i);
				cardNums[i-1] = compHand.findNum(i);
			}
			for(int i = 0; i < 13; i++){
				if(cardNums[i] == max){
					maxValues.add(i+1);
				}
			}
			request = maxValues.get((int)Math.random() * maxValues.size());
			requestCard = new Card(request, 'S');
			System.out.println("The computer is asking for " + requestCard.getName() + "s");

			if(humanHand.findNum(request) > 0){
				for (Card card : humanHand.getAll(request)){
					compHand.addCard(card);
					System.out.println("You gave the computer the " + card.getName() + " of " + card.getSuit());
				}
				humanHand.removeAll(request);
				goAgain = true;
			}
			else{
				System.out.println("You do not have that card. The computer is going fish.");
				if(deck.getRemaining() > 0){
					drawnCard = deck.draw();
					compHand.addCard(drawnCard);
					if(drawnCard.getValue() == request){
						goAgain = true;
					}
				}
				else{
					System.out.println("The computer tried to draw a card, but the deck is gone.");
				}
			}
			for(int i = 1; i <= 13; i++){
				if (compHand.findNum(i) == 4){
					System.out.println("The computer is getting rid of:");
					for(Card card : compHand.getAll(i)){
						System.out.println(card.getName() + " of " + card.getSuit());
					}
					compHand.removeAll(i);
					compscore++;
				}
			}
			if(goAgain == true){
				System.out.println("The computer got what it asked for, and is now going again.");
			}

		}while(goAgain);
	}
}
