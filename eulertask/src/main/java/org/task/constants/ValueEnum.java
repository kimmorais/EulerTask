package org.task.constants;

public enum ValueEnum {

    TWO('2', 2),
    THREE('3', 3),
    FOUR('4', 4),
    FIVE('5', 5),
    SIX('6', 6),
    SEVEN('7', 7),
    EIGHT('8', 8),
    NINE('9', 9),
    TEN('T', 10),
    JACK('J', 11),
    QUEEN('Q', 12),
    KING('K', 13),
    ACE('A', 14);

    private final char valueChar;

    private final Integer valueOrdinal;

    ValueEnum(char valueChar, int valueOrdinal) {
        this.valueChar = valueChar;
        this.valueOrdinal = valueOrdinal;
    }

    public char getValueChar() {
        return valueChar;
    }

    public Integer getValueOrdinal() {
        return valueOrdinal;
    }

    public static ValueEnum fromValue(char character) {

        for (ValueEnum v: ValueEnum.values()) {

            if (Character.valueOf(v.valueChar).equals(character)) {

                return v;
            }
        }

        return null;
    }
}
