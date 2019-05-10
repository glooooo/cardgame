import java.util.Scanner;
public class GoFish{
  public static void main(String[] args) {
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
		String[] acceptableInputs = {"A", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String entered;
		boolean acceptable = false;
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
	}
}
