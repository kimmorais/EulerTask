package org.task;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Pairs;
import org.task.model.Ranking;

public class PairOrKind {

    public Ranking get(Hand hand) {

        if (isFourOfAKind(hand)) {

            return createRanking(RankingEnum.FOUR_OF_A_KIND, hand.getNCard(2));
        }

        var isThreeOfAKind = isTreeOfAKind(hand);

        if (isPair(hand)) {

            if (isThreeOfAKind) {

                var card = findHighestCard(hand);
                return createRanking(RankingEnum.FULL_HOUSE, card);
            }

            var pairs = howManyPairs(hand);
            var pairsCount = pairs.pairsCount();
            var card = pairs.highestValueCard();

            if (pairsCount == 1) {

                return createRanking(RankingEnum.PAIR, card);
            }
            if (pairsCount == 2) {
                return createRanking(RankingEnum.TWO_PAIRS, card);
            }
        }

        if (isThreeOfAKind) {

            return createRanking(RankingEnum.THREE_OF_A_KIND, hand.getNCard(2));
        }

        return null;
    }

    private boolean isFourOfAKind(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == fourthCardValue || secondCardValue == fifthCardValue;
    }

    private boolean isPair(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue();
            var nextCardValue = hand.getNCard(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {
                return true;
            }
        }

        return false;
    }

    private Card findHighestCard(Hand hand) {

        var highestCard = hand.getNCard(0);

        for (int i = 1; i < 5; i++) {

            if (highestCard.getValue().getValueOrdinal() < hand.getNCard(i).getValue().getValueOrdinal()) {

                highestCard = hand.getNCard(i);
            }
        }

        return highestCard;
    }

    private boolean isTreeOfAKind(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var thirdCardValue = hand.getNCard(2).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == thirdCardValue
                || secondCardValue == fourthCardValue
                || thirdCardValue == fifthCardValue;
    }

    private Pairs howManyPairs(Hand hand) {

        var countPairs = 0;
        Card highestCardValue = null;

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue();
            var nextCardValue = hand.getNCard(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {

                countPairs++;
                if (highestCardValue == null) {

                    highestCardValue = hand.getNCard(i);
                }
                if (highestCardValue != null && currentCardGreaterThanHighestCard(highestCardValue.getValue().getValueOrdinal(), currentCardValue.getValueOrdinal())) {

                    highestCardValue = hand.getNCard(i);
                }
            }
        }

        return new Pairs(countPairs, highestCardValue);
    }

    private boolean currentCardGreaterThanHighestCard(Integer highestCardValue, Integer currentCardValue) {

        return highestCardValue < currentCardValue;
    }

    private Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
