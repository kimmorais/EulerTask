package org.task.comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.task.constants.SuitEnum;
import org.task.constants.ValueEnum;
import org.task.model.Card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardValuesComparatorTest {

    private static final int ZERO = 0;
    private Card baseNumberCard;
    private Card lowerNumberCard;
    private Card baseLetterCard;
    private Card lowerLetterCard;
    private CardValuesComparator comparator;

    @BeforeEach
    void setUp() {

        baseNumberCard = createBaseNumberCard();
        baseLetterCard = createBaseLetterCard();
        comparator = new CardValuesComparator();
    }

    @Test
    @DisplayName("Given two cards of equal number values compared, should return zero")
    void compare_compareEqualNumbers() {

        var result = comparator.compare(baseNumberCard, baseNumberCard);

        assertEquals(ZERO, result);
    }

    @Test
    @DisplayName("Given two cards of higher and lower number values compared, should return positive integer")
    void compare_compareHigherAndLowerNumbers() {

        lowerNumberCard = new Card(ValueEnum.FOUR, SuitEnum.HEARTS);

        var result = comparator.compare(baseNumberCard, lowerNumberCard);

        assertTrue(result > ZERO);
    }

    @Test
    @DisplayName("Given two cards of lower and higher number values compared, should return negative integer")
    void compare_compareLowerAndHigherNumbers() {

        lowerNumberCard = new Card(ValueEnum.FOUR, SuitEnum.HEARTS);

        var result = comparator.compare(lowerNumberCard, baseNumberCard);

        assertTrue(result < ZERO);
    }

    @Test
    @DisplayName("Given two cards of equal letter values compared, should return zero")
    void compare_compareEqualLetters() {

        var result = comparator.compare(baseLetterCard, baseLetterCard);

        assertEquals(ZERO, result);
    }

    @Test
    @DisplayName("Given two cards of lower and higher letter values compared, should return negative integer")
    void compare_compareLowerAndHigherLetter() {

        lowerLetterCard = new Card(ValueEnum.TEN, SuitEnum.HEARTS);

        var result = comparator.compare(lowerLetterCard, baseLetterCard);

        assertTrue(result < ZERO);
    }

    @Test
    @DisplayName("Given two cards of higher and lower letter values compared, should return positive integer")
    void compare_compareHigherAndLowerLetter() {

        lowerLetterCard = new Card(ValueEnum.TEN, SuitEnum.HEARTS);

        var result = comparator.compare(baseLetterCard, lowerLetterCard);

        assertTrue(result > ZERO);
    }

    private Card createBaseNumberCard() {

        return new Card(ValueEnum.EIGHT, SuitEnum.HEARTS);
    }

    private Card createBaseLetterCard() {

        return new Card(ValueEnum.QUEEN, SuitEnum.HEARTS);
    }
}