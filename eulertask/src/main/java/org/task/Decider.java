package org.task;

import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;
import org.task.model.*;

import java.util.List;

public class Decider {

    private Decider() {}

    public static boolean playerOneWins(Round round) {
        var pOneHand = round.playerOneHand();
        var pTwoHand = round.playerTwoHand();
        
        var pOneRanking = defineHanking(pOneHand);

        pOneHand.setRankingEnum(pOneRanking.rankingEnum());
        pOneHand.setHighestValueRanking(pOneRanking.card().getValue());

        var pTwoRanking = defineHanking(pTwoHand);

        pTwoHand.setRankingEnum(pTwoRanking.rankingEnum());
        pTwoHand.setHighestValueRanking(pTwoRanking.card().getValue());

        return decide(pOneHand, pTwoHand);
    }

    private static boolean decide(Hand pOneHand, Hand pTwoHand) {

        var pOneHandRankingValue = pOneHand.getRankingEnum().getValue();
        var pTwoHandRankingvalue = pTwoHand.getRankingEnum().getValue();

        if (pOneHandRankingValue > pTwoHandRankingvalue) {
            return true;
        }
        if (pOneHandRankingValue.equals(pTwoHandRankingvalue)) {

            return decideEquality(pOneHand, pTwoHand);
        }

        return false;
    }

    private static boolean decideEquality(Hand pOneHand, Hand pTwoHand) {

        var pOneCardValue = pOneHand.getHighestValueRanking().getValueOrdinal();
        var pTwoCardValue = pTwoHand.getHighestValueRanking().getValueOrdinal();

        if (pOneCardValue > pTwoCardValue) {

            return true;
        }
        if (pOneCardValue.equals(pTwoCardValue)) {

            var pOneCards = pOneHand.getCards();
            var pTwoCards = pTwoHand.getCards();

            for (int i = 0; i < 5; i++) {

                var pOneHighestCardValue = pOneCards.get(i).getValue().getValueOrdinal();
                var pTwoHighestCardValue = pTwoCards.get(i).getValue().getValueOrdinal();

                if (pOneHighestCardValue > pTwoHighestCardValue) {
                    return true;
                }
                if (pTwoHighestCardValue > pOneHighestCardValue) {
                    return false;
                }
            }
        }
        return false;
    }

    private static Ranking defineHanking(Hand hand) {

        var cards = hand.getCards();
        var flush = isFlush(cards);
        var straight = isStraight(cards);
        var royal = isRoyal(cards);

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
        if (isFourOfAKind(cards)) {

            return createRanking(RankingEnum.FOUR_OF_A_KIND, hand.getNCard(2));
        }

        var isThreeOfAKind = isTreeOfAKind(cards);

        if (isPair(cards)) {

            if (isThreeOfAKind) {

                var card = findHighestCard(cards);
                return createRanking(RankingEnum.FULL_HOUSE, card);
            }

            var pairs = howManyPairs(cards);
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

        return createRanking(RankingEnum.HIGH_CARD, hand.getNCard(4));
    }

    private static Card findHighestCard(List<Card> cards) {

        var highestCard = cards.get(0);

        for (int i = 1; i < 5; i++) {

            if (highestCard.getValue().getValueOrdinal() < cards.get(i).getValue().getValueOrdinal()) {

                highestCard = cards.get(i);
            }
        }

        return highestCard;
    }

    private static boolean isFlush(List<Card> cards) {

        for (int i = 0; i < 4; i++) {

            var currentCardSuit = cards.get(i).getSuit();
            var nextCardSuit = cards.get(i + 1).getSuit();

            if (currentCardSuit != nextCardSuit) {

                return false;
            }
        }

        return true;
    }

    private static boolean isStraight(List<Card> cards) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = cards.get(i).getValue().getValueOrdinal();
            var nextCardValue = cards.get(i + 1).getValue().getValueOrdinal();

            if (currentCardValue + 1 != nextCardValue) {

                return false;
            }
        }

        return true;
    }

    private static boolean isRoyal(List<Card> cards) {

        return cards.get(0).getValue() == ValueEnum.TEN
                && cards.get(1).getValue() == ValueEnum.JACK
                && cards.get(2).getValue() == ValueEnum.QUEEN
                && cards.get(3).getValue() == ValueEnum.KING
                && cards.get(4).getValue() == ValueEnum.ACE;
    }

    private static boolean isFourOfAKind(List<Card> cards) {

        var firstCardValue = cards.get(0).getValue();
        var fourthCardValue = cards.get(3).getValue();
        var secondCardValue = cards.get(1).getValue();
        var fifthCardValue = cards.get(4).getValue();

        return firstCardValue == fourthCardValue || secondCardValue == fifthCardValue;
    }

    private static boolean isPair(List<Card> cards) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = cards.get(i).getValue();
            var nextCardValue = cards.get(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {
                return true;
            }
        }

        return false;
    }

    private static boolean isTreeOfAKind(List<Card> cards) {

        var firstCardValue = cards.get(0).getValue();
        var secondCardValue = cards.get(1).getValue();
        var thirdCardValue = cards.get(2).getValue();
        var fourthCardValue = cards.get(3).getValue();
        var fifthCardValue = cards.get(4).getValue();

        return firstCardValue == thirdCardValue
                || secondCardValue == fourthCardValue
                || thirdCardValue == fifthCardValue;
    }

    private static Pairs howManyPairs(List<Card> cards) {

        var countPairs = 0;
        Card highestCardValue = null;

        for (int i = 0; i < 4; i++) {

            var currentCardValue = cards.get(i).getValue();
            var nextCardValue = cards.get(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {

                countPairs++;
                if (highestCardValue == null) {

                    highestCardValue = cards.get(i);
                }
                if (highestCardValue != null && currentCardGreaterThanHighestCard(highestCardValue.getValue().getValueOrdinal(), currentCardValue.getValueOrdinal())) {

                    highestCardValue = cards.get(i);
                }
            }
        }

        return new Pairs(countPairs, highestCardValue);
    }

    private static boolean currentCardGreaterThanHighestCard(Integer highestCardValue, Integer currentCardValue) {
        return highestCardValue < currentCardValue;
    }

    private static Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
