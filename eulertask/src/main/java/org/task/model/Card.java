package org.task.model;

import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;

public class Card {

    private final ValueEnum value;
    private final SuitEnum suit;

    public Card(ValueEnum value, SuitEnum suit) {

        this.value = value;
        this.suit = suit;
    }

    public Card(String s) {

        this(ValueEnum.fromValue(s.charAt(0)), SuitEnum.fromValue(s.charAt(1)));
    }
}
