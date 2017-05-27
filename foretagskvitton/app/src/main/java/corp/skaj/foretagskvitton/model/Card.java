package corp.skaj.foretagskvitton.model;

/**
 * Holds last 4 digits of a business card, enabling identification
 * of the company holding a specific card.
 */
public class Card {
    private int cardNum;

    public Card(int cardNum) {
        this.cardNum = cardNum;
    }

    public boolean setCard(int cardNum) {
        if (isCard(cardNum)) {
            this.cardNum = cardNum;
            return true;
        }
        return false;
    }

    private boolean isCard(int cardNum) {
        return String.valueOf(cardNum).length() == 4;
    }

    public int getCard() {
        return cardNum;
    }
}