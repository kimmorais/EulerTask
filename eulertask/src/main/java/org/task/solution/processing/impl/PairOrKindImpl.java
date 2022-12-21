package org.task.solution.processing.impl;

import org.task.constants.RankingEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Pairs;
import org.task.model.Ranking;
import org.task.solution.processing.PairOrKind;
import org.task.solution.ranking_validators.four_of_a_kind.FourOfAKindValidator;
import org.task.solution.ranking_validators.full_house.FullHouseValidator;
import org.task.solution.ranking_validators.pair.PairValidator;
import org.task.solution.ranking_validators.three_of_a_kind.ThreeOfAKindValidator;

import java.util.Optional;

public class PairOrKindImpl implements PairOrKind {

    private final FourOfAKindValidator fourOfAKindValidator;
    private final ThreeOfAKindValidator threeOfAKindValidator;
    private final FullHouseValidator fullHouseValidator;
    private final PairValidator pairValidator;

    public PairOrKindImpl(FourOfAKindValidator fourOfAKindValidator, ThreeOfAKindValidator threeOfAKindValidator, FullHouseValidator fullHouseValidator, PairValidator pairValidator) {

        this.fourOfAKindValidator = fourOfAKindValidator;
        this.threeOfAKindValidator = threeOfAKindValidator;
        this.fullHouseValidator = fullHouseValidator;
        this.pairValidator = pairValidator;
    }

    @Override
    public Optional<Ranking> get(Hand hand) {

        var isFourOfAKind = this.fourOfAKindValidator.isCompatibleWith(hand);
        if (isFourOfAKind) {

            return Optional.of(createRanking(RankingEnum.FOUR_OF_A_KIND, hand.getNCard(2)));
        }

        var isThreeOfAKind = this.threeOfAKindValidator.isCompatibleWith(hand);
        if (isThreeOfAKind) {
            var isFullHouse = this.fullHouseValidator.isCompatibleWith(hand);
            if (isFullHouse) {

                return Optional.of(createRanking(RankingEnum.FULL_HOUSE, hand.getNCard(2)));
            }

            return Optional.of(createRanking(RankingEnum.THREE_OF_A_KIND, hand.getNCard(2)));
        }

        var isPair = this.pairValidator.isCompatibleWith(hand);
        if (isPair) {

            var pairs = countPairs(hand);
            var pairsCount = pairs.pairsCount();
            var card = pairs.highestValueCard();

            if (pairsCount == 1) {

                return Optional.of(createRanking(RankingEnum.PAIR, card));
            }
            if (pairsCount == 2) {

                return Optional.of(createRanking(RankingEnum.TWO_PAIRS, card));
            }
        }

        return Optional.empty();
    }

    private Pairs countPairs(Hand hand) {

        var countPairs = 0;
        Card highestCardValue = null;

        for (int i = 0; i < 4; i++) {

            var currentCardValue = hand.getNCard(i).getValue();
            var nextCardValue = hand.getNCard(i + 1).getValue();

            if (currentCardValue == nextCardValue)  {

                countPairs++;
                highestCardValue = highestCardValue == null ?
                        hand.getNCard(i) :
                        getHighestCard(highestCardValue, hand.getNCard(i));
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
