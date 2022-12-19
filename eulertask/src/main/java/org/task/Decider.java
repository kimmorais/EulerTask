package org.task;

import org.task.model.Hand;
import org.task.model.Round;

public class Decider {

    private final RankingDefiner rankingDefiner;

    public Decider() {

        this(new RankingDefiner());
    }

    public Decider(RankingDefiner rankingDefiner) {

        this.rankingDefiner = rankingDefiner;
    }

    public boolean playerOneWins(Round round) {

        var pOneHand = round.playerOneHand();
        var pTwoHand = round.playerTwoHand();
        
        var pOneRanking = rankingDefiner.defineRanking(pOneHand);

        pOneHand.setRankingEnum(pOneRanking.rankingEnum());
        pOneHand.setHighestValueRanking(pOneRanking.card().getValue());

        var pTwoRanking = rankingDefiner.defineRanking(pTwoHand);

        pTwoHand.setRankingEnum(pTwoRanking.rankingEnum());
        pTwoHand.setHighestValueRanking(pTwoRanking.card().getValue());

        return decide(pOneHand, pTwoHand);
    }

    private boolean decide(Hand pOneHand, Hand pTwoHand) {

        var pOneHandRankingValue = pOneHand.getRankingEnum().getValue();
        var pTwoHandRankingvalue = pTwoHand.getRankingEnum().getValue();

        if (pOneHandRankingValue > pTwoHandRankingvalue) {
            return true;
        }
        if (pOneHandRankingValue.equals(pTwoHandRankingvalue)) {

            return decideEquality(pOneHand, pTwoHand);
        }

        return false;
    }

    private boolean decideEquality(Hand pOneHand, Hand pTwoHand) {

        var pOneCardValue = pOneHand.getHighestValueRanking().getValueOrdinal();
        var pTwoCardValue = pTwoHand.getHighestValueRanking().getValueOrdinal();

        if (pOneCardValue > pTwoCardValue) {

            return true;
        }
        if (pOneCardValue.equals(pTwoCardValue)) {

            var pOneCards = pOneHand.getCards();
            var pTwoCards = pTwoHand.getCards();

            for (int i = 0; i < 5; i++) {

                var pOneHighestCardValue = pOneCards.get(i).getValue().getValueOrdinal();
                var pTwoHighestCardValue = pTwoCards.get(i).getValue().getValueOrdinal();

                if (pOneHighestCardValue > pTwoHighestCardValue) {
                    return true;
                }
                if (pTwoHighestCardValue > pOneHighestCardValue) {
                    return false;
                }
            }
        }

        return false;
    }
}
