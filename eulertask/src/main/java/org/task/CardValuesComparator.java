package org.task;

import org.task.model.Card;

import java.util.*;

public class CardValuesComparator implements Comparator<Card> {

    private static final Map<Character, List<Character>> MAP_OF_WEAKER_VALUES;

    static {
        var valuesWeakerThanAce = List.of('K', 'Q', 'J', 'T');
        var valuesWeakerThanKing = List.of('Q', 'J', 'T');
        var valuesWeakerThanQueen = List.of('J', 'T');
        var valuesWeakerThanJack = List.of('T');
        var valuesWeakerThanTen = new ArrayList<Character>();

        MAP_OF_WEAKER_VALUES = Map.of(
                'A', valuesWeakerThanAce,
                'K', valuesWeakerThanKing,
                'Q', valuesWeakerThanQueen,
                'J', valuesWeakerThanJack,
                'T', valuesWeakerThanTen
        );
    }

    @Override
    public int compare(Card card1, Card card2) {

        var cardOneIsANumber = valueIsANumber(card1);
        var cardTwoIsANumber = valueIsANumber(card2);

        if (cardOneIsANumber && cardTwoIsANumber) {

            return compareNumbers(card1, card2);
        }

        if (!cardOneIsANumber && !cardTwoIsANumber) {

            return compareLetters(card1, card2);
        }

        if (!cardOneIsANumber) {
            return 1;
        }

        return -1;
    }

    private int compareNumbers(Card card1, Card card2) {

        var cardOneValue = card1.getValue().getValueChar();
        var cardTwoValue = card2.getValue().getValueChar();

        return Character.compare(cardOneValue, cardTwoValue);
    }

    private int compareLetters(Card card1, Card card2) {

        var cardOneValue = card1.getValue().getValueChar();
        var cardTwoValue = card2.getValue().getValueChar();

        if (MAP_OF_WEAKER_VALUES.get(cardOneValue).contains(cardTwoValue)) {

            return 1;
        }
        if (MAP_OF_WEAKER_VALUES.get(cardTwoValue).contains(cardOneValue)) {

            return -1;
        }
        return 0;
    }

    private boolean valueIsANumber(Card card) {

        return card.getValue().getValueChar() > 49
                && card.getValue().getValueChar() < 58;
    }
}
