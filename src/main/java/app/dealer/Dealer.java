package app.dealer;

import app.card.Ace;
import app.card.Card;
import app.hand.Hand;
import app.player.Player;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Dealer {
    private Hand hand;
    private Deck deck;
    private final List<Player> players;
    private Card upCard;
    private Card downCard;
    private boolean busted;
    private boolean blackJack;

    public Dealer(List<Player> players, int numDecks) {
        this.hand = new Hand();
        this.deck = new Deck(numDecks);
        this.players = players;
        this.busted = false;
        this.blackJack = false;
    }

    public Dealer(List<Player> players) {
        this.hand = new Hand();
        this.deck = new Deck();
        this.players = players;
        this.busted = false;
        this.blackJack = false;
    }

    public Hand getHand() {
        return this.hand;
    }

    public List<Card> getCards() {
        return this.getHand().getCards();
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Card getUpCard() {
        return upCard;
    }

    public Card getDownCard() {
        return downCard;
    }

    public boolean isBusted() {
        return busted;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }



    public void reset() {
        this.busted = false;
        this.blackJack = false;
        this.hand = new Hand();
    }

    public void dealOpeningCards() {
        for(int i= 0; i < 2; i++) {
            if(i == 0 ) {
                this.upCard = this.addCard(dealFaceUp());
            } else {
                this.downCard = this.addCard(dealFaceDown());
            }

            for(Player player : players) {
                player.addCard(this.dealFaceUp());
            }
        }

    }

    public Card addCard(Card card) {
        return this.getHand().addCard(card);
    }

    public void resetDeck() {
        this.deck = new Deck();
    }



    public int getHandValue() {

        return this.hand.getValue();
    }


    public Card dealFaceUp() {
        return deck.removeFirst();
    }

    public Card dealFaceDown() {
        Card card = deck.removeFirst();
        card.setHidden(true);
        return card;
    }

    public Card revealDownCard() {
        Card card = getDownCard();
        card.setHidden(false);
        return card;
    }

    public boolean checkHiddenBlackjack() {
        boolean result =  false;
        if(upCard instanceof Ace) {
            //ask for insurance
            result = checkBlackJack();
        }

        return result;
    }

    public void play() {
        downCard.setHidden(false);

        printHand();

        if(checkBlackJack()) {
            printBlackJack();
            return;
        }

        while(this.hand.getValue() <=16) {
            this.addCard(dealFaceUp());
            printHand();
        }

        if(this.hand.getValue() > 21) {
            this.busted = true;
            printBust();
        } else {
            printStand();
        }

//        System.out.println("DEALER FINISHED");
    }

    public boolean checkBlackJack() {
        return this.hand.checkBlackJack();
    }

    public void printHand() {
        System.out.println( System.lineSeparator() + "The dealers hand is: ");
        System.out.println(this.toString());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }

    }

    public void printStand() {
//        printHand();
        System.out.println("The dealer stood." + System.lineSeparator());
    }

    public void printBust() {
//        printHand();
        System.out.println("The dealer busted!" + System.lineSeparator());
    }

    public void printOpeningHand() {
        System.out.println("The dealer's opening hand is an up " + this.upCard.toString());
    }

    public void printBlackJack() {
        System.out.println("Dealer has a black jack!");
        printWin();
    }

    public void printWin() {
        System.out.println("Dealer won!");
    }

    @Override
    public String toString() {
        return this.hand.toString();
    }



}
