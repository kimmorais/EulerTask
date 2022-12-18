package org.task;

import org.task.constants.RankingEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Round;

import java.util.List;

public class Decider {

    private Decider() {}

    public static boolean playerOneWins(Round round) {
        var pOneHand = round.playerOneHand();
        var pTwoHand = round.playerTwoHand();
        
        var pOneRankingAndHighestValue = defineHanking(pOneHand);

        pOneHand.setRankingEnum((RankingEnum) pOneRankingAndHighestValue[0]);
        pOneHand.setHighestValueRanking(((Card) pOneRankingAndHighestValue[1]).getValue());

        var pTwoRankingAndHighestValue = defineHanking(pTwoHand);

        pTwoHand.setRankingEnum((RankingEnum) pTwoRankingAndHighestValue[0]);
        pTwoHand.setHighestValueRanking(((Card) pTwoRankingAndHighestValue[1]).getValue());

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

    private static Object[] defineHanking(Hand hand) {

        var cards = hand.getCards();
        var flush = isFlush(cards);
        var straight = isStraight(cards);
        var royal = isRoyal(cards);

        if (flush) {

            if (royal) {
                return new Object[]{RankingEnum.ROYAL_FLUSH, hand.getNCard(4)};
            }
            if (straight) {
                return new Object[]{RankingEnum.STRAIGHT_FLUSH, hand.getNCard(4)};
            }
            return new Object[]{RankingEnum.FLUSH, hand.getNCard(4)};
        }
        if (straight) {

            return new Object[]{RankingEnum.STRAIGHT, hand.getNCard(4)};
        }
        if (isFourOfAKind(cards)) {

            return new Object[]{RankingEnum.FOUR_OF_A_KIND, hand.getNCard(2)};
        }

        var isThreeOfAKind = isTreeOfAKind(cards);

        if (isPair(cards)) {

            if (isThreeOfAKind) {

                var card = findHighestCard(cards);
                return new Object[]{RankingEnum.FULL_HOUSE, card};
            }

            var pairs = howManyPairs(cards);
            var pairQuantity = (Integer) pairs[0];
            var card = (Card) pairs[1];

            if (pairQuantity == 1) {

                return new Object[]{RankingEnum.PAIR, card};
            }
            if (pairQuantity == 2) {
                    return new Object[]{RankingEnum.TWO_PAIRS, card};
            }
        }

        if (isThreeOfAKind) {

            return new Object[]{RankingEnum.THREE_OF_A_KIND, hand.getNCard(2)};
        }

        return new Object[]{RankingEnum.HIGH_CARD, hand.getNCard(4)};
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

    private static Object[] howManyPairs(List<Card> cards) {

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

        return new Object[]{countPairs, highestCardValue};
    }

    private static boolean currentCardGreaterThanHighestCard(Integer highestCardValue, Integer currentCardValue) {
        return highestCardValue < currentCardValue;
    }
}
