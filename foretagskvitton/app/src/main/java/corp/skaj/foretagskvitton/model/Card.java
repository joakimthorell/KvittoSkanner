package corp.skaj.foretagskvitton.model;

/**
 * Created by annekeller on 2017-04-05.
 */

public class Card {
    private int lastFourDigits;

    public Card (int lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }

    public int getLastFourDigits() {
        return lastFourDigits;
    }

    public void setLastFourDigits(int lastFourDigits) {
        this.lastFourDigits = lastFourDigits;
    }
}
