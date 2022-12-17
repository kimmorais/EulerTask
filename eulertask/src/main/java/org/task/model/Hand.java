package org.task.model;

import org.task.HandHanking;

import java.util.List;

public class Hand {

    private final List<Card> cards;
    private final HandHanking handHanking;

    public Hand(List<Card> cards, HandHanking handHanking) {
        this.cards = cards;
        this.handHanking = handHanking;
    }

    public Hand(List<Card> cards) {
        this.cards = cards;
        this.handHanking = new HandHanking(cards);
    }
}
