package app.hand;

import app.card.Ace;
import app.card.Card;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HandTest {

    private static Hand hand;

    @Before
    public void setUp() throws Exception {
        hand = new Hand();
    }

    @Test
    public void setValue() {
        hand.addCard(new Card(9, "SPADES"));
        hand.addCard(new Card(5, "SPADES"));

        assertEquals(14, hand.getValue());
        assertEquals(14, hand.getLowAceValue());
        assertEquals(14, hand.getHighAceValue());
    }

    @Test
    public void setValueSoftWithAce() {
        hand.addCard(new Card(9, "SPADES"));
        hand.addCard(new Ace("SPADES"));

        assertEquals(20, hand.getValue());
        assertEquals(20, hand.getHighAceValue());
        assertEquals(10, hand.getLowAceValue());
    }

    @Test
    public void setValueWithHardAce() {
        hand.addCard(new Card(9, "SPADES"));
        hand.addCard(new Card(9, "SPADES"));
        hand.addCard(new Ace("SPADES"));

        assertEquals(29, hand.getHighAceValue());
        assertEquals(19, hand.getValue());
        assertEquals(19, hand.getLowAceValue());
    }

    @Test
    public void addCard() {
    }

    @Test
    public void getCard() {
    }

    @Test
    public void removeCard() {
    }

    @Test
    public void removeCard1() {
    }

    @Test
    public void getCards() {
    }

    @Test
    public void getValue() {
    }

    @Test
    public void getNumAces() {
    }

    @Test
    public void addAce() {
    }

    @Test
    public void checkBlackJack() {
    }

    @Test
    public void checkBust() {
    }

    @Test
    public void numCards() {
    }

    @Test
    public void isCardHidden() {
    }

    @Test
    public void canSplit() {
    }

    @Test
    public void withSoftAceMessage() {
    }

    @Test
    public void withHardAceMessage() {
    }

    @Test
    public void withoutAceMessage() {
    }

    @Test
    public void hiddenCardMessage() {
    }
//
//    @Test
//    public void toString() {
//    }
}