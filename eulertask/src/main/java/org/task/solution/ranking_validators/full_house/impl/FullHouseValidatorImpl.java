package org.task.solution.ranking_validators.full_house.impl;

import org.task.model.Hand;
import org.task.solution.ranking_validators.full_house.FullHouseValidator;

public class FullHouseValidatorImpl implements FullHouseValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var firstCardValue = hand.getNCard(0).getValue();
        var secondCardValue = hand.getNCard(1).getValue();
        var thirdCardValue = hand.getNCard(2).getValue();
        var fourthCardValue = hand.getNCard(3).getValue();
        var fifthCardValue = hand.getNCard(4).getValue();

        return (firstCardValue == thirdCardValue && fourthCardValue == fifthCardValue)
                || (firstCardValue == secondCardValue && thirdCardValue == fifthCardValue);
    }
}
