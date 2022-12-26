package app.hand;

import app.card.Card;
import app.hand.Hand;

import java.util.ArrayList;
import java.util.List;

public class HandContainer {

    private int currentHand = 0;



    private int numHands = 0;
    private List<Hand> hands = new ArrayList<>();

    public HandContainer() {
        this.addHand();
    }

    public HandContainer(List<Card> cards) {
        this.addHand(cards);
    }

    public Hand getCurrentHand() {
        return this.hands.get(currentHand);
    }


    public Hand addHand(List<Card> cards) {
        this.numHands++;
        Hand newHand = new Hand(cards);
        this.hands.add(newHand);
        return newHand;
    }

    public Hand addHand() {
        this.numHands++;
        Hand newHand = new Hand();
        this.hands.add(newHand);
        return newHand;
    }

    public Hand addHand(Card card) {
        this.numHands++;
        Hand newHand = new Hand(card);
        this.hands.add(newHand);
        return newHand;
    }

    public List<Hand> getHands() {
        return this.hands;
    }

    public int getNumHands() {
        return numHands;
    }

    public void setCurrentHand(int currentHand) {
        if(currentHand <= hands.size()) {
            this.currentHand = currentHand;
        }
    }


}


