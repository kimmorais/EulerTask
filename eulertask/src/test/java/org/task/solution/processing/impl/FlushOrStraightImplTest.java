package org.task.solution.processing.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.task.constants.RankingEnum;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;
import org.task.model.Ranking;
import org.task.solution.ranking_validators.flush.FlushValidator;
import org.task.solution.ranking_validators.royal.RoyalValidator;
import org.task.solution.ranking_validators.straight.StraightValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.task.constants.RankingEnum.*;
import static org.task.constants.ValueEnum.*;

@ExtendWith(MockitoExtension.class)
class FlushOrStraightImplTest {

    private Hand hand;

    @Mock
    private FlushValidator flushValidator;

    @Mock
    private StraightValidator straightValidator;

    @Mock
    private RoyalValidator royalValidator;

    @InjectMocks
    private FlushOrStraightImpl flushOrStraight;

    @Test
    @DisplayName("Given a valid hand for ROYAL_FLUSH, should return Ranking with this enum and the higher card inside")
    void get_validHandForRoyalFlush_returnRoyalFlushRanking() {

        hand = buildHand(TEN, JACK, QUEEN, KING, ACE);
        var expectedRanking = Optional.of(buildExpectedRanking(ROYAL_FLUSH, getCard(ACE)));

        when(this.flushValidator.isCompatibleWith(hand)).thenReturn(true);
        when(this.straightValidator.isCompatibleWith(hand)).thenReturn(false);
        when(this.royalValidator.isCompatibleWith(hand)).thenReturn(true);

        var result = flushOrStraight.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for STRAIGHT_FLUSH, should return Ranking with this enum and the higher card inside")
    void get_validHandForStraightFlush_returnStraightFlushRanking() {

        hand = buildHand(NINE, TEN, JACK, QUEEN, KING);
        var expectedRanking = Optional.of(buildExpectedRanking(STRAIGHT_FLUSH, getCard(KING)));

        when(this.flushValidator.isCompatibleWith(hand)).thenReturn(true);
        when(this.straightValidator.isCompatibleWith(hand)).thenReturn(true);
        when(this.royalValidator.isCompatibleWith(hand)).thenReturn(false);

        var result = flushOrStraight.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for FLUSH, should return Ranking with this enum and the higher card inside")
    void get_validHandForFlush_returnFlushRanking() {

        hand = buildHand(TWO, TEN, EIGHT, QUEEN, ACE);
        var expectedRanking = Optional.of(buildExpectedRanking(FLUSH, getCard(ACE)));

        when(this.flushValidator.isCompatibleWith(hand)).thenReturn(true);
        when(this.straightValidator.isCompatibleWith(hand)).thenReturn(false);
        when(this.royalValidator.isCompatibleWith(hand)).thenReturn(false);

        var result = flushOrStraight.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for STRAIGHT, should return Ranking with this enum and the higher card inside")
    void get_validHandForStraight_returnStraightRanking() {

        hand = buildStraightHand();
        var expectedRanking = Optional.of(buildExpectedRanking(STRAIGHT, getCard(SEVEN)));

        when(this.flushValidator.isCompatibleWith(hand)).thenReturn(false);
        when(this.straightValidator.isCompatibleWith(hand)).thenReturn(true);
        when(this.royalValidator.isCompatibleWith(hand)).thenReturn(false);

        var result = flushOrStraight.get(hand);

        assertEquals(expectedRanking, result);
    }

    @Test
    @DisplayName("Given a valid hand for PAIR, should return empty optional")
    void get_validHandForPair_returnNull() {

        hand = buildPairHand();

        when(this.flushValidator.isCompatibleWith(hand)).thenReturn(false);
        when(this.straightValidator.isCompatibleWith(hand)).thenReturn(false);
        when(this.royalValidator.isCompatibleWith(hand)).thenReturn(false);

        var result = flushOrStraight.get(hand);

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

    private Hand buildStraightHand() {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(new Card(ValueEnum.THREE, SuitEnum.HEARTS));
        listOfCards.add(new Card(ValueEnum.FOUR, SuitEnum.HEARTS));
        listOfCards.add(new Card(ValueEnum.FIVE, SuitEnum.DIAMONDS));
        listOfCards.add(new Card(ValueEnum.SIX, SuitEnum.HEARTS));
        listOfCards.add(new Card(ValueEnum.SEVEN, SuitEnum.HEARTS));

        return new Hand(listOfCards);
    }
    private Hand buildPairHand() {

        var list = new ArrayList<Card>();

        list.add(new Card(ValueEnum.THREE, SuitEnum.HEARTS));
        list.add(new Card(ValueEnum.THREE, SuitEnum.DIAMONDS));
        list.add(new Card(ValueEnum.TWO, SuitEnum.DIAMONDS));
        list.add(new Card(ValueEnum.SIX, SuitEnum.CLUBS));
        list.add(new Card(ValueEnum.FIVE, SuitEnum.SPADES));

        return new Hand(list);
    }
}