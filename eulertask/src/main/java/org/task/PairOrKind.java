package org.task;

import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Pairs;
import org.task.model.Ranking;

import java.util.HashSet;
import java.util.Set;

public class PairOrKind {

    public Ranking get(Hand hand) {

        if (isFourOfAKind(hand)) {

            return createRanking(RankingEnum.FOUR_OF_A_KIND, hand.getNCard(2));
        }

        if (isTreeOfAKind(hand)) {
            if (isFullHouse(hand)) {

                return createRanking(RankingEnum.FULL_HOUSE, hand.getNCard(4));
            }

            return createRanking(RankingEnum.THREE_OF_A_KIND, hand.getNCard(2));
        }

        if (isPair(hand)) {

            var pairs = countPairs(hand);
            var pairsCount = pairs.pairsCount();
            var card = pairs.highestValueCard();

            if (pairsCount == 1) {

                return createRanking(RankingEnum.PAIR, card);
            }
            if (pairsCount == 2) {
                return createRanking(RankingEnum.TWO_PAIRS, card);
            }
        }

        return null;
    }

    private boolean isFullHouse(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var thirdCardValue = hand.getNCard(2).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return (firstCardValue == thirdCardValue && fourthCardValue == fifthCardValue)
                || (firstCardValue == secondCardValue && thirdCardValue == fifthCardValue);
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

    private Pairs countPairs(Hand hand) {

        var countPairs = 0;
        Card highestCardValue = null;
        Set<ValueEnum> set = new HashSet<>();

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue();
            var nextCardValue = hand.getNCard(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {
                if (set.isEmpty()) {

                    set.add(currentCardValue);
                    highestCardValue = hand.getNCard(i);
                    countPairs++;
                } else if (!set.contains(currentCardValue)) {

                    set.add(currentCardValue);
                    highestCardValue = getHighestCard(highestCardValue, hand.getNCard(i));
                    countPairs++;
                }
            }
        }

        return new Pairs(countPairs, highestCardValue);
    }

    private static Card getHighestCard(Card highestCardValue, Card currentCard) {

        return highestCardValue.getValue().getValueOrdinal() > currentCard.getValue().getValueOrdinal() ?
                highestCardValue :
                currentCard;
    }

    private Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
