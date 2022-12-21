package org.task.solution.ranking_validators.four_of_a_kind.impl;

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
import static org.task.constants.ValueEnum.FIVE;
import static org.task.constants.ValueEnum.THREE;

class FourOfAKindValidatorImplTest {

    Hand hand;
    FourOfAKindValidatorImpl fourOfAKindValidator;

    @BeforeEach
    void setUp() {

        this.fourOfAKindValidator = new FourOfAKindValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand, should return true")
    void isCompatibleWith_compatibleHand_returnTrue() {

        this.hand = buildHand(THREE);

        var result = this.fourOfAKindValidator.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand, should return false")
    void isCompatibleWith_nonCompatibleHand_returnFalse() {

        this.hand = buildHand(FIVE);

        var result = this.fourOfAKindValidator.isCompatibleWith(this.hand);

        assertFalse(result);
    }

    private Hand buildHand(ValueEnum third) {

        return new Hand(getCards(third));
    }

    private List<Card> getCards(ValueEnum third) {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(third));
        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(ValueEnum.FIVE));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }
}