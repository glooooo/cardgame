import java.util.Scanner;
public class ShoeTest{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many decks do you want in the shoe?");
		int decks = input.nextInt();
		Shoe shoe = new Shoe(decks);
		System.out.println("What should the redraw limit be?");
		int redraw = input.nextInt();
		Card card;
		for(int i = 0; i < ((52*decks)-redraw); i++){
			card = shoe.draw();
			System.out.println((i+1) + ": " + card.getName() + " of " + card.getSuit());
		}
	}
}
