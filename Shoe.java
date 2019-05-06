import java.util.Vector;
import java.lang.Math;
public class Shoe{
  private Vector<Deck> shoe;
  int deckNum;
  public Shoe(int deckNum){
    this.deckNum = deckNum;
    shoe = new Vector<Deck>();
    for(int i = 0; i < deckNum; i++){
      shoe.add(new Deck());
    }
    for(Deck deck : shoe){
      deck.shuffle();
    }
  }
  public Card draw(){
    Card card = shoe.get((int)Math.random()*deckNum).draw();
    for(Deck deck : shoe){
      if(deck.getRemaining() == 0){
        shoe.removeElement(deck);
      }
    }
		return card;
  }
  public int getRemainingDecks(){
    return shoe.size();
  }
}
