package app.player;

import app.card.Card;
import app.exception.BetException;
import app.exception.HandException;
import app.hand.Hand;
import app.hand.HandContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Player {


    private final String name;
    private HandContainer hands;
    private double bet = 0;
    private double winnings = 0;
    private boolean busted = false;
    private boolean blackJack = false;

    public Player() {
        this.name = "The Man with No Name";
        this.winnings += 100;
        this.hands = new HandContainer();
    }

    public Player(String name) {
        this.name = name;
        this.winnings = 100;
        this.hands = new HandContainer();
    }

    public Player(String name, double winnings) {
        this.name = name;
        this.winnings += winnings;
        this.hands = new HandContainer();
    }

    public Player(String name, double winnings, HandContainer hands) {
        this.name = name;
        this.winnings += winnings;
        this.hands = hands;
    }

    public Player(double winnings) {
        this.name = "DEFAULT";
        this.winnings += winnings;
        this.hands = new HandContainer();
    }

    public void hit(Card card) {
        hands.getCurrentHand().addCard(card);
    }

    private void setBet(double bet) throws BetException {
        if(bet > this.winnings) {
            throw new BetException("Bet cannot be greater than current winnings");
        } else {
            this.bet = bet;
            this.winnings -= bet;
        }
    }

    public String getName() {
        return name;
    }

    public double getWinnings() {
        return winnings;
    }

    public boolean isBusted() {
        return busted;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public void setBusted(boolean busted) {
        this.busted = busted;
    }

    public void setBlackJack(boolean blackJack) {
        this.blackJack = blackJack;
    }

    public void setWinnings(double winnings) {
        this.winnings = winnings;
    }

    public List<Hand> getHands() {
        return this.hands.getHands();
    }

    public int getNumHands() {
        return this.hands.getNumHands();
    }

    public void setCurrentHand(int index) {
        this.hands.setCurrentHand(index);
    }

    public Hand getCurrentHand() {
        return this.hands.getCurrentHand();
    }

    public int getCurrentHandValue() {
        return this.getCurrentHand().getValue();
    }

    public double getBet() {
        return this.bet;
    }

    public void addCard(Card card) {
        hands.getCurrentHand().addCard(card);
    }

    public Hand addHand(Card card1, Card card2) {
        List<Card> newCards = new ArrayList<>();
        newCards.add(card1);
        newCards.add(card2);
        return this.hands.addHand(newCards);
    }

    public void split(Card card1, Card card2) {
        try {
            hands.getCurrentHand().canSplit();
        } catch (HandException ex) {
            ex.printStackTrace();
        }
        Hand originalHand = hands.getCurrentHand();
//        Card firstCard = originalHand.cards.get(0);
        Card secondCard = originalHand.getCard(1);
        Hand newHand = this.hands.addHand(secondCard);
        originalHand.removeCard(secondCard);

        originalHand.addCard(card1);
        newHand.addCard(card2);
    }

    public void reset() {
        this.busted = false;
        this.blackJack = false;
        this.bet = 0;
        this.hands = new HandContainer();
    }

    public boolean checkZeroWinnings() {
        return this.winnings == 0;
    }

    public boolean checkBlackJack () {
        this.blackJack = getCurrentHand().checkBlackJack();
        return this.blackJack;
    }

    public boolean checkBust() {
        this.busted = getCurrentHand().checkBust();
        return this.busted;
    }

    public void placeBet(double bet) throws BetException {
            this.setBet(bet);


        printBet();
    }

    public void push() {
        this.winnings += this.bet;
        printPush();
    }

    public void win() {
        this.winnings += 2 * this.bet;
        printWin();
    }

    public void blackJackWin() {
        this.winnings += this.bet * 2.5;
        printBlackJack();
    }

    public void lose() {
        printLose();
    }

    public void printZeroWinningsLoss() {
        System.out.println(this.name + " lost all of their money!" + System.lineSeparator() + this.name + " loses the game!");
    }

    public void doubleDown(Card card) {
        this.winnings -= this.bet;
        this.bet += this.bet;
        addCard(card);
        printDouble();
    }

    public void printDouble() {
        System.out.println(this.name + " doubled down!");
    }

    public void printHand() {
        System.out.println(this.name + " has:" + System.lineSeparator() + this.toString() + System.lineSeparator());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void printBet() {
        System.out.println(this.name + " bet " + this.bet + System.lineSeparator());
    }


    public void printBust() {
        printHand();
        System.out.println(this.name + " busted!" + System.lineSeparator());
    }

    public void printStand() {
        System.out.println(this.name + " stood with a " + this.getCurrentHand().getValue() + System.lineSeparator());
    }

    public void printBlackJack() {
        System.out.println(this.name + " has a Black Jack!");
        printWin();
    }

    public void printWin() {
        System.out.println(this.name + " won " + this.bet);
    }

    public void printLose() {
        System.out.println(this.name + " lost " + this.bet);
    }

    public void printPush() {
        System.out.println(this.name + " pushed.");
    }

    public void printWinnings() {
        System.out.println(this.name + " has " + this.winnings + " winnings.");
    }



    @Override
    public String toString() {
        return hands.getCurrentHand().toString();
    }


}

