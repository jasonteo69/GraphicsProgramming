import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private ArrayList<Card> hand;
    private Rectangle button;
    private Deck deck;
    private Rectangle gameFunction;

    public DrawPanel() {
        button = new Rectangle(147, 255, 125, 26);
        gameFunction = new Rectangle(330, 25, 150, 30);
        this.addMouseListener(this);
        hand = Card.buildHand();
        deck = new Deck(Card.buildDeck());
        for (Card card : hand) {
            deck.remove(card);
        }
        System.out.println(deck.getDeck().size());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 125;
        int y = 10;
        int track = 0;
        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            track++;
            x = x + c.getImage().getWidth() + 10;
            if (track == 3) {
                y = y + c.getImage().getWidth() + 10;
                track = 0;
                x = 125;
            }
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("PLAY AGAIN", 150, 275);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        g.drawRect((int)gameFunction.getX(), (int)gameFunction.getY(), (int)gameFunction.getWidth(), (int)gameFunction.getHeight());
        g.setFont(new Font("Courier New", Font.BOLD, 16));
        g.drawString("REPLACE CARDS", 333, 45);
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                hand = Card.buildHand();
                deck = new Deck(Card.buildDeck());
                for (Card card : hand) {
                    deck.remove(card);
                }
            }

            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                if (box.contains(clicked)) {
                    hand.get(i).flipCard();
                }
            }
        }

        if (e.getButton() == 3) {
            System.out.println("Mouse right-click detected.");
            for (int i = 0; i < hand.size(); i++) {
                Rectangle box = hand.get(i).getCardBox();
                System.out.println("Clicked position: " + clicked);
                System.out.println("Card " + i + " position: " + box);
                if (box.contains(clicked)) {
                    System.out.println("Card " + i + " clicked.");
                    if (hand.get(i).getHighlight()) {
                        System.out.println("Card " + i + " is highlighted.");
                        System.out.println("Deck size before removal: " + deck.getDeck().size());
                        deck.remove(hand.get(i));
                        System.out.println("Deck size after removal: " + deck.getDeck().size());
                        hand.remove(hand.get(i));
                        Card newCard = deck.randomCard();
                        hand.add(i, newCard);
                        System.out.println("New card selected: " + newCard);
                        System.out.println("Hand size: " + hand.size());
                        System.out.println("Deck size: " + deck.getDeck().size());

                    }
                    else {
                        hand.get(i).flipHighlight();
                    }
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}