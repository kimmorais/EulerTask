package org.task.model;

public class Round {

    private final Hand playerOneHand;
    private final Hand playerTwoHand;

    public Round(Hand playerOneHand, Hand playerTwoHand) {
        this.playerOneHand = playerOneHand;
        this.playerTwoHand = playerTwoHand;
    }

    public boolean playerOneWins() {

        return true;
    }
}
