import java.util.Vector;
import java.util.Iterator;

public class Hand{
  Vector<Card> hand = new Vector<Card>(0,0); //create new vector
  public void addCard(Card card){
    hand.add(card);
  }
  public Card discard(Card card){
    hand.remove(card);
    return card;
  }
  public Card discard(int index){ //overload method, one version removes a specific card, the other removes an index
    Card card = hand.elementAt(index);
    hand.remove(index);
    return card;
  }
  public int findNum(int value){ //find the number of cards with a specific value
    int num = 0;
    for (Card card : hand){
      if (card.getValue() == value){
        num++;
      }
    }
    return num;
  }
  public Card[] getAll(int value){ //return an array of all of the cards with a specific value
    Vector<Card> cards = new Vector<Card>(0,0);
    for (Card card : hand){
      if (card.getValue() == value){
        cards.add(card);
      }
    }
    return cards.toArray(new Card[0]); //need to give a dimension for the array, because java is weird
  }
	public void removeAll(int value){ //remove all cards with a specific value
		Card card;
		Iterator<Card> itr = hand.iterator(); //create iterator
    while(itr.hasNext()){ //while there is another card in the hand
			card = itr.next(); //move to the next position
			if(card.getValue() == value) //remove the card if it has the value
				itr.remove();
		}
	}
	public int getNumOfCards(){ 
    return hand.size();
  }
  public String toString(){
    String cards = "";
    for (int i = 1; i <= 13; i++){
      for(Card card : getAll(i))
				cards = cards + card.getName() + " of " + card.getSuit() + "\n";
    }
    return cards;
  }
}
