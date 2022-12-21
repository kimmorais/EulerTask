package org.task.solution.processing.impl;

import org.task.model.Hand;
import org.task.model.Round;
import org.task.solution.processing.Decider;
import org.task.solution.processing.RankingDefiner;

public class DeciderImpl implements Decider {

    private final RankingDefiner rankingDefiner;

    public DeciderImpl(RankingDefiner rankingDefiner) {

        this.rankingDefiner = rankingDefiner;
    }

    public boolean playerOneWins(Round round) {

        var playerOne = round.playerOneHand();
        var playerTwo = round.playerTwoHand();
        this.attachRankingTo(playerOne);
        this.attachRankingTo(playerTwo);

        if (this.isADrawBasedOnRankingBetween(playerOne, playerTwo)) {

            return decideEquality(playerOne, playerTwo);
        }

        return playerOne.getRankingEnum().getValue() > playerTwo.getRankingEnum().getValue();
    }

    private void attachRankingTo(Hand aHand){

        var ranking = this.rankingDefiner.defineRanking(aHand);

        aHand.setRankingEnum(ranking.rankingEnum());
        aHand.setHighestValueRanking(ranking.card().getValue());
    }

    private boolean isADrawBasedOnRankingBetween(Hand playerOneHand, Hand playerTwoHand) {

        return playerOneHand.getRankingEnum().getValue().equals(playerTwoHand.getRankingEnum().getValue());
    }

    private boolean decideEquality(Hand pOneHand, Hand pTwoHand) {

        if (this.isADrawBasedOnTheHighestValueRankingBetween(pOneHand, pTwoHand)) {

            return this.playerOneBreaksTheTieBasedOnHandCards(pOneHand, pTwoHand);
        }

        return pOneHand.getHighestValueRanking().getValueOrdinal() > pTwoHand.getHighestValueRanking().getValueOrdinal();
    }

    private boolean playerOneBreaksTheTieBasedOnHandCards(Hand pOneHand, Hand pTwoHand) {

        for (int i = 4; i >= 0; i--) {

            var pOneHighestCardValue = pOneHand.getNCard(i).getValue().getValueOrdinal();
            var pTwoHighestCardValue = pTwoHand.getNCard(i).getValue().getValueOrdinal();

            if (pOneHighestCardValue.equals(pTwoHighestCardValue)) {

                continue;
            }

            return pOneHighestCardValue > pTwoHighestCardValue;
        }

        return false;
    }

    private boolean isADrawBasedOnTheHighestValueRankingBetween(Hand pOneHand, Hand pTwoHand) {

        return pOneHand.getHighestValueRanking().getValueOrdinal().equals(pTwoHand.getHighestValueRanking().getValueOrdinal());
    }
}
