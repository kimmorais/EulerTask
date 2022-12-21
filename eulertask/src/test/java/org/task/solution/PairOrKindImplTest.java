package org.task.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.task.constants.RankingEnum;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;
import org.task.solution.interfaces.impl.PairOrKindImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.task.constants.RankingEnum.*;
import static org.task.constants.ValueEnum.*;
import static org.task.constants.ValueEnum.FIVE;

class PairOrKindImplTest {

    private Hand hand;
    private PairOrKindImpl pairOrKind;

    @BeforeEach
    void setUp() {

        pairOrKind = new PairOrKindImpl();
    }

    @Test
    @DisplayName("Given a valid hand for FOUR_OF_A_KIND, should return Ranking with this enum and the higher card inside")
    void get_validHandForFourOfAKind_returnFourOfAKindRanking() {

        hand = buildHand(THREE, THREE, THREE, THREE, FIVE);
        var expectedRanking = Optional.of(buildExpectedRanking(FOUR_OF_A_KIND, getCard(THREE)));

        var result = pairOrKind.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for FULL_HOUSE, should return Ranking with this enum and the higher card inside")
    void get_validHandForFullHouse_returnFullHouseRanking() {

        hand = buildHand(THREE, THREE, THREE, FIVE, FIVE);
        var expectedRanking = Optional.of(buildExpectedRanking(FULL_HOUSE, getCard(THREE)));

        var result = pairOrKind.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for PAIR, should return Ranking with this enum and the higher card inside")
    void get_validHandForPair_returnPairRanking() {

        hand = buildHand(THREE, THREE, TWO, SIX, FIVE);
        var expectedRanking = Optional.of(buildExpectedRanking(PAIR, getCard(THREE)));

        var result = pairOrKind.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for TWO_PAIRS, should return Ranking with this enum and the higher card inside")
    void get_validHandForTwoPairs_returnTwoPairsRanking() {

        hand = buildHand(THREE, THREE, FOUR, FOUR, FIVE);
        var expectedRanking = Optional.of(buildExpectedRanking(TWO_PAIRS, getCard(FOUR)));

        var result = pairOrKind.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for THREE_OF_A_KIND, should return Ranking with this enum and the higher card inside")
    void get_validHandForThreeOfAKind_returnThreeOfAKindRanking() {

        hand = buildHand(THREE, THREE, THREE, FOUR, FIVE);
        var expectedRanking = Optional.of(buildExpectedRanking(THREE_OF_A_KIND, getCard(THREE)));

        var result = pairOrKind.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for STRAIGHT_FLUSH, should return empty optional")
    void get_validHandForStraightFlush_returnNull() {

        hand = buildHand(NINE, TEN, JACK, QUEEN, KING);

        var result = pairOrKind.get(hand);

        assertThat(result).isEmpty();
    }

    private Ranking buildExpectedRanking(RankingEnum rankingEnum, Card card) {

        return new Ranking(rankingEnum, card);
    }

    private Hand buildHand(ValueEnum first, ValueEnum second, ValueEnum third, ValueEnum fourth, ValueEnum fifth) {

        return new Hand(getCards(first, second, third, fourth, fifth));
    }

    private List<Card> getCards(ValueEnum first, ValueEnum second, ValueEnum third, ValueEnum fourth, ValueEnum fifth) {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(first));
        listOfCards.add(getCard(second));
        listOfCards.add(getCard(third));
        listOfCards.add(getCard(fourth));
        listOfCards.add(getCard(fifth));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }
}