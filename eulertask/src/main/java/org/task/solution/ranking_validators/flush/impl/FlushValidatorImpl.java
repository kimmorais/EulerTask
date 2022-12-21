package org.task.solution.ranking_validators.flush.impl;

import org.task.model.Card;
import org.task.model.Hand;
import org.task.solution.ranking_validators.flush.FlushValidator;

import java.util.stream.IntStream;

public class FlushValidatorImpl implements FlushValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var suit = hand.getNCard(0).getSuit();

        return IntStream.range(1, 5)
                .mapToObj(hand::getNCard)
                .map(Card::getSuit)
                .allMatch(otherSuit -> otherSuit.equals(suit));

    }
}
