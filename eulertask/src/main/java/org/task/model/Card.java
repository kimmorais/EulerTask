package org.task.model;

import org.task.constants.Suit;
import org.task.constants.Value;

public class Card {

    private final Value value;
    private final Suit suit;

    public Card(Value value, Suit suit) {

        this.value = value;
        this.suit = suit;
    }

    public Card(String s) {

        this(Value.valueOf(s.charAt(0)), Suit.valueOf(s.charAt(1)));
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
}
