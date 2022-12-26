package app.game;

import app.dealer.Dealer;
import app.exception.BetException;
import app.exception.HandException;
import app.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {


    public final List<Player> players;
    public int activePlayer = 0;
    public Dealer dealer;
    public Scanner sc = new Scanner(System.in);
//    public final double minBet = 1;

    public Game(List<Player> players, int numDecks) {
        this.players = players;
        this.dealer = new Dealer(players, numDecks);
    }

    public Game(List<Player> players) {
        this.players = players;
        this.dealer = new Dealer(players);
    }

    public Game(Player player) {
        this.players = new ArrayList<>();
        this.players.add(player);
        this.dealer = new Dealer(this.players);
    }

    public Game() {
        this.players = new ArrayList<>();
//        players.add(new Player());
        this.dealer = new Dealer(this.players);
    }

    public Game(int numDecks) {
        this.players = new ArrayList<Player>();
        players.add(new Player());
        this.dealer = new Dealer(this.players, numDecks);
    }

    public void addPlayer(String name) {
        if(name.equalsIgnoreCase("")) {
            this.players.add(new Player());
        } else {
            this.players.add(new Player(name));
        }

        System.out.println("Player " + this.players.size() + " '" + name + "' " + "has been added.");

    }



    public void start() throws HandException {

        System.out.println(welcomeMessage());
        System.out.println("------------------------------------");

        this.addPlayer(sc.nextLine());

        System.out.println(continueMessage());
        System.out.println("------------------------------------");

        while (!sc.nextLine().equalsIgnoreCase("q") && this.players.size() > 0) {

            bets();

            dealer.dealOpeningCards();

            printTurn();

            if(dealer.checkHiddenBlackjack()) {
                dealer.printWin();
            } else {
                turns();

                dealer.play();

                results();
            }

            dealer.reset();

            System.out.println(continueMessage());
            System.out.println("------------------------------------");
        }

        end();
    }

    public boolean checkWin(int player) {

        return players.get(player).getCurrentHandValue() > dealer.getHandValue();
    }

    public boolean checkPush(int player) {
        return players.get(player).getCurrentHandValue() == dealer.getHandValue();
    }

    public void bets() {
        for(int i = 0; i < players.size(); i++) {

            Player currentPlayer = players.get(i);
            currentPlayer.printWinnings();
            printPlaceBets();

            validateBet(currentPlayer);

        }
    }

    public double validateBet(Player currentPlayer) {
        while(true) {
            try {
                double bet = sc.nextDouble();
                currentPlayer.placeBet(bet);
                return bet;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Bet must be numeric");
                printBetMessage();
                sc.next();
            } catch (BetException e) {
                System.out.println(e.getMessage());
                printBetMessage();
                sc.nextLine();
            }
        }
    }

    public void turns() throws HandException {
        for(int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            new Turn(currentPlayer, dealer, sc);
        }
    }

    public void results() {
        for(int i = 0; i < players.size(); i++) {
            Player currentPlayer = players.get(i);
            if(currentPlayer.checkBlackJack()) {
                currentPlayer.blackJackWin();
            } else if((dealer.isBusted() || checkWin(i)) && !currentPlayer.isBusted()) {
                currentPlayer.win();
            } else if(checkPush(i)) {
                currentPlayer.push();
            } else {
                currentPlayer.lose();
                dealer.printWin();
            }

            currentPlayer.printWinnings();
            if(currentPlayer.checkZeroWinnings()) {
                currentPlayer.printZeroWinningsLoss();
                this.players.remove(i);
                if(this.players.size() == 0) return;
            } else {
                currentPlayer.reset();
            }

        }
    }


    public void end() {
        System.out.println("Thanks for playing!");
        sc.close();
        System.exit(0);
    }


    public void printActivePlayerHand() {
        System.out.println(players.get(activePlayer).toString() + System.lineSeparator());
    }

    public void printTurn() {
        dealer.printOpeningHand();
        System.out.println("------------------------------------");
        printActivePlayerHand();
    }

    public void printPlaceBets() {
        System.out.println("Please place bets:");
        System.out.println("------------------------------------");
    }

    public void printBetMessage() {
        System.out.println("Please enter a valid bet:");
        System.out.println("------------------------------------");
    }




    public String welcomeMessage() {
        return "Welcome to CLI BlackJack" + System.lineSeparator() + "You can exit at anytime by entering 'q'" + System.lineSeparator() + "Please enter your name:";
    }

    public String continueMessage() {
        return "Press 'q' to quit, and any other key to continue:";
    }

}
