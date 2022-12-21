package org.task.solution.ranking_validators.three_of_a_kind.impl;

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

class ThreeOfAKindValidatorImplTest {

    Hand hand;
    ThreeOfAKindValidatorImpl threeOfAKindValidator;

    @BeforeEach
    void setUp() {

        this.threeOfAKindValidator = new ThreeOfAKindValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand, should return true")
    void isCompatibleWith_compatibleHand_shouldReturnTrue() {

        this.hand = buildHand(THREE);

        var result = this.threeOfAKindValidator.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand, should return false")
    void isCompatibleWith_nonCompatibleHand_shouldReturnFalse() {

        this.hand = buildHand(NINE);

        var result = this.threeOfAKindValidator.isCompatibleWith(this.hand);

        assertFalse(result);
    }

    private Hand buildHand(ValueEnum second) {

        return new Hand(getCards(second));
    }

    private List<Card> getCards(ValueEnum second) {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(second));
        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(ValueEnum.FOUR));
        listOfCards.add(getCard(ValueEnum.FIVE));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }
}