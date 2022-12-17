package org.task;

import org.task.constants.RankingEnum;
import org.task.model.Card;

import java.util.Collections;
import java.util.List;

public class HandHanking {

    private final List<Card> cards;
    private final RankingEnum rankingEnum;

    public HandHanking(List<Card> cards) {

        cards.sort(new CardValuesComparator());
        this.cards = cards;
        this.rankingEnum = defineRanking(cards);
    }

    private RankingEnum defineRanking(List<Card> cards) {
        return RankingEnum.FULL_HOUSE;
    }
}
