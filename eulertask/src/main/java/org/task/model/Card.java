package org.task.model;

import org.jetbrains.annotations.NotNull;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;

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
}
