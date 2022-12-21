package org.task.solution.ranking_validators.royal.impl;

import org.task.constants.ValueEnum;
import org.task.model.Hand;
import org.task.solution.ranking_validators.royal.RoyalValidator;

import java.util.Map;

public class RoyalValidatorImpl implements RoyalValidator {

    private static final Map<Integer, ValueEnum> ROYAL_VALIDATION_RULES = Map.of(
            0, ValueEnum.TEN,
            1, ValueEnum.JACK,
            2, ValueEnum.QUEEN,
            3, ValueEnum.KING,
            4, ValueEnum.ACE);

    @Override
    public boolean isCompatibleWith(Hand hand) {

        return ROYAL_VALIDATION_RULES.keySet()
                .stream()
                .allMatch(key -> hand.getNCard(key).getValue().equals(ROYAL_VALIDATION_RULES.get(key)));
    }
}
