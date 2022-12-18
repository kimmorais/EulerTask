package org.task.model;

import org.jetbrains.annotations.NotNull;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;

import java.util.Objects;

public class Card implements Comparable<Card> {

    private final ValueEnum value;

    private final SuitEnum suit;

    public Card(ValueEnum value, SuitEnum suit) {

        this.value = value;
        this.suit = suit;
    }
    public Card(String s) {

        this(ValueEnum.fromValue(s.charAt(0)), SuitEnum.fromValue(s.charAt(1)));
    }

    @Override
    public int compareTo(@NotNull Card card) {

        return 0;
    }

    public ValueEnum getValue() {
        return value;
    }

    public SuitEnum getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, suit);
    }
}
