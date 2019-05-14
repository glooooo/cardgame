import java.util.Vector;
import java.util.Iterator;

public class Hand{
  Vector<Card> hand = new Vector<Card>(0,0);
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
  public int findNum(int value){
    int num = 0;
    for (Card card : hand){
      if (card.getValue() == value){
        num++;
      }
    }
    return num;
  }
  public Card[] getAll(int value){
    Vector<Card> cards = new Vector<Card>(0,0);
    for (Card card : hand){
      if (card.getValue() == value){
        cards.add(card);
      }
    }
    return cards.toArray(new Card[0]); //need to give a dimension for the array, because java is weird
  }
	public void removeAll(int value){
		Card card;
		Iterator<Card> itr = hand.iterator();
    while(itr.hasNext()){
			card = itr.next();
			if(card.getValue() == value)
				itr.remove();
		}
	}
  public String toString(){
    String cards = "";
    for (Card card : hand){
      cards = cards + card.getName() + " of " + card.getSuit() + "\n";
    }
    return cards;
  }
  public int getNumOfCards(){
    return hand.size();
  }
}
