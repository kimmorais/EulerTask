package org.task.solution.ranking_validators.royal.impl;

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

class RoyalValidatorImplTest {

    private Hand hand;
    private RoyalValidatorImpl impl;

    @BeforeEach
    void setUp() {

        this.impl = new RoyalValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand with Royal Ranking, should return true")
    void isCompatibleWith_compatibleHand_returnTrue() {

        this.hand = buildHand(TEN, JACK, QUEEN, KING, ACE);

        var result = this.impl.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand with Royal Ranking, should return false")
    void isCompatibleWith_nonCompatibleHand_returnTrue() {

        this.hand = buildHand(TWO, JACK, EIGHT, SEVEN, ACE);

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