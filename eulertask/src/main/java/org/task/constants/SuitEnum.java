package org.task.constants;

public enum SuitEnum {

    SPADES('S'),
    HEARTS('H'),
    DIAMONDS('D'),
    CLUBS('C');

    private final char suitChar;

    SuitEnum(char suitChar) {
        this.suitChar = suitChar;
    }

    public char getSuitChar() {
        return suitChar;
    }

    public static SuitEnum fromValue(char caracter) {

        for (SuitEnum s: SuitEnum.values()) {

            if (Character.valueOf(s.suitChar).equals(caracter)) {

                return s;
            }
        }

        return null;
    }
}
