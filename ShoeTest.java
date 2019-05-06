import java.util.Scanner;
public class ShoeTest{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("How many decks do you want in the shoe?");
		int decks = input.nextInt();
		Shoe shoe = new Shoe(decks);
		

	}
}
