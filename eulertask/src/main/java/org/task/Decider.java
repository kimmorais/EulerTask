package org.task;

import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;
import org.task.model.*;

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

        return createRanking(RankingEnum.HIGH_CARD, hand.getNCard(4));
    }

    private static Card findHighestCard(Hand hand) {

        var highestCard = hand.getNCard(0);

        for (int i = 1; i < 5; i++) {

            if (highestCard.getValue().getValueOrdinal() < hand.getNCard(i).getValue().getValueOrdinal()) {

                highestCard = hand.getNCard(i);
            }
        }

        return highestCard;
    }

    private static boolean isFlush(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardSuit = hand.getNCard(i).getSuit();
            var nextCardSuit = hand.getNCard(i + 1).getSuit();

            if (currentCardSuit != nextCardSuit) {

                return false;
            }
        }

        return true;
    }

    private static boolean isStraight(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue().getValueOrdinal();
            var nextCardValue = hand.getNCard(i + 1).getValue().getValueOrdinal();

            if (currentCardValue + 1 != nextCardValue) {

                return false;
            }
        }

        return true;
    }

    private static boolean isRoyal(Hand hand) {

        return hand.getNCard(0).getValue() == ValueEnum.TEN
                && hand.getNCard(1).getValue() == ValueEnum.JACK
                && hand.getNCard(2).getValue() == ValueEnum.QUEEN
                && hand.getNCard(3).getValue() == ValueEnum.KING
                && hand.getNCard(4).getValue() == ValueEnum.ACE;
    }

    private static boolean isFourOfAKind(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == fourthCardValue || secondCardValue == fifthCardValue;
    }

    private static boolean isPair(Hand hand) {

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue();
            var nextCardValue = hand.getNCard(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {
                return true;
            }
        }

        return false;
    }

    private static boolean isTreeOfAKind(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var thirdCardValue = hand.getNCard(2).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == thirdCardValue
                || secondCardValue == fourthCardValue
                || thirdCardValue == fifthCardValue;
    }

    private static Pairs howManyPairs(Hand hand) {

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

    private static boolean currentCardGreaterThanHighestCard(Integer highestCardValue, Integer currentCardValue) {
        return highestCardValue < currentCardValue;
    }

    private static Ranking createRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }
}
