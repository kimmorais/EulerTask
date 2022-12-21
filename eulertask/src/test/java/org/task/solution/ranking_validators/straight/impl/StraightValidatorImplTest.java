package org.task.solution.ranking_validators.straight.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.task.constants.ValueEnum.*;

class StraightValidatorImplTest {

    private Hand hand;
    private StraightValidatorImpl impl;

    @BeforeEach
    void setUp() {

        this.impl = new StraightValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand with Straight Ranking, should return true")
    void isCompatibleWith_compatibleHand_returnTrue() {

        this.hand = buildHand(TWO, THREE, FOUR, FIVE, SIX);

        var result = this.impl.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand with Straight Ranking, should return false")
    void isCompatibleWith_nonCompatibleHand_returnFalse() {

        this.hand = buildHand(FIVE, NINE, TEN, QUEEN, KING);

        var result = this.impl.isCompatibleWith(this.hand);

        assertFalse(result);
    }


    private Hand buildHand(ValueEnum first, ValueEnum second, ValueEnum third, ValueEnum fourth, ValueEnum fifth) {

        return new Hand(getCards(first, second, third, fourth, fifth));
    }

    private List<Card> getCards(ValueEnum first, ValueEnum second, ValueEnum third, ValueEnum fourth, ValueEnum fifth) {

        var cards = new ArrayList<Card>();

        cards.add(getCard(first));
        cards.add(getCard(second));
        cards.add(getCard(third));
        cards.add(getCard(fourth));
        cards.add(getCard(fifth));

        return cards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.DIAMONDS);
    }
}