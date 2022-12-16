package org.task.constants;

public enum ValueEnum {

    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char valueChar;

    ValueEnum(char valueChar) {
        this.valueChar = valueChar;
    }

    public char getValueChar() {
        return valueChar;
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
