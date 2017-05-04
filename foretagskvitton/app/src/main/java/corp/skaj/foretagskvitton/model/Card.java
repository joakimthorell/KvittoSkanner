package corp.skaj.foretagskvitton.model;

import java.io.Serializable;

/**
 *
 */
public class Card {
    private int card;

    public Card (int card) {
        this.card = card;
    }

    /**
     *
     * @param card
     */
    public void setCard (int card) {
        this.card = card;
    }

    /**
     *
     * @return card
     */
    public int getCard () {
        return card;
    }


}
