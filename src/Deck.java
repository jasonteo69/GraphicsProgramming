import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(ArrayList<Card> deck) {
        this.deck = deck;
    }
    public void remove(Card card) {
        deck.remove(card);
    }
    public Card randomCard() {
        return deck.get((int) (Math.random() * deck.size()));
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}
