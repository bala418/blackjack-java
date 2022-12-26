package app.dealer;

import app.card.Ace;
import app.card.Card;
import app.card.Royal;

import java.util.*;

public class Deck {

    public final Deque<Card> cards = new ArrayDeque<>();
    private int numCards;

    public Deck(int numDecks) {
        cards.addAll(generateCards(numDecks));
    }

    public Deck() {
        cards.addAll(generateCards(1));
    }

    private void incrementNumCards() {
        this.numCards++;
    }

    private void decrementNumCards() {
        this.numCards--;
    }

    public int getNumCards() {
        return this.numCards;
    }

    private List<Card> generateCards(int numDecks) {
        List<Card> deckArray = new ArrayList<>();

        for (int i = 0; i < numDecks; i++) {

            //Add number cards
            for (Integer value : Card.values) {
                for (String suit : Card.suits) {
                    deckArray.add(new Card(value, suit));
                    incrementNumCards();
                }
            }

            //Add royal cards
            for (char type : Card.royals) {
                for (String suit : Card.suits) {
                    deckArray.add(new Royal(suit, type));
                    incrementNumCards();
                }
            }

            //Add aces
            for (String suit : Card.suits) {
                deckArray.add(new Ace(suit));
                incrementNumCards();
            }
        }


        Collections.shuffle(deckArray);

        return deckArray;
    }

    public Card removeFirst() {
        decrementNumCards();
        return cards.removeFirst();
    }



    public void print() {
        Iterator iterator = cards.iterator();
        int counter = 1;

        while(iterator.hasNext()) {

            System.out.println(counter);
            System.out.println(iterator.next().toString());
            counter++;
        }

    }

}
