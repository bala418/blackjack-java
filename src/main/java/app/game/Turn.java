package app.game;

import app.dealer.Dealer;
import app.exception.HandException;
import app.hand.Hand;
import app.player.Player;

import java.util.Scanner;

public class Turn {

    //If up card is ace, ask for insurance and check if black jack
    //First player turn
    //Check if black jack
    //If not, continue and get cards

//    public static final String[] actions = { "h", "st", "sp", "d"};
//    public static final String actionMessaage = "Please stand, hit, or double:";
//    public static final String splitActionMessage = "Please stand, hit, split, or double:";
//    public static final String hitOrStandMessage = "Please stand or hit";

    public static final ActionSingleton ACTION_SET = ActionSingleton.getInstance();
    private static int idCounter = 0;

    public int id;



    public Player activePlayer;
    public Dealer dealer;
    public Scanner playerInput;
    public String inputNextLine;

    public Turn(Player activePlayer, Dealer dealer, Scanner playerInput) throws HandException {
        this.id= ++idCounter;
        this.activePlayer = activePlayer;
        this.dealer = dealer;
        this.playerInput = playerInput;
        this.start();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public void start() throws HandException {
        int currentHandIndex = 0;

        for(int i = 0; i < activePlayer.getHands().size(); i++) {
            activePlayer.setCurrentHand(currentHandIndex);
            if(this.activePlayer.checkBlackJack()) {
                activePlayer.printBlackJack();
                return;
            }
            try {
                playerAct();
            } catch (TurnException ex) {
                ex.printStackTrace();
            }
        }
    }


//    public void insurance(Scanner sc) {
//        while (!playerInput.nextLine().toLowerCase().equals("y") || !playerInput.toLowerCase().equals("n")) {
//            System.out.println("Please enter 'y' or 'n'");
//        }
//    }

    public void playerAct() throws TurnException, HandException {

        //Validation is not required on below logic because check input handles if its valid for the given hand
        if(activePlayer.checkBlackJack()) return;

        this.checkInput();

        while (inputNextLine.equalsIgnoreCase("h") && activePlayer.getCurrentHand().getValue() < 21) {
            hit();

            if (activePlayer.checkBust()) return;
            printActionMessage();
            System.out.println("------------------------------------");
            this.checkInput();
        }

        if(inputNextLine.equalsIgnoreCase("st")) {
            stand();
        } else if(inputNextLine.equalsIgnoreCase("d")) {
            doubleDown();
        } else if(inputNextLine.equalsIgnoreCase("sp")) {
            try {
                split();
            } catch (HandException ex) {
                throw new TurnException(ex);
            } catch (TurnException ex) {
                ex.printStackTrace();
            }
        }


    }


    public void hit () {
        activePlayer.hit(dealer.dealFaceUp());
        if(activePlayer.getCurrentHand().getValue() > 21) {
            activePlayer.printBust();
        } else {
            activePlayer.printHand();
        }
    }

    public void stand() {
        activePlayer.printStand();
    }

    public void split() throws HandException, TurnException {
        if(!activePlayer.getCurrentHand().canSplit()) throw new TurnException("Invalid input because hand cannot be split");
        activePlayer.split(dealer.dealFaceUp(), dealer.dealFaceUp());
    }

    public void doubleDown() {
        activePlayer.doubleDown(dealer.dealFaceUp());
        activePlayer.printHand();
    }

    //
    public void checkInput() throws HandException {
        inputNextLine = this.playerInput.nextLine();
        while(ACTION_SET.checkInput(inputNextLine, activePlayer.getCurrentHand())) {
            printActionMessage();
            System.out.println("------------------------------------");
            inputNextLine = this.playerInput.nextLine();
        }
    }

    public void printActionMessage() throws HandException {
        System.out.println(System.lineSeparator() + generateActionMessage());
    }

    public String generateActionMessage() throws HandException {
        Hand currentHand = activePlayer.getCurrentHand();
        int actionInt = ACTION_SET.getActionSet(currentHand);
        return ACTION_SET.getMessage(actionInt);
    }


}


class TurnException extends Exception {
    public TurnException(String message) {
        super(message);
    }

    public TurnException(String message, Throwable cause) {
        super(message, cause);
    }

    public TurnException(Throwable cause) {
        super(cause);
    }
}