package corp.skaj.foretagskvitton.model;

/**
 * Holds information about the card used for the purchase
 */
public class Card {
    private int card;

    public Card(int card) {
        this.card = card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public int getCard() {
        return card;
    }
}
