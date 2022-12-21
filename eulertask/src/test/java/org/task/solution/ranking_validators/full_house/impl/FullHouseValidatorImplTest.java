package org.task.solution.ranking_validators.full_house.impl;

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

class FullHouseValidatorImplTest {

    private Hand hand;
    private FullHouseValidatorImpl fullHouseValidator;

    @BeforeEach
    void setUp() {

        this.fullHouseValidator = new FullHouseValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand, should return true")
    void isCompatibleWith_compatibleHand_shouldReturnTrue() {

        this.hand = buildHand(FIVE);

        var result = this.fullHouseValidator.isCompatibleWith(this.hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand, should return false")
    void isCompatibleWith_nonCompatibleHand_shouldReturnFalse() {

        this.hand = buildHand(THREE);

        var result = this.fullHouseValidator.isCompatibleWith(this.hand);

        assertFalse(result);
    }

    private Hand buildHand(ValueEnum fourth) {

        return new Hand(getCards(fourth));
    }

    private List<Card> getCards(ValueEnum fourth) {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(ValueEnum.THREE));
        listOfCards.add(getCard(fourth));
        listOfCards.add(getCard(ValueEnum.FIVE));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }
}