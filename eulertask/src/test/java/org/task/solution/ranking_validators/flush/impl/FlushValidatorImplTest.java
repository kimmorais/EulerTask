package org.task.solution.ranking_validators.flush.impl;

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

class FlushValidatorImplTest {

    private Hand hand;
    private FlushValidatorImpl impl;

    @BeforeEach
    void setUp() {

        this.impl = new FlushValidatorImpl();
    }

    @Test
    @DisplayName("Given a compatible hand with Flush, should return true")
    void isCompatibleWith_compatibleHand_returnTrue() {

        this.hand = buildHand();

        var result = this.impl.isCompatibleWith(hand);

        assertTrue(result);
    }

    @Test
    @DisplayName("Given a non-compatible hand with Flush, should return false")
    void isCompatibleWith_notCompatibleHand_return_false() {

        this.hand = buildHandNotFlush();

        var result = this.impl.isCompatibleWith(hand);

        assertFalse(result);
    }

    private Hand buildHand() {

        return new Hand(getCards());
    }

    private List<Card> getCards() {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCard(ValueEnum.TWO));
        listOfCards.add(getCard(ValueEnum.TEN));
        listOfCards.add(getCard(ValueEnum.EIGHT));
        listOfCards.add(getCard(ValueEnum.QUEEN));
        listOfCards.add(getCard(ValueEnum.ACE));

        return listOfCards;
    }

    private Card getCard(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.HEARTS);
    }

    private Hand buildHandNotFlush() {

        return new Hand(getCardsNotFlush());
    }

    private List<Card> getCardsNotFlush() {

        var listOfCards = new ArrayList<Card>();

        listOfCards.add(getCardNotFlush(ValueEnum.TWO));
        listOfCards.add(getCard(ValueEnum.TEN));
        listOfCards.add(getCard(ValueEnum.EIGHT));
        listOfCards.add(getCardNotFlush(ValueEnum.QUEEN));
        listOfCards.add(getCard(ValueEnum.ACE));

        return listOfCards;
    }

    private Card getCardNotFlush(ValueEnum valueEnum) {

        return new Card(valueEnum, SuitEnum.DIAMONDS);
    }
}