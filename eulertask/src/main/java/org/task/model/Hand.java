package org.task.model;

import org.task.CardValuesComparator;
import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    private RankingEnum rankingEnum;

    private ValueEnum highestValueRanking;

    private boolean isOrdered;

    public Hand(List<Card> cards) {

        this.cards = cards;
        this.rankingEnum = null;
        this.highestValueRanking = null;
        this.isOrdered = false;
    }

    public List<Card> getCards() {

        return cards;
    }

    public RankingEnum getRankingEnum() {
        return rankingEnum;
    }

    public ValueEnum getHighestValueRanking() {
        return highestValueRanking;
    }

    public void setRankingEnum(RankingEnum ranking) {

        this.rankingEnum = ranking;
    }

    public void setHighestValueRanking(ValueEnum value) {

        this.highestValueRanking = value;
    }

    public Card getNCard(int cardNumber) {

        if (Boolean.FALSE.equals(isOrdered)) {

            this.cards.sort(new CardValuesComparator());
            this.isOrdered = true;
        }

        return this.cards.get(cardNumber);
    }
}
