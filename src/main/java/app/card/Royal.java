package app.card;

import java.util.Objects;

public class Royal extends Card {
    private final char type;
    private final int value;

    public Royal(String suit, char type) {
        super(suit);
        this.type = type;
        this.value = 10;
    }

    public char getType() {
        return this.type;
    }

    @Override
    public boolean canSplit(Object o) {
        if(o == this) return true;
        if(!(o instanceof Royal)) return false;
        Royal card = (Royal) o;

        return Objects.equals(this.getType(), card.getType());
    }

    @Override
    public String toString() {
        return String.valueOf(Character.toChars(this.getUnicodeSuit())) + (this.isHidden() ? "" : this.getType());
    }

    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Royal)) return false;
        Royal card = (Royal) o;

        return this.getUnicodeSuit() == card.getUnicodeSuit() && Objects.equals(this.getType(), card.getType()) && this.getValue() == card.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getType(), this.getUnicodeSuit(), this.getSuit());
    }
}
