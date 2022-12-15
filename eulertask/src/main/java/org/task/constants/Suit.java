package org.task.constants;

import java.util.HashMap;
import java.util.Map;

public enum Suit {

    SPADES('S'),
    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C');

    private final char suit;

    private static final Map<Character, Suit> MAP_OF_SUIT;

    static {
        MAP_OF_SUIT = new HashMap<>();

        for (var c: Suit.values()) {
            MAP_OF_SUIT.put(c.getSuit(), c);
        }
    }

    Suit(char suit) {
        this.suit = suit;
    }

    public char getSuit() {
        return suit;
    }

    public static Suit valueOf(char c) {

        return MAP_OF_SUIT.get(c);
    }
}
