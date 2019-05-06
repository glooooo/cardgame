import java.util.Scanner;
public class ShoeTest{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int decks, redraw;
		do{
			System.out.println("How many decks do you want in the shoe?");
			decks = input.nextInt();
			if(decks < 1 || decks > 10){
				System.out.println("Please enter a valid number");
			}
		}while(decks < 1 || decks > 10);
		Shoe shoe = new Shoe(decks);
		do{
			System.out.println("What should the redraw limit be?");
			redraw = input.nextInt();
			if(redraw < 0 || redraw > 52*decks){
				System.out.println("Please enter a valid input.");
			}
		}while(redraw < 0 || redraw > 52*decks);
		Card card;
		for(int i = 0; i < ((52*decks)-redraw); i++){
			card = shoe.draw();
			System.out.println((i+1) + ": " + card.getName() + " of " + card.getSuit());
		}
	}
}
