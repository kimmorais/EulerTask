package org.task.solution.ranking_validators.three_of_a_kind.impl;

import org.task.model.Hand;
import org.task.solution.ranking_validators.three_of_a_kind.ThreeOfAKindValidator;

public class ThreeOfAKindValidatorImpl implements ThreeOfAKindValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var thirdCardValue = hand.getNCard(2).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return firstCardValue == thirdCardValue
                || secondCardValue == fourthCardValue
                || thirdCardValue == fifthCardValue;
    }
}
