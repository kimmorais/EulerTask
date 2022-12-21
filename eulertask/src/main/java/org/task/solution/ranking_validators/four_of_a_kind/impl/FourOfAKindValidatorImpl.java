package org.task.solution.ranking_validators.four_of_a_kind.impl;

import org.task.model.Hand;
import org.task.solution.ranking_validators.four_of_a_kind.FourOfAKindValidator;

public class FourOfAKindValidatorImpl implements FourOfAKindValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == fourthCardValue || secondCardValue == fifthCardValue;
    }
}
