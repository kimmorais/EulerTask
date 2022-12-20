package org.task.solution;

import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;

public class FlushOrStraight {

    public Ranking get(Hand hand) {

        var flush = isFlush(hand);
        var straight = isStraight(hand);
        var royal = isRoyal(hand);

        if (flush) {

            if (royal) {
                return createRanking(RankingEnum.ROYAL_FLUSH, hand.getNCard(4));
            }
            if (straight) {
                return createRanking(RankingEnum.STRAIGHT_FLUSH, hand.getNCard(4));
            }

            return createRanking(RankingEnum.FLUSH, hand.getNCard(4));
        }

        if (straight) {

            return createRanking(RankingEnum.STRAIGHT, hand.getNCard(4));
        }

        return null;
    }

    private boolean isFlush(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardSuit = hand.getNCard(i).getSuit();
            var nextCardSuit = hand.getNCard(i + 1).getSuit();

            if (currentCardSuit != nextCardSuit) {

                return false;
            }
        }

        return true;
    }

    private boolean isStraight(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue().getValueOrdinal();
            var nextCardValue = hand.getNCard(i + 1).getValue().getValueOrdinal();

            if (currentCardValue + 1 != nextCardValue) {

                return false;
            }
        }

        return true;
    }

    private boolean isRoyal(Hand hand) {

        return hand.getNCard(0).getValue() == ValueEnum.TEN
                && hand.getNCard(1).getValue() == ValueEnum.JACK
                && hand.getNCard(2).getValue() == ValueEnum.QUEEN
                && hand.getNCard(3).getValue() == ValueEnum.KING
                && hand.getNCard(4).getValue() == ValueEnum.ACE;
    }

    private Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
