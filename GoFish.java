import java.util.Scanner;
import java.lang.Integer;
import java.util.Vector;
import java.lang.Math;

public class GoFish{
	int playerscore = 0, compscore = 0; //initalize the score variables for the player and computer
	Scanner input = new Scanner(System.in); //create scanner
	Hand humanHand = new Hand(); //create hand objects
	Hand compHand = new Hand();
	Deck deck = new Deck(); //create deck object
  public static void main(String[] args) { //main method
		GoFish goFish = new GoFish(); //create object
    goFish.deck.shuffle(); //shuffle the deck
    System.out.println("Welcome to Go Fish!");
		System.out.println("Drawing to see who goes first...");
		Card compCard; //initalize card objects
		Card humanCard;

		do{ //while it's not a tie
			humanCard = goFish.deck.draw(); //draw cards
			compCard = goFish.deck.draw();
			System.out.println("You got the " + humanCard.getName() + " of " + humanCard.getSuit());
			System.out.println("The computer got the " + compCard.getName() + " of " + compCard.getSuit());
			if(humanCard.getValue() > compCard.getValue()){
				System.out.println("You go first.");
				goFish.deck = new Deck(); //create a new deck object, as there is no way to replace cards in the deck, this is a functionality that could easily be added if neccesary
				goFish.deck.shuffle(); //shuffle new deck

				for(int i = 0; i < 7; i++){ //deal both players 7 cards from the new deck
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


		System.out.println("Your hand:"); //print the user's hand
		System.out.println(goFish.humanHand);

		while((goFish.humanHand.getNumOfCards() > 0 || goFish.compHand.getNumOfCards() > 0) || goFish.deck.getRemaining() > 0){ //while there are cards left in play
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
		String[] acceptableInputs = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"}; // the name is self explanitory
		String entered = "";
		int request; //the requested value of card
		boolean acceptable = false; //did the user enter an acceptable input?
		boolean goAgain = false; //did the user get what they wanted, and therefore go again
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

			switch(entered){ //if they entered a letter
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
			if(compHand.findNum(request) > 0){ //if the computer has any of the requested card
				for (Card card : compHand.getAll(request)){ //add all of the card to the human's hand
					humanHand.addCard(card);
					System.out.println("You got the " + card.getName() + " of " + card.getSuit());
				}
				compHand.removeAll(request); //remove the cards from the computer's hand
				goAgain = true; //the user got what they wanted, and gets to go again
			}
			else{
				System.out.println("The computer does not have that card. Go Fish!");
				if(deck.getRemaining() > 0){ //if there are any cards left in the deck
					drawnCard = deck.draw(); //go fish
					humanHand.addCard(drawnCard);
					System.out.println("You got the " + drawnCard.getName() + " of " + drawnCard.getSuit());
					if(drawnCard.getValue() == request){ //if the user got what they wanted
						goAgain = true;
					}
				}
				else{
					System.out.println("You tried to draw, but there are no more cards in the deck.");
				}
			}

			for(int i = 1; i <= 13; i++){ //get rid of any sets of 4
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
			for(int i = 1; i <= 13; i++){ //find what the largest number of cards with the same value is, and fill the cardNums array
				if(compHand.findNum(i) > max)
					max = compHand.findNum(i);
				cardNums[i-1] = compHand.findNum(i);
			}
			for(int i = 0; i < 13; i++){ //put the values with the max number of cards into the maxValues vector
				if(cardNums[i] == max){
					maxValues.add(i+1);
				}
			}
			request = maxValues.get((int)Math.random() * maxValues.size()); //choose a random one of the options to ask for
			requestCard = new Card(request, 'S'); //create new card object
			System.out.println("The computer is asking for " + requestCard.getName() + "s");

			if(humanHand.findNum(request) > 0){ //basicly do all of the same things as in humanTurn, but with the computer's hand and requested card
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
