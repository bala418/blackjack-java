package app.card;

import java.util.Objects;

public class Card {
    public static final int[] values = {2,3,4,5,6,7,8,9,10};
    public static final String[] suits = {"SPADES","HEARTS", "DIAMONDS", "CLUBS"};
    public static final int unicodeCardBack = 0x1F0A0;

    public static final char[] royals = {'J', 'Q','K'};

    public final int value;

    private static int idCounter = 0;
    private final int id;


    private final int unicodeSuit;
    private final String suit;

    private boolean hidden;


    public Card(int value, String suit) {
        this.id = ++idCounter;
        this.value = value;
        this.unicodeSuit = unicodeSuitLookup(suit);
        this.suit = suit;
        this.hidden = false;
    }

    public Card(String suit) {
        this.id = ++idCounter;
        this.value = 10;
        this.unicodeSuit = unicodeSuitLookup(suit);
        this.suit = suit;
        this.hidden = false;
    }


    private int unicodeSuitLookup(String suit) {
        if(suit.equals("SPADES")) return 0x2660;

        if(suit.equals("HEARTS")) return 0x2665;

        if(suit.equals("DIAMONDS")) return 0x2666;

        if(suit.equals("CLUBS")) return 0x2663;

        return 0x0000FFFD;
    }

    public int getValue() {
        return value;
    }

    public int getUnicodeSuit() {
        return this.hidden ? unicodeCardBack : this.unicodeSuit;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }


    public void print() {
        System.out.println(toString());
    }

    public boolean canSplit(Object o) {
        if(o == this) return true;
        if(!(o instanceof Card)) return false;
        Card card = (Card) o;

        return this.value == card.value;
    }


    @Override
    public String toString() {
        return this.hidden ? "" : String.valueOf(Character.toChars(this.getUnicodeSuit())) + (this.getValue());
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Card)) return false;
        Card card = (Card) o;

        return this.value == card.value && unicodeSuit == card.unicodeSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, unicodeSuit, suit);
    }
}
