package org.task.solution.ranking_validators.pair.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;
import org.task.model.Hand;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.task.constants.ValueEnum.*;

class PairValidatorImplTest {

    Hand hand;
    PairValidatorImpl pairValidator;

    @BeforeEach
    void setUp() {

        this.pairValidator = new PairValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand, should return true")
    void isCompatibleWith_compatibleHand_shouldReturnTrue() {

        hand = buildHand(THREE);

        var result = this.pairValidator.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand, should return false")
    void isCompatibleWith_nonCompatibleHand_shouldReturnFalse() {

        hand = buildHand(NINE);

        var result = this.pairValidator.isCompatibleWith(this.hand);

        assertFalse(result);
    }

    private Hand buildHand(ValueEnum second) {

        return new Hand(getCards(second));
    }

    private List<Card> getCards(ValueEnum second) {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(second));
        listOfCards.add(getCard(ValueEnum.TWO));
        listOfCards.add(getCard(ValueEnum.SIX));
        listOfCards.add(getCard(ValueEnum.FIVE));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }
}