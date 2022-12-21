package org.task.solution.ranking_validators.pair.impl;

import org.task.model.Card;
import org.task.model.Hand;
import org.task.solution.ranking_validators.pair.PairValidator;

import java.util.List;

public class PairValidatorImpl implements PairValidator {

    @Override
    public boolean isCompatibleWith(Hand hand) {

        var validatorAux = new ValidatorAux(hand.getCards());
        return hand.getCards().stream().anyMatch(validatorAux::execute);
    }

    private static class ValidatorAux {

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
                return this.isAPair(card, nextCard);
            }

            return false;
        }

        private boolean isAPair(Card card, Card nextCard) {

            return card.getValue() == nextCard.getValue();
        }

        private boolean executionIsAllowed() {

            return this.nextCardIndex < 5;
        }
    }
}
