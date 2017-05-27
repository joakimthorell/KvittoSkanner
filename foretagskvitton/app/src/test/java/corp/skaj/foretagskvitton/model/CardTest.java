package corp.skaj.foretagskvitton.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardTest {
    private int cardNum;
    Card card;

    @Before
    public void setup () {
        cardNum = 4543;
        card = new Card(cardNum);
    }

    @Test
    public void testIfCard() {
        assertEquals(card.getCard(), 4543);
    }

    @Test
    public void testChangeCard(){
        card.setCard(2222);
        assertEquals(card.getCard(), 2222);
    }

    @Test
    public void incorrectFormatTest(){
        try {
            card.setCard(33335);
            card.setCard(3);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            assertTrue(false);
        }
    }
}
