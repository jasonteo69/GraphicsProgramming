import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck;
    public Deck() {
        deck = new ArrayList<Card>();
    }
    public void addToDeck(Card card) {
        deck.add(card);
    }

}
