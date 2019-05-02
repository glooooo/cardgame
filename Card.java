public class Card{ //this class represents a playing card
	int value; //the value of the card, 1 through 13
	char suit; //the suit of the card, either a letter, such as S, D, H, or C, or a unicode suit character
	public Card(int value, char suit){ //constructor
		this.value = value;
		this.suit = suit;
	}
	//getters
	public int getValue(){
		return this.value;
	}
	public char getSuit(){
		return this.suit;
	}
	public String getName(){ //find the name of the card from its value
		String name = "";
		switch(this.value){
			case 13:
				return "King";
				//break;
			case 12:
				return "Queen";
				//break;
			case 11:
				return "Jack";
				//break;
			case 10:
				return "Ten";
				//break;
			case 9:
				return "Nine";
				//break;
			case 8:
				return "Eight";
				//break;
			case 7:
				return "Seven";
				//break;
			case 6:
				return "Six";
				//break;
			case 5:
				return "Five";
				//break;
			case 4:
				return "Four";
				//break;
			case 3:
				return "Three";
				//break;
			case 2:
				return "Two";
				//break;
			case 1:
				return "Ace";
				//break;
			default:
				System.out.println("Something has gone terribly wrong.");
				return "Something";
		}
	}

	public String toString(){
		return getName() + suit;
	}
}
