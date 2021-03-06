import java.util.Stack;
import java.util.Collections;
public class Deck{
  private Stack<Card> deck;
  public Deck(){ //I don't think that a redraw limit is neccesary in almost all card games, and that should be part of the program using this class
    deck = new Stack<Card>();
    //int cardNo = 0;
    char suit;
    for (int i = 0; i < 4; i++){ //suit
      for (int j = 1; j <= 13; j++){ //value
        switch(i){ //find suit
          case 0:
            suit = '\u2660'; //spades
            break;
          case 1:
            suit = '\u2665'; //hearts
            break;
          case 2:
            suit = '\u2666'; //diamonds
            break;
          case 3:
            suit = '\u2663'; //clubs
            break;
          default:
            System.out.println("Something has gone horribly wrong.");
            suit = 'E';
        }

        deck.push(new Card(j, suit)); //add card to deck
        //cardNo ++;
      }
    }
  }
  public int getRemaining(){
    return deck.size();
  }
  public void shuffle(){
    Collections.shuffle(deck);
  }
  public Card draw(){
    return deck.pop();
  }
  public String toString(){
    String output = "";
    for(Card card : deck){
      output += (card + "\n");
    }
    return output;
  }
}
