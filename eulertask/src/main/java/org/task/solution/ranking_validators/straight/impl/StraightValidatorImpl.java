package org.task.solution.ranking_validators.straight.impl;

import org.task.model.Card;
import org.task.model.Hand;
import org.task.solution.ranking_validators.straight.StraightValidator;

import java.util.List;

public class StraightValidatorImpl implements StraightValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var validatorAux = new ValidatorAux(hand.getCards());
        return hand.getCards().stream().allMatch(validatorAux::execute);
    }

    private static class ValidatorAux{

        private final List<Card> cards;
        public ValidatorAux(List<Card> cards){

            this.cards = cards;
        }
        private Integer nextCardIndex = 1;
        public void incrementCounter() {

            this.nextCardIndex++;
        }

        public boolean execute(Card card) {

            if (this.executionIsAllowed()) {

                var nextCard = this.cards.get(this.nextCardIndex);
                this.incrementCounter();
                return this.isInSequence(card, nextCard);
            }

            return true;
        }

        private boolean isInSequence(Card card, Card nextCard) {

            return (card.getValue().getValueOrdinal() + 1 == nextCard.getValue().getValueOrdinal());
        }

        private boolean executionIsAllowed() {

            return this.nextCardIndex < 5;
        }
    }
}
