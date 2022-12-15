package org.task.constants;

import java.util.HashMap;
import java.util.Map;

public enum Value {

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

    private final char value;

    private static final Map<Character, Value> MAP_OF_VALUES;

    static {
        MAP_OF_VALUES = new HashMap<>();

        for (var c: Value.values()) {

            MAP_OF_VALUES.put(c.getValue(), c);
        }
    }

    Value(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Value valueOf(char c) {

        return MAP_OF_VALUES.get(c);
    }
}
