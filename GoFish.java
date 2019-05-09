public class GoFish{
  public static void main(String[] args) {
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
}
